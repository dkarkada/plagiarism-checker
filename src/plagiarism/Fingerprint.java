package plagiarism;

import java.util.*;

public class Fingerprint{
    private ArrayList <Integer> hashes;
    private Set<String> prints;

    public Fingerprint(ArrayList<Integer> h, int sz, int grams){
        hashes = h;
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
    }

    public boolean equals(Fingerprint doc2){
        return false;
    }
}
