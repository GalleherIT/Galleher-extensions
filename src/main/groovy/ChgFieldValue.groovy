// Description: ChgFieldValue transaction to change values to EXTCUS Tables instead of using CUGEX1 Table.
import java.text.SimpleDateFormat;

public class ChgFieldValue extends ExtendM3Transaction {
  private final MIAPI mi;
  private final DatabaseAPI database;
  private final ProgramAPI program;
  private String apiResponse="";
  
  public ChgFieldValue(MIAPI mi, DatabaseAPI database, ProgramAPI program) {
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
    
    apiResponse = "NOK-Record does not exist";
    
    actionEXTCUS.readLock(containerEXTCUS,callback);
    
    mi.outData.put("APIR",apiResponse);
  }
  
  Closure <?> callback = { LockedResult lockedResult ->
  
    String a030 = mi.inData.get("A030");
    String a130 = mi.inData.get("A130");
    String a230 = mi.inData.get("A230");
    String a330 = mi.inData.get("A330");
    String a430 = mi.inData.get("A430");
    String a530 = mi.inData.get("A530");
    String a630 = mi.inData.get("A630");
    String a730 = mi.inData.get("A730");
    String a830 = mi.inData.get("A830");
    String a930 = mi.inData.get("A930");
    double n096 = (mi.inData.get("N096").trim()?Double.parseDouble(mi.inData.get("N096")):0);
    double n196 = (mi.inData.get("N196").trim()?Double.parseDouble(mi.inData.get("N196")):0);
    double n296 = (mi.inData.get("N296").trim()?Double.parseDouble(mi.inData.get("N296")):0);
    double n396 = (mi.inData.get("N396").trim()?Double.parseDouble(mi.inData.get("N396")):0);
    double n496 = (mi.inData.get("N496").trim()?Double.parseDouble(mi.inData.get("N496")):0);
    double n596 = (mi.inData.get("N596").trim()?Double.parseDouble(mi.inData.get("N596")):0);
    double n696 = (mi.inData.get("N696").trim()?Double.parseDouble(mi.inData.get("N696")):0);
    double n796 = (mi.inData.get("N796").trim()?Double.parseDouble(mi.inData.get("N796")):0);
    double n896 = (mi.inData.get("N896").trim()?Double.parseDouble(mi.inData.get("N896")):0);
    double n996 = (mi.inData.get("N996").trim()?Double.parseDouble(mi.inData.get("N996")):0);
    int migr = (mi.inData.get("MIGR").trim()?Integer.parseInt(mi.inData.get("MIGR")):0);
    String a256 = mi.inData.get("A256");
    String a121 = mi.inData.get("A121");
    String a122 = mi.inData.get("A122");
    int dtid = (mi.inData.get("DTID").trim()?Integer.parseInt(mi.inData.get("DTID").trim()):0);
    int txid = (mi.inData.get("TXID").trim()?Integer.parseInt(mi.inData.get("TXID").trim()):0);
    int chb1 = (mi.inData.get("CHB1").trim()?Integer.parseInt(mi.inData.get("CHB1")):0);
    int chb2 = (mi.inData.get("CHB2").trim()?Integer.parseInt(mi.inData.get("CHB2")):0);
    int chb3 = (mi.inData.get("CHB3").trim()?Integer.parseInt(mi.inData.get("CHB3")):0);
    int chb4 = (mi.inData.get("CHB4").trim()?Integer.parseInt(mi.inData.get("CHB4")):0);
    int dat1 = (mi.inData.get("DAT1").trim()?Integer.parseInt(mi.inData.get("DAT1")):0);
    int dat2 = (mi.inData.get("DAT2").trim()?Integer.parseInt(mi.inData.get("DAT2")):0);
    int dat3 = (mi.inData.get("DAT3").trim()?Integer.parseInt(mi.inData.get("DAT3")):0);
    int dat4 = (mi.inData.get("DAT4").trim()?Integer.parseInt(mi.inData.get("DAT4")):0);
    
      if(mi.inData.get("A030")?.trim()){
        lockedResult.set("EXA030",a030);
      }
      if(mi.inData.get("A130")?.trim()){
        lockedResult.set("EXA130",a130);
      }
      if(mi.inData.get("A230")?.trim()){
        lockedResult.set("EXA230",a230);
      }
      if(mi.inData.get("A330")?.trim()){
        lockedResult.set("EXA330",a330);
      }
      if(mi.inData.get("A430")?.trim()){
        lockedResult.set("EXA430",a430);
      }
      if(mi.inData.get("A530")?.trim()){
        lockedResult.set("EXA530",a530);
      }
      if(mi.inData.get("A630")?.trim()){
        lockedResult.set("EXA630",a630);
      }
      if(mi.inData.get("A730")?.trim()){
        lockedResult.set("EXA730",a730);
      }
      if(mi.inData.get("A830")?.trim()){
        lockedResult.set("EXA830",a830);
      }
      if(mi.inData.get("A930")?.trim()){
        lockedResult.set("EXA930",a930);
      }
      if(mi.inData.get("N096")?.trim()){
        lockedResult.set("EXN096",n096);
      }
      if(mi.inData.get("N196")?.trim()){
        lockedResult.set("EXN196",n196);
      }
      if(mi.inData.get("N296")?.trim()){
        lockedResult.set("EXN296",n296);
      }
      if(mi.inData.get("N396")?.trim()){
        lockedResult.set("EXN396",n396);
      }
      if(mi.inData.get("N496")?.trim()){
        lockedResult.set("EXN496",n496);
      }
      if(mi.inData.get("N596")?.trim()){
        lockedResult.set("EXN596",n596);
      }
      if(mi.inData.get("N696")?.trim()){
        lockedResult.set("EXN696",n696);
      }
      if(mi.inData.get("N796")?.trim()){
        lockedResult.set("EXN796",n796);
      }
      if(mi.inData.get("N896")?.trim()){
        lockedResult.set("EXN896",n896);
      }
      if(mi.inData.get("N996")?.trim()){
        lockedResult.set("EXN996",n996);
      }
      if(mi.inData.get("A256")?.trim()){
        lockedResult.set("EXA256",a256);
      }
      if(mi.inData.get("A121")?.trim()){
        lockedResult.set("EXA121",a121);
      }
      if(mi.inData.get("A122")?.trim()){
        lockedResult.set("EXA122",a122);
      }
      if(mi.inData.get("MIGR")?.trim()){
        lockedResult.set("EXMIGR",migr);
      }
      if(mi.inData.get("DTID")?.trim()){
        lockedResult.set("EXDTID",dtid);
      }
      if(mi.inData.get("TXID")?.trim()){
        lockedResult.set("EXTXID",txid);
      }
      if(mi.inData.get("CHB1")?.trim()){
        lockedResult.set("EXCHB1",chb1);
      }
      if(mi.inData.get("CHB2")?.trim()){
        lockedResult.set("EXCHB2",chb2);
      }
      if(mi.inData.get("CHB3")?.trim()){
        lockedResult.set("EXCHB3",chb3);
      }
      if(mi.inData.get("CHB4")?.trim()){
        lockedResult.set("EXCHB4",chb4);
      }
      if(mi.inData.get("DAT1")?.trim()){
        lockedResult.set("EXDAT1",dat1);
      }
      if(mi.inData.get("DAT2")?.trim()){
        lockedResult.set("EXDAT2",dat2);
      }
      if(mi.inData.get("DAT3")?.trim()){
        lockedResult.set("EXDAT3",dat3);
      }
      if(mi.inData.get("DAT4")?.trim()){
        lockedResult.set("EXDAT4",dat4);
      }
      
      
      
      int lmdt = Integer.parseInt(new SimpleDateFormat("yyyyMMdd").format(new Date()));
      lockedResult.set("EXLMDT",lmdt);
      int chno = Integer.parseInt(lockedResult.get("EXCHNO").toString());
      chno = chno + 1;
      lockedResult.set("EXCHNO",chno);
      lockedResult.set("EXCHID",program.getUser());
      
      lockedResult.update();
      apiResponse = "OK";
      
  };
  
}