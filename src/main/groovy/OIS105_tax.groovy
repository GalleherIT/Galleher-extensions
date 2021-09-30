//Description:: Making Tax Field Noneditable

public class OIS105_tax extends ExtendM3Trigger {
  
  private final InteractiveAPI interactive
  
  public OIS105_tax(InteractiveAPI interactive) {
    
    this.interactive = interactive
    
  }
  
  public void main() {
    
    interactive.display.fields.WRTAXC = 'NOT'
    
  }
}