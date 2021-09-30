public class LstFieldValue extends ExtendM3Transaction {
  private final MIAPI mi;
  private final DatabaseAPI database;
   private final ProgramAPI program;
  public LstFieldValue(MIAPI mi, DatabaseAPI database, ProgramAPI program) {
    this.mi = mi;
    this.database = database;
    this.program = program;
  }
  
  public void main() {
    
   // int company = Integer.parseInt(mi.inData.get("CONO"));
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
    
   actionEXTCUS.readAll(containerEXTCUS,2,lockResult)
      //mi.outData.put("APIR","NOK-Record does not exist");
  }   
    
 Closure <?> lockResult = { DBContainer containerEXTCUS ->
      
      mi.outData.put("APIR","OK");
      mi.outData.put("CONO",containerEXTCUS.get("EXCONO").toString());
      mi.outData.put("FILE",containerEXTCUS.get("EXFILE").toString());
      mi.outData.put("PK01",containerEXTCUS.get("EXPK01").toString());
      mi.outData.put("PK02",containerEXTCUS.get("EXPK02").toString());
      mi.outData.put("PK03",containerEXTCUS.get("EXPK03").toString());
      mi.outData.put("PK04",containerEXTCUS.get("EXPK04").toString());
      mi.outData.put("PK05",containerEXTCUS.get("EXPK05").toString());
      mi.outData.put("PK06",containerEXTCUS.get("EXPK06").toString());
      mi.outData.put("PK07",containerEXTCUS.get("EXPK07").toString());
      mi.outData.put("PK08",containerEXTCUS.get("EXPK08").toString());
      
      mi.outData.put("A030",containerEXTCUS.get("EXA030").toString());
      mi.outData.put("A130",containerEXTCUS.get("EXA130").toString());
      mi.outData.put("A230",containerEXTCUS.get("EXA230").toString());
      mi.outData.put("A330",containerEXTCUS.get("EXA330").toString());
      mi.outData.put("A430",containerEXTCUS.get("EXA430").toString());
      mi.outData.put("A530",containerEXTCUS.get("EXA530").toString());
      mi.outData.put("A630",containerEXTCUS.get("EXA630").toString());
      mi.outData.put("A730",containerEXTCUS.get("EXA730").toString());
      mi.outData.put("A830",containerEXTCUS.get("EXA830").toString());
      mi.outData.put("A930",containerEXTCUS.get("EXA930").toString());
      
      mi.outData.put("N096",containerEXTCUS.get("EXN096").toString());
      mi.outData.put("N196",containerEXTCUS.get("EXN196").toString());
      mi.outData.put("N296",containerEXTCUS.get("EXN296").toString());
      mi.outData.put("N396",containerEXTCUS.get("EXN396").toString());
      mi.outData.put("N496",containerEXTCUS.get("EXN496").toString());
      mi.outData.put("N596",containerEXTCUS.get("EXN596").toString());
      mi.outData.put("N696",containerEXTCUS.get("EXN696").toString());
      mi.outData.put("N796",containerEXTCUS.get("EXN796").toString());
      mi.outData.put("N896",containerEXTCUS.get("EXN896").toString());
      mi.outData.put("N996",containerEXTCUS.get("EXN996").toString());
      
      mi.outData.put("MIGR",containerEXTCUS.get("EXMIGR").toString());
      mi.outData.put("A256",containerEXTCUS.get("EXA256").toString());
      mi.outData.put("A121",containerEXTCUS.get("EXA121").toString());
      mi.outData.put("A122",containerEXTCUS.get("EXA122").toString());
      mi.outData.put("DTID",containerEXTCUS.get("EXDTID").toString());
      mi.outData.put("TXID",containerEXTCUS.get("EXTXID").toString());
      
      mi.outData.put("CHB1",containerEXTCUS.get("EXCHB1").toString());
      mi.outData.put("CHB2",containerEXTCUS.get("EXCHB2").toString());
      mi.outData.put("CHB3",containerEXTCUS.get("EXCHB3").toString());
      mi.outData.put("CHB4",containerEXTCUS.get("EXCHB4").toString());
      
      mi.outData.put("DAT1",containerEXTCUS.get("EXDAT1").toString());
      mi.outData.put("DAT2",containerEXTCUS.get("EXDAT2").toString());
      mi.outData.put("DAT3",containerEXTCUS.get("EXDAT3").toString());
      mi.outData.put("DAT4",containerEXTCUS.get("EXDAT4").toString());
      
      mi.outData.put("RGDT",containerEXTCUS.get("EXRGDT").toString());
      mi.outData.put("RGTM",containerEXTCUS.get("EXRGTM").toString());
      mi.outData.put("LMDT",containerEXTCUS.get("EXLMDT").toString());
      mi.outData.put("CHID",containerEXTCUS.get("EXCHID").toString());
      mi.write();
      
 }  
    
    
  
}