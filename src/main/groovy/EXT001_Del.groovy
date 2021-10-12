/**
* README
* This extension is being used to delete remaining amount information from custom table EXT001
*
* Name: EXT001MI.Del
* Description: Delete remaining amount information
* Date                         Changed By                         Description
* 20211008                     Lavkesh Chaudhari            Delete remaining amount information
*/
public class Del extends ExtendM3Transaction {
  private final MIAPI mi;
  private final ProgramAPI program;
  private final MICallerAPI miCaller;
  private final DatabaseAPI database;
  
  //Input variables
  private int iCONO
  private String iORNO
  
  public Del(MIAPI mi, ProgramAPI program, MICallerAPI miCaller, DatabaseAPI database) {
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
    
    if(!query.readLock(EXT001,callback)){
      mi.error("Record does not exists")
      return
    }
  }
  
  Closure <?> callback = { LockedResult lockedResult ->
    lockedResult.delete();
  }
}