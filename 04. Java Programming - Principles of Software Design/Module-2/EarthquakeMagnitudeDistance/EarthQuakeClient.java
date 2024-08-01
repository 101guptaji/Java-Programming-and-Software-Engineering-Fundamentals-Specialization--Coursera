
import java.util.*;
import edu.duke.*;

public class EarthQuakeClient {
    
    private ArrayList<QuakeEntry> filterByMagnitude(ArrayList<QuakeEntry> quakeData, double magMin) {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();

        for (QuakeEntry qe : quakeData) {
            if (qe.getMagnitude() > magMin) {
                answer.add(qe);
            }
        }
        return answer;              
    }
    
    private ArrayList<QuakeEntry> filterByDistanceFrom(ArrayList<QuakeEntry> quakeData, double distMax, Location from) {      
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        
        for (QuakeEntry qe : quakeData) {
            if (qe.getLocation().distanceTo(from) < distMax) {
                answer.add(qe);
            }
        }
        return answer;
    }
    
    private void dumpCSV(ArrayList<QuakeEntry> list){
        /*
        System.out.println("Latitude,Longitude,Magnitude,Info");
        for(QuakeEntry qe : list){
            System.out.printf("%4.2f,%4.2f,%4.2f,%s\n",
                              qe.getLocation().getLatitude(),
                              qe.getLocation().getLongitude(),
                              qe.getMagnitude(),
                              qe.getInfo());
        }
        */
        for(QuakeEntry qe : list){
            System.out.println(qe);
        }
        
    }
    
    public void bigQuakes() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedatasmall.atom";
        ArrayList<QuakeEntry> list = parser.read(source);
        System.out.println("read data for " + list.size() + " quakes");
        /*
        for (QuakeEntry qe : list) {
            if (qe.getMagnitude() > 5.0) {
                System.out.println(qe);
            }
        }
        */
        ArrayList<QuakeEntry> listBig = filterByMagnitude(list, 5.0);
        for (QuakeEntry qe : listBig) {
           System.out.println(qe); 
        }
        System.out.println("Found "+listBig.size()+" quakes that match that criteria");
    }
    
    public void createCSV(){
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedatasmall.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";

        ArrayList<QuakeEntry> list = parser.read(source);
        System.out.println("# quakes read: " + list.size());
        dumpCSV(list);
    }
    
    public void closeToMe() {
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedatasmall.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list = parser.read(source);
        System.out.println("# quakes read: " + list.size());
        
        //Durham, NC
        Location city = new Location(35.988, -78.907);
        //Bridgeport, CA
        //Location city = new Location(38.17, -118.82);
        ArrayList<QuakeEntry> close = filterByDistanceFrom(list, 1000*1000, city);
        for (int k=0; k< close.size(); k++) {
            QuakeEntry entry = close.get(k);
            double distanceInMeters = city.distanceTo(entry.getLocation());
            System.out.println(distanceInMeters/1000 + " " + entry.getInfo());
        }
        System.out.println("Found "+close.size()+" quakes that match that criteria");
    }
    
    //Write the method filterByDepth that has three parameters, an ArrayList of type QuakeEntry named quakeData,  a double named minDepth and a double named maxDepth. This method should return an ArrayList of type QuakeEntry of all the earthquakes from quakeData whose depth is between minDepth and maxDepth, exclusive.
    private ArrayList<QuakeEntry> filterByDepth(ArrayList<QuakeEntry> quakeData, double minDepth, double maxDepth)
    {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        
        for (QuakeEntry qe : quakeData) {
            if (qe.getDepth() > minDepth && qe.getDepth() < maxDepth) {
                answer.add(qe);
            }
        }
        return answer;
        
    }
    
    //Write the void method quakesOfDepth that has no parameters to use filterByDepth and print all the earthquakes from a data source whose depth is between a given minimum and maximum value. You should also print out the number of earthquakes found. 
    public void quakesOfDepth()
    {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list = parser.read(source);
        System.out.println("read data for " + list.size() + " quakes");
        
        ArrayList<QuakeEntry> listBig = filterByDepth(list, -12000.0, -10000.0);
        for (QuakeEntry qe : listBig) {
           System.out.println(qe); 
        }
        System.out.println("Found "+listBig.size()+" quakes that match that criteria");
    }
    
    //Write the method filterByPhrase that has three parameters, an ArrayList of type QuakeEntry named quakeData, a String named where that indicates where to search in the title and has one of three values: (“start”, ”end”, or “any”), and a String named phrase, indicating the phrase to search for in the title of the earthquake. The title of the earthquake can be obtained through the getInfo() method. The filterByPhrase method should return an ArrayList of type QuakeEntry of all the earthquakes from quakeData whose titles have the given phrase found at location where (“start” means the phrase must start the title, “end” means the phrase must end the title and “any” means the phrase is a substring anywhere in the title.)
    private ArrayList<QuakeEntry> filterByPhrase(ArrayList<QuakeEntry> quakeData, String where, String phrase)
    {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        
        for (QuakeEntry qe : quakeData) {
            if (where.equals("start") && qe.getInfo().startsWith(phrase)) 
            {
                answer.add(qe);
            }
            else if (where.equals("end") && qe.getInfo().endsWith(phrase)) 
            {
                answer.add(qe);
            }
            else if (where.equals("any") && qe.getInfo().contains(phrase)) 
            {
                answer.add(qe);
            }
        }
        return answer;
    }
    
    //Write the void method quakesByPhrase to use filterByPhrase and print all the earthquakes from a data source that have phrase in their title in a given position in the title. You should also print out the number of earthquakes found. 
    public void quakesByPhrase()
    {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list = parser.read(source);
        System.out.println("read data for " + list.size() + " quakes");
        
        ArrayList<QuakeEntry> listBig = filterByPhrase(list, "start", "Quarry Blast");
        //ArrayList<QuakeEntry> listBig = filterByPhrase(list, "any", "Can");
        //ArrayList<QuakeEntry> listBig = filterByPhrase(list, "end", "Alaska");
        for (QuakeEntry qe : listBig) {
           System.out.println(qe); 
        }
        System.out.println("Found "+listBig.size()+" quakes that match that criteria");

    }
}







