/**
* README
* This extension is being used to add delivery information in custom table EXT002
*
* Name: EXT002MI.Add
* Description: Adding delivery information
* Date                         Changed By                         Description
* 20211008                     Neha Agarwal            Adding delivery information
*/
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Add extends ExtendM3Transaction {
  private final MIAPI mi;
  private final ProgramAPI program;
  private final MICallerAPI miCaller;
  private final DatabaseAPI database;
  
  //Input variables
  private int iCONO
  private String iDLIX
  private String iWHLO
  private boolean hasError = false
  
  public Add(MIAPI mi, ProgramAPI program, MICallerAPI miCaller, DatabaseAPI database) {
    this.mi = mi;
    this.program = program;
    this.miCaller = miCaller;
    this.database = database;
  }
  
  public void main() {
    
    iCONO = mi.inData.get("CONO") == null ? 0 : Integer.parseInt(mi.inData.get("CONO").trim())
    iDLIX = mi.inData.get("DLIX") == null ? "" : mi.inData.get("DLIX").trim()
    iWHLO = mi.inData.get("WHLO") == null ? "" : mi.inData.get("WHLO").trim()
    
    validate()
    
    if(hasError == false){
      DBAction query = database.table("EXT002")
      .index("00")
      .selection("EXWHLO")
      .build()
      DBContainer EXT002 = query.getContainer()
      EXT002.set("EXCONO", iCONO)
      EXT002.set("EXDLIX", Integer.parseInt(iDLIX))
      if(query.read(EXT002)){
        mi.error("Record already exists")
        return
      }else{
        EXT002.set("EXWHLO", iWHLO)
        EXT002.set("EXRGDT", LocalDate.now().format(DateTimeFormatter.ofPattern("YYYYMMdd")).toInteger())
        EXT002.set("EXRGTM", LocalTime.now().format(DateTimeFormatter.ofPattern("HHmmss")).toInteger())
        EXT002.set("EXLMDT", LocalDate.now().format(DateTimeFormatter.ofPattern("YYYYMMdd")).toInteger())
        EXT002.set("EXCHNO", 0)
        EXT002.set("EXCHID", program.getUser())
        query.insert(EXT002, callback)
      }
    }
  }
  
  Closure <?> callback = {}
   
  /**
   * validate - Validate Input
   * @return
   */
   void validate(){
     
     //Check company number if blank
     if(iCONO == null || iCONO == 0){
       mi.error("Company must be entered")
       hasError = true
     }
     
     //Check delivery number if blank
     if(iDLIX == null || iDLIX == ""){
       mi.error("Delivery number must be entered")
       hasError = true
     }
     
     //Check warehouse if blank
     if(iWHLO == null || iWHLO == ""){
       mi.error("Warehouse must be entered")
       hasError = true
     }
     
     //Check for valid warehouse
     def parameters = ["WHLO": iWHLO]
     Closure<?> handler = {
       Map<String, String> response ->
       if(response.containsKey("error")){
         mi.error("Warehouse does not exist")
         hasError = true
       }
     }
     miCaller.call("MMS005MI", "GetWarehouse", parameters, handler)
   }
}