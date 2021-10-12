/**
* README
* This extension is being used to add remaining amount information in custom table EXT001
*
* Name: EXT001MI.Add
* Description: Adding remaining amount information
* Date                         Changed By                         Description
* 20211008                     Lavkesh Chaudhari            Adding remaining amount information
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
  private String iORNO
  private String iCUNO
  private double iRAMT
  private boolean hasError = false

  
  public Add(MIAPI mi, ProgramAPI program, MICallerAPI miCaller, DatabaseAPI database) {
    this.mi = mi;
    this.program = program;
    this.miCaller = miCaller;
    this.database = database;
  }
  
  public void main() {
      
    iCONO = mi.inData.get("CONO") == null ? 0 : Integer.parseInt(mi.inData.get("CONO").trim())
    iORNO = mi.inData.get("ORNO") == null ? "" : mi.inData.get("ORNO").trim()
    iCUNO = mi.inData.get("CUNO") == null ? "" : mi.inData.get("CUNO").trim()
    iRAMT = mi.inData.get("RAMT") == null ? 0 : Double.parseDouble(mi.inData.get("RAMT").trim())
    
    validate()
        
	  if(hasError == false){
		  DBAction query = database.table("EXT001")
		  .index("00")
		  .selection("RAMT")
		  .build()
		  DBContainer EXT001 = query.getContainer()
		  EXT001.set("EXCONO", iCONO)
		  EXT001.set("EXORNO", iORNO)
		  if(query.read(EXT001)){
			  mi.error("Record already exists")
			  return
		  }else{
			  EXT001.set("EXCUNO", iCUNO)
			  EXT001.set("EXRAMT", iRAMT)
			  EXT001.set("EXRGDT", LocalDate.now().format(DateTimeFormatter.ofPattern("YYYYMMdd")).toInteger())
			  EXT001.set("EXRGTM", LocalTime.now().format(DateTimeFormatter.ofPattern("HHmmss")).toInteger())
			  EXT001.set("EXLMDT", LocalDate.now().format(DateTimeFormatter.ofPattern("YYYYMMdd")).toInteger())
			  EXT001.set("EXCHNO", 0)
			  EXT001.set("EXCHID", program.getUser())
			  query.insert(EXT001, callback)
		  }
    }
  }
    
  Closure <?> callback = {}
   
  /**
   * validate - Validate Input
   * @return boolean
   */
  void validate(){
     
    //Check company number if blank
    if(iCONO == null || iCONO == 0){
      mi.error("Company must be entered")
      hasError = true
    }
    
    //Check Order number if blank
    if(iORNO == null || iORNO == ""){
      mi.error("Order number must be entered")
      hasError = true
    }
     
    //Check Remaining amount if blank
    if(iRAMT == null || iRAMT == 0){
      mi.error("Remaining amount should not be null")
      hasError = true
    }
     
    //Check for valid Customer
    if(!iCUNO.isEmpty()){
    def parameters = ["CONO":iCONO.toString(), "CUNO": iCUNO ]
    Closure<?> handler = {
      Map<String, String> response ->
      if(response.containsKey("error")){
        mi.error("Customer does not exist")
        hasError = true
      }
    }
    miCaller.call("CRS610MI", "GetBasicData", parameters, handler)
    }
  }
}  
