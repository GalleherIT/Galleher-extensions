// Description: DelFieldValue transaction to delete values from EXTCUS Tables instead of using CUGEX1 Table.

public class DelFieldValue extends ExtendM3Transaction {
  private final MIAPI mi;
  private final DatabaseAPI database;
  private final ProgramAPI program;
  private String apiResponse = "";
  
  public DelFieldValue(MIAPI mi, DatabaseAPI database, ProgramAPI program) {
    this.mi = mi;
    this.database = database;
    this.program = program;
  }
  
  public void main() {
    
    int company = program.LDAZD.CONO;
    String file = mi.inData.get("FILE");
    String pk01 = mi.inData.get("PK01");
    String pk02 = mi.inData.get("PK02");
    String pk03 = mi.inData.get("PK03");
    String pk04 = mi.inData.get("PK04");
    String pk05 = mi.inData.get("PK05");
    String pk06 = mi.inData.get("PK06");
    String pk07 = mi.inData.get("PK07");
    String pk08 = mi.inData.get("PK08");
    
    DBAction actionEXTCUS = database.table("EXTCUS").index("00").selectAllFields().build();
    DBContainer containerEXTCUS = actionEXTCUS.getContainer();
    containerEXTCUS.set("EXCONO",company);
    containerEXTCUS.set("EXFILE",file);
    containerEXTCUS.set("EXPK01",pk01);
    containerEXTCUS.set("EXPK02",pk02);
    containerEXTCUS.set("EXPK03",pk03);
    containerEXTCUS.set("EXPK04",pk04);
    containerEXTCUS.set("EXPK05",pk05);
    containerEXTCUS.set("EXPK06",pk06);
    containerEXTCUS.set("EXPK07",pk07);
    containerEXTCUS.set("EXPK08",pk08);
    
    apiResponse = "NOK-Record cannot be deleted";
    
    actionEXTCUS.readLock(containerEXTCUS,callback);
    
    mi.outData.put("APIR",apiResponse);
  }
  
  Closure <?> callback = { LockedResult lockedResult ->
  
    lockedResult.delete();
    apiResponse = "OK";
    
  };
}