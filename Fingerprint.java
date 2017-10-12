import java.util.ArrayList;

public class Fingerprint{
    private ArrayList hashes; //Arraylist of hashes (gotten from Hash.java)
    private int gramSize; //length of n-gram (variables)
    private int windowSize; //Size of the window we're using to winnow
    private ArrayList<Integer> prints; //Eventual fingerprint of the document

    public Fingerprint(ArrayList<Integer> h, int sz, int window){
        hashes = h;
        gramSize = sz;
        windowSize = window;
        prints = makeFingerprint(hashes);
    }

    public int getGramSize(){
        return gramSize;
    }

    public void setGramSize(int newSize){
        gramSize = newSize > 0 ? newSize : gramSize;
    }

    public int setWindowSize(int newWindow){
        windowSize = newWindow;
    }

    public HashSet<String> getPrints{
        return prints;
    }

    private static void addPrint(String print){
        prints.add(print);
    }

    public boolean contains(String print){
        return prints.contains(print);
    }

    public String printsToString(){
        String answer = new String("");
        for(String print : prints){
            answer = answer + print;
        }
        return answer;
    }

    private static ArrayList<Integer> makeFingerprint(ArrayList<Integer> hashes){
        prints.clear();
        //clear the ArrayList to make a new set of prints
        //add code here to create the document's fingerprint
        //use winnowing method
    }

    public double compareFingerprints(Fingerprint doc2){
        HashSet<String> prints2 = doc2.getPrints();
        int sharedHashes = 0;
        if(hashes.size() <= doc2.getPrints().size())){
            for(int i = 0; i < hashes.size(); i++){
                if(doc2.getPrints().contains(hashes.get(i)))
                    sharedHashes++;
            }
        }
        //add code here to compare two documents' fingerprints (HashSets)
        //should return the percent of fingerprints that match
        //probably need to change this because we want to create a hash that
        //encodes both highly similar words and exact word matches
        return 0;
    }
}
