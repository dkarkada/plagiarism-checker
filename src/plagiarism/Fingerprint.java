package plagiarism;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Fingerprint{
    private ArrayList<Hash> hashes;
    private int gramSize;
    private int guarantee;
    private ArrayList<Hash> fingerprint;

    /**
     * 
     * @param filepath				Path to the document
     * @param ksize					Size of k-gram (noise threshold; matches below ksize will not be found)
     * @param guaranteeThreshold	Matches >= this size are guaranteed to be found
     */
    public Fingerprint(String filepath, int ksize, int guaranteeThreshold){
        gramSize = ksize;
        guarantee = guaranteeThreshold;
    	if (gramSize > guarantee)
    		throw new IllegalArgumentException("guarantee threshold must be >= ksize.");
    	if (gramSize <= 0)
    		throw new IllegalArgumentException("parameters must be positive.");
        hashes = new ArrayList<Hash>();
        fingerprint = new ArrayList<Hash>();
        makeHashes(parseFile(filepath));
        makeFingerprint();
    }
    public int getGramSize(){
        return gramSize;
    }
    public ArrayList<Hash> getPrint(){
        return fingerprint;
    }
    public int size() {
    	return fingerprint.size();
    }
    public boolean hasHash(Hash hash){
        return fingerprint.contains(hash);
    }
    public String toString(){
    	return fingerprint.toString();
    }
    /**
     * Measures similarity between this and another fingerprint.
     * @param other		Another fingerprint
     * @return percentage of matches
     */
    public double compare(Fingerprint other){
    	double pctMatch = 0;
    	for (Hash h : this.fingerprint)
    		if (other.fingerprint.contains(h))
    			pctMatch++;
    	pctMatch /= this.size();
        return pctMatch; 
    }
    
    /**
     * Parse the file into a list of words. Filters out garbage.
     * Program exits if file or io exception.
     * @param filepath
     * @return words in document
     */
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
    /**
     * Takes a list of words, representing the contents of the document (order
     * retained), and generate a list of representative hashes.
     * @param words
     */
    private void makeHashes(ArrayList<String> words) {
    	// ignore words shorter than threshold
    	int threshold = 3;
    	for (int i=words.size()-1; i>=0; i--) {
    		if (words.get(i).length() < threshold)
    			words.remove(i);
    	}
    	// create hashes
    	for (int i=0; i < words.size() - gramSize + 1; i++) {
        	// create k-gram 
        	String kgram = "";
    		for (int j=0; j<gramSize; j++) {
    			kgram += words.get(i+j);
    		}
    		// hash k-gram by character, keeping hashes under 1 million
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
    /**
     * Uses the winnowing technique to select all the distinct, least hashes
     * within consecutive windows of size guarantee - gramSize + 1. This list
     * of hashes constitutes the fingerprint of the document. 
     */
    private void makeFingerprint(){
    	int window = guarantee - gramSize + 1;
    	int minIndex = -1;
    	// traverse through each window
    	for (int i=0; i < hashes.size() - window + 1; i++) {
    		// if previous minimum is still in the window, only compare to new element
    		if (minIndex >= i) {
    			if (hashes.get(i+window-1).getHash() < hashes.get(minIndex).getHash()) {
    				minIndex = i+window-1;
    				fingerprint.add(hashes.get(i+window-1));
    			}
    		}
    		// otherwise, traverse the window to find the least element
    		else {
    			Hash min = null;
	    		for (int ind = 0; ind < window; ind++) {
	    			if (min==null || hashes.get(ind+i).getHash() < min.getHash()) {
	    				min = hashes.get(ind+i);
	    				minIndex = ind + i;
	    			}
	    		}
	    		fingerprint.add(min);
    		}
    	}
    }
    
}

/**
 * Represents the hash of a document substring, along with the index of the
 * substring within the larger document. The index of the hash is useful for
 * locating hash matches (since it is difficult to recreate the substring
 * from the hash).
 * @author Dhruva
 *
 */
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
	@Override
	public boolean equals(Object other) {
		return other instanceof Hash && ((Hash)other).hash == this.hash;
	}
	@Override
	public int hashCode() {
		return hash;
	}
}
