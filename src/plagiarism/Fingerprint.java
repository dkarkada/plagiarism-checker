package plagiarism;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Fingerprint{
    private ArrayList<Hash> hashes; //Arraylist of hashes (gotten from Hash.java)
    private int gramSize; //length of n-gram (variables)
    private int guarantee; // match of length >= guarantee is guaranteed to be detected
    private ArrayList<Integer> fingerprint; //Eventual fingerprint of the document

    public Fingerprint(String filepath, int ksize, int guaranteeThreshold){
        gramSize = ksize;
        guarantee = guaranteeThreshold;
    	if (gramSize > guarantee)
    		throw new IllegalArgumentException("guarantee threshold must be >= ksize.");
    	if (gramSize <= 0)
    		throw new IllegalArgumentException("parameters must be positive.");
        hashes = new ArrayList<Hash>();
        fingerprint = new ArrayList<Integer>();
        makeHashes(parseFile(filepath));
        makeFingerprint();
    }

    public int getGramSize(){
        return gramSize;
    }

    public ArrayList<Integer> getPrint(){
        return fingerprint;
    }
    
    private static ArrayList<String> parseFile(String filepath){
    	ArrayList<String> parsedText = new ArrayList<String>();
        try {
        	File document = new File(filepath);
	        BufferedReader reader = new BufferedReader(new FileReader(document));
	        String line;
	        while((line = reader.readLine()) != null){
	        	//goes through string and deletes crap
	            line = line.replace("['-]","");
	            //splits string whenever it hits something that isn't a letter
	            String[] parsedLine = line.split("[^a-zA-Z]+");
	            for (String word : parsedLine)
	            	parsedText.add(word.toLowerCase());
	        }
	        reader.close();
        } catch (FileNotFoundException e) {
        	e.printStackTrace();
        	System.exit(1);
        } catch (IOException e) {
        	e.printStackTrace();
        	System.exit(1);        	
        }
        return parsedText;
    }
    
    private void makeHashes(ArrayList<String> words) {
    	int threshold = 3;
    	for (int i=words.size()-1; i>=0; i--) {
    		if (words.get(i).length() < threshold)
    			words.remove(i);
    	}
    	for (int i=0; i < words.size() - gramSize + 1; i++) { 
        	String kgram = "";   		
    		for (int j=0; j<gramSize; j++) {
    			kgram += words.get(i+j);
    		}
    		char[] chars = kgram.toLowerCase().toCharArray();
    		int hash = 0;
    		for (char c : chars) {
    			hash <<= 6;
    			hash |= c - 96;
    			hash %= 1000000;
    		}
    		hashes.add(new Hash(hash, i));
    	}
    }

    public boolean hasHash(int hash){
        return fingerprint.contains(hash);
    }

    public String toString(){
    	return fingerprint.toString();
    }

    private void makeFingerprint(){ 
        //clear the ArrayList to make a new set of prints
        //add code here to create the document's fingerprint
        //use winnowing method
    	int window = gramSize - guarantee + 1;
    	int index = 0;
    	
    }

    public double compareFingerprints(Fingerprint f2){
    	ArrayList<Integer> f2p = f2.fingerprint;
        //add code here to compare two documents' fingerprints (HashSets)
        //should return the percent of fingerprints that match
        return 0; 
    }
}

class Hash{
	private int hash;
	private int index;
	
	public Hash(int hash, int ind) {
		this.hash = hash;
		this.index = ind;
	}
	public int getHash() {
		return hash;
	}
	public int getIndex() {
		return index;
	}
	@Override
	public String toString() {
		return Integer.toString(hash);
	}
}
