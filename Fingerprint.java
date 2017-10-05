public class Fingerprint{
    private ArrayList <Integer> hashes;
    private int gramSize; //length of n-gram (variables)
    private int numGrams; //number of n-grams used for fingerprint (we could do a percent and randomly pick n-grams)
    private Set<String> prints;

    public Fingerprint(ArrayList<Integer> h, int sz, int grams){
        hashes = h;
        gramSize = sz;
        numGrams = grams;
    }

    public void setFile(File doc){
        text = doc;
    }

    public int getN(){
        return gramSize;
    }

    public void setN(int newN){
        n = newN > 0 ? newN : n;
    }

    public int setNumGrams(int newNum){
        numGrams = newNum;
    }

    public void getNumGrams(){
        return numGrams;
    }

    public HashSet<String> getPrints{
        return prints;
    }

    public void addPrint(String print){
        prints.add(print);
    }

    public boolean hasPrint(String print){
        return prints.contains(print);
    }

    public String toString(){
        String answer = new String("");
        for(String print : prints){
            answer = answer + print;
        }
        return answer;
    }

    public void makeFingerprint(){
        prints.clear();
        new FileReader f = new FileReader(text);
        //clear the HashSet to make a new set of prints
        //add code here to create the document's fingerprint
        f.close();
    }

    public double compareFingerprints(Fingerprint doc2){
        HashSet<String> prints2 = doc2.getPrints;
        //add code here to compare two documents' fingerprints (HashSets)
        //should return the percent of fingerprints that match
        return 0; //change this
    }
}
