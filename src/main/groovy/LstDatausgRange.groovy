// Description: LstDatausgRange transaction to getting list of data between two dates.
public class LstDatausgRange extends ExtendM3Transaction {
  private final MIAPI mi;
  private final DatabaseAPI database;
   private final ProgramAPI program;
  public LstDatausgRange(MIAPI mi, DatabaseAPI database, ProgramAPI program) {
    this.mi = mi;
    this.database = database;
    this.program = program;
  }
  
  public void main() {
    int currentCompany = program.LDAZD.CONO;
     String file = mi.inData.get("FILE");
    String FK01_Data = mi.inData.get("FK01");
     String TK01_Data = mi.inData.get("TK01");
      String A530 = mi.inData.get("A530");
    String pk02 = mi.inData.get("PK02");
     String pk03 = mi.inData.get("PK03");
    String pk04 = mi.inData.get("PK04");
    String pk05 = mi.inData.get("PK05");
    String pk06 = mi.inData.get("PK06");
    String pk07 = mi.inData.get("PK07");
    String pk08 = mi.inData.get("PK08");
    
  ExpressionFactory expression = database.getExpressionFactory("EXTCUS");
  expression = expression.between("EXPK01",FK01_Data,TK01_Data).and(expression.eq("EXA530",A530));
 // expression = expression.eq("MMITGR", "EX-GROUP").and(expression.gt("MMCFI1", "2"))
  DBAction query = database.table("EXTCUS").index("00").matching(expression).selectAllFields().build();
  DBContainer container = query.getContainer();
  container.set("EXCONO", currentCompany);
  container.set("EXFILE", file);
  query.readAll(container, 2, releasedItemProcessor)
  }
  
  Closure<?> releasedItemProcessor = { DBContainer container ->
  //String description = container.get("MMITDS")
   mi.outData.put("CONO",container.get("EXCONO").toString());
      mi.outData.put("FILE",container.get("EXFILE").toString());
      mi.outData.put("PK01",container.get("EXPK01").toString());
      mi.outData.put("PK02",container.get("EXPK02").toString());
      mi.outData.put("PK03",container.get("EXPK03").toString());
      mi.outData.put("PK04",container.get("EXPK04").toString());
      mi.outData.put("PK05",container.get("EXPK05").toString());
      mi.outData.put("PK06",container.get("EXPK06").toString());
      mi.outData.put("PK07",container.get("EXPK07").toString());
      mi.outData.put("PK08",container.get("EXPK08").toString());
      
      mi.outData.put("A030",container.get("EXA030").toString());
      mi.outData.put("A130",container.get("EXA130").toString());
      mi.outData.put("A230",container.get("EXA230").toString());
      mi.outData.put("A330",container.get("EXA330").toString());
      mi.outData.put("A430",container.get("EXA430").toString());
      mi.outData.put("A530",container.get("EXA530").toString());
      mi.outData.put("A630",container.get("EXA630").toString());
      mi.outData.put("A730",container.get("EXA730").toString());
      mi.outData.put("A830",container.get("EXA830").toString());
      //mi.outData.put("A930",container.get("EXA930").toString());
      
      mi.outData.put("N096",container.get("EXN096").toString());
     /* mi.outData.put("N196",container.get("EXN196").toString());
      mi.outData.put("N296",container.get("EXN296").toString());
      mi.outData.put("N396",container.get("EXN396").toString());
      mi.outData.put("N496",container.get("EXN496").toString());
      mi.outData.put("N596",container.get("EXN596").toString());
      mi.outData.put("N696",container.get("EXN696").toString());
      mi.outData.put("N796",container.get("EXN796").toString());
      mi.outData.put("N896",container.get("EXN896").toString());
      mi.outData.put("N996",container.get("EXN996").toString());
      
      mi.outData.put("MIGR",container.get("EXMIGR").toString());
      mi.outData.put("A256",container.get("EXA256").toString());
      mi.outData.put("A121",container.get("EXA121").toString());
      mi.outData.put("A122",container.get("EXA122").toString());
      mi.outData.put("DTID",container.get("EXDTID").toString());
      mi.outData.put("TXID",container.get("EXTXID").toString());
      
      mi.outData.put("CHB1",container.get("EXCHB1").toString());
      mi.outData.put("CHB2",container.get("EXCHB2").toString());
      mi.outData.put("CHB3",container.get("EXCHB3").toString());
      mi.outData.put("CHB4",container.get("EXCHB4").toString());
      
      mi.outData.put("DAT1",container.get("EXDAT1").toString());
      mi.outData.put("DAT2",container.get("EXDAT2").toString());
      mi.outData.put("DAT3",container.get("EXDAT3").toString());
     // mi.outData.put("DAT4",container.get("EXDAT4").toString());
      */
      mi.outData.put("RGDT",container.get("EXRGDT").toString());
     // mi.outData.put("RGTM",container.get("EXRGTM").toString());
      //mi.outData.put("LMDT",container.get("EXLMDT").toString());
      mi.outData.put("CHID",container.get("EXCHID").toString());
      mi.write();
 
}
}