
/**
 * Enumeration class GeneratorMode - write a description of the enum class here
 *
 * @author (your name here)
 * @version (version number or date here)
 */
public enum GeneratorMode
{
    CONTINIOUS("CONTINIOUS"), BURST("BURST");//SWEEP
    
    String text;
    private GeneratorMode(String text) {
    this.text = text;
   
  }
    
}
