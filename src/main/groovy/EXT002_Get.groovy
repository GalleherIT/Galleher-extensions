/**
* README
* This extension is being used to retrieve delivery information from custom table EXT002
*
* Name: EXT002MI.Get
* Description: Get delivery information
* Date                         Changed By                         Description
* 20211008                     Neha Agarwal            Get delivery information
*/
public class Get extends ExtendM3Transaction {
  private final MIAPI mi;
  private final ProgramAPI program;
  private final MICallerAPI miCaller;
  private final DatabaseAPI database;
  
  //Input variables
  private int iCONO
  private String iDLIX
  
  public Get(MIAPI mi, ProgramAPI program, MICallerAPI miCaller, DatabaseAPI database) {
    this.mi = mi;
    this.program = program;
    this.miCaller = miCaller;
    this.database = database;
  }
  
  public void main() {
    iCONO = mi.inData.get("CONO") == null ? 0 : Integer.parseInt(mi.inData.get("CONO").trim())
    iDLIX = mi.inData.get("DLIX") == null ? "" : mi.inData.get("DLIX").trim()
    
    //Check company number if blank
     if(iCONO == null || iCONO == 0){
       mi.error("Company must be entered")
       return
     }
    
    //Check delivery number if blank
     if(iDLIX == null || iDLIX == ""){
       mi.error("Delivery number must be entered")
       return
     }
    
    DBAction query = database.table("EXT002")
      .index("00")
      .selection("EXWHLO")
      .build()
    DBContainer EXT002 = query.getContainer()
    EXT002.set("EXCONO", iCONO)
    EXT002.set("EXDLIX", Integer.parseInt(iDLIX))
    if(!query.read(EXT002)){
      mi.error("No record found")
      return
    }else{
      mi.outData.put("WHLO", EXT002.getString("EXWHLO"))
      mi.write()
    }
  }
}