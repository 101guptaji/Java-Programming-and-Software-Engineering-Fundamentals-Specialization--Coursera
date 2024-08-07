
/**
 * Write a description of class MarkovRunner here.
 * 
 * @author Duke Software
 * @version 1.0
 */

import edu.duke.*; 

public class MarkovRunnerWithInterface {
    private void runModel(IMarkovModel markov, String text, int size, int seed) {
        markov.setRandom(seed);
        markov.setTraining(text);
        System.out.println("running with " + markov);
        for(int k=0; k < 3; k++){
            String st= markov.getRandomText(size);
            printOut(st);
        }
    }
    
    public void runMarkov() {
        FileResource fr = new FileResource();
        String st = fr.asString();
        st = st.replace('\n', ' ');
        //st = "yes-this-is-a-thin-pretty-pink-thistle";
        int size = 200;
        int seed = 40;
        
        MarkovZero mz = new MarkovZero();
        runModel(mz, st, size, seed);
    
        MarkovOne mOne = new MarkovOne();
        runModel(mOne, st, size, seed);
        
        MarkovModel mThree = new MarkovModel(3);
        runModel(mThree, st, size, seed);
        
        MarkovFour mFour = new MarkovFour();
        runModel(mFour, st, size, seed);

    }
    
    public void testHashMap()
    {
        FileResource fr = new FileResource();
        String st = fr.asString();
        st = st.replace('\n', ' ');
        //st = "yes-this-is-a-thin-pretty-pink-thistle";
        int size = 500;
        int seed = 615;
        
        EfficientMarkovModel mEfficient = new EfficientMarkovModel (5);
        runModel(mEfficient, st, size, seed);

    }
    
    public void compareMethods()
    {
        FileResource fr = new FileResource();
        String st = fr.asString();
        st = st.replace('\n', ' ');
        //st = "yes-this-is-a-thin-pretty-pink-thistle";
        int size = 1000;
        int seed = 42;        
        
        MarkovModel mTwo = new MarkovModel(2);
        runModel(mTwo, st, size, seed);
        
        EfficientMarkovModel mEfficient = new EfficientMarkovModel (2);
        runModel(mEfficient, st, size, seed);
    }
    
    private void printOut(String s){
        String[] words = s.split("\\s+");
        int psize = 0;
        System.out.println("----------------------------------");
        for(int k=0; k < words.length; k++){
            System.out.print(words[k]+ " ");
            psize += words[k].length() + 1;
            if (psize > 60) {
                System.out.println();
                psize = 0;
            }
        }
        System.out.println("\n----------------------------------");
    }
    
}
