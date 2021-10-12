/**
* README
* This extension is being used to retrieve remaining amount information from custom table EXT001
*
* Name: EXT001MI.Get
* Description: Get remaining amount information
* Date                         Changed By                         Description
* 20211008                     Lavkesh Chaudhari            Get remaining amount information
*/
public class Get extends ExtendM3Transaction {
  private final MIAPI mi;
  private final ProgramAPI program;
  private final MICallerAPI miCaller;
  private final DatabaseAPI database;
  
  //Input variables
  private int iCONO
  private String iORNO
  
  public Get(MIAPI mi, ProgramAPI program, MICallerAPI miCaller, DatabaseAPI database) {
    this.mi = mi;
    this.program = program;
    this.miCaller = miCaller;
    this.database = database;
  }
  
  public void main() {
    iCONO = mi.inData.get("CONO") == null ? 0 : Integer.parseInt(mi.inData.get("CONO").trim())
    iORNO = mi.inData.get("ORNO") == null ? "" : mi.inData.get("ORNO").trim()
    
    //Check company number if blank
    if(iCONO == null || iCONO == 0){
      mi.error("Company must be entered")
      return
    }
    
    //Check order number if blank
    if(iORNO == null || iORNO == ""){
      mi.error("Order number must be entered")
      return
    }
    
    DBAction query = database.table("EXT001")
    .index("00")
    .selection("EXRAMT")
    .build()
    DBContainer EXT001 = query.getContainer()
    EXT001.set("EXCONO", iCONO)
    EXT001.set("EXORNO", iORNO)

    if(!query.read(EXT001)){
      mi.error("No record found")
      return
    }else{
      mi.outData.put("RAMT", EXT001.getDouble("EXRAMT").toString())
      mi.write()
    }
  }
}