
/**
 * Write the class MagnitudeFilter that implements Filter. 
 * This class should include private instance variables for a minimum and maximum magnitude, a constructor to initialize those variables, and a satisfies method that returns true if a QuakeEntry’s magnitude is between the minimum and maximum magnitudes, or equal to one of them. 
 * Otherwise it should return false.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

public class MagnitudeFilter implements Filter
{
    private double magMin, magMax; 
    
    public MagnitudeFilter (double min, double max) { 
        magMin = min;
        magMax = max;
    } 

    public boolean satisfies(QuakeEntry qe) { 
        return qe.getMagnitude() >= magMin && 
                qe.getMagnitude() <= magMax; 
    } 
    
    public String getName()
    {
        return "Magnitude";
    }
}
