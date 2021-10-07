/**
* README
* This extension is executed from OIS105 E panel to initialize the field WRTAXC (Tax code)
*
* Name: SLS_IW_EXT005_InzTaxCode
* Description: Initialization of field WRTAXC (Tax code)
 * Date                         Changed By                         Description
* 20211007                     Onkar Kulkarni            Initialization of field WRTAXC (Tax code)
*/
public class SLS_IW_EXT005_InzTaxCode extends ExtendM3Trigger {
  
  private final InteractiveAPI interactive
  
  public SLS_IW_EXT005_InzTaxCode(InteractiveAPI interactive) {
    this.interactive = interactive
  }
  
  public void main() {
    interactive.display.fields.WRTAXC = 'NOT'
  }
}