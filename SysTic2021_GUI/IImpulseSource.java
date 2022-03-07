
/**
 * Write a description of interface IImpulseSource here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public interface IImpulseSource
{
    void addImpulsListener(ImpulseListener il);
    void removeImpulseListener(ImpulseListener il);
    void on();
    void off();
    void setPeriod(int rateMS);
    void setMode(GeneratorMode mode);
    //do zrobienia
    //
}
