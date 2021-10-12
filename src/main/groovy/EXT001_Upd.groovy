/**
* README
* This extension is being used to update remaining amount information in custom table EXT001
*
* Name: EXT001MI.Upd
* Description: Update remaining amount information
* Date                         Changed By                         Description
* 20211008                     Lavkesh Chaudhari            Update remaining amount information
*/

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Upd extends ExtendM3Transaction {
  private final MIAPI mi
  private final DatabaseAPI database
  private final ProgramAPI program
  private final MICallerAPI miCaller;
  
  private int iCONO
  private String iORNO
  private String iCUNO
  private double iRAMT
  private boolean hasError = false
  
  public Upd(MIAPI mi, DatabaseAPI database, MICallerAPI miCaller, ProgramAPI program) {
    this.mi = mi
    this.database = database
    this.miCaller = miCaller
    this.program = program
  }
  
  public void main() {

    iCONO = mi.inData.get("CONO") == null ? 0 : Integer.parseInt(mi.inData.get("CONO").trim())
    iORNO = mi.inData.get("ORNO") == null ? "" : mi.inData.get("ORNO").trim()
    iCUNO = mi.inData.get("CUNO") == null ? "" : mi.inData.get("CUNO").trim()
    iRAMT = mi.inData.get("RAMT") == null ? 0 : Double.parseDouble(mi.inData.get("RAMT").trim())
    
    validate()
    
    if(hasError == false){
      DBAction actionEXT001 = database.table("EXT001").index("00").selectAllFields().build()
      DBContainer containerEXT001 = actionEXT001.getContainer()
      containerEXT001.set("EXCONO",iCONO)
      containerEXT001.set("EXORNO",iORNO)
    
      if(!actionEXT001.readLock(containerEXT001,callback)){
        mi.error("No record found")
        return
      }
    }
  }
  
  Closure <?> callback = { LockedResult lockedResult ->
  
    lockedResult.set("EXRAMT", iRAMT)
    lockedResult.set("EXLMDT", LocalDate.now().format(DateTimeFormatter.ofPattern("YYYYMMdd")).toInteger())
    lockedResult.set("EXCHNO", lockedResult.getInt("EXCHNO") + 1)
    lockedResult.set("EXCHID", program.getUser())
      
    lockedResult.update()
  }
  
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