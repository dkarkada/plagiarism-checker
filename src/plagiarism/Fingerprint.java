package plagiarism;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Fingerprint{
    private ArrayList<Integer> hashes; //Arraylist of hashes (gotten from Hash.java)
    private int gramSize; //length of n-gram (variables)
    private int windowSize; //Size of the window we're using to winnow
    private ArrayList<Integer> print; //Eventual fingerprint of the document

    public Fingerprint(String filepath, int sz, int window){
        gramSize = sz;
        windowSize = window;
        hashes = new ArrayList<Integer>();
        print = new ArrayList<Integer>();
        makeHashes(parseFile(filepath));
        makeFingerprint();
    }

    public int getGramSize(){
        return gramSize;
    }

    public void setGramSize(int newSize){
        gramSize = newSize > 0 ? newSize : gramSize;
    }

    public void setWindowSize(int newWindow){
        windowSize = newWindow > 0 ? newWindow : windowSize;
    }

    public ArrayList<Integer> getPrint(){
        return print;
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
    	for (String word : words) {
    		char[] chars = word.toLowerCase().toCharArray();
    		int hash = 0;
    		for (char c : chars) {
    			hash <<= 6;
    			hash |= c - 96;
    			hash %= 100000;
    		}
    		hashes.add(hash);
    	}
    }

    public boolean hasHash(int hash){
        return print.contains(hash);
    }

    public String toString(){
       // return print.toString();
    	return hashes.toString();
    }

    private void makeFingerprint(){
        print.clear(); //idk if necessary
        //clear the ArrayList to make a new set of prints
        //add code here to create the document's fingerprint
        //use winnowing method
    }

    public double compareFingerprints(Fingerprint f2){
    	ArrayList<Integer> f2p = f2.print;
        //add code here to compare two documents' fingerprints (HashSets)
        //should return the percent of fingerprints that match
        return 0; 
    }
}
