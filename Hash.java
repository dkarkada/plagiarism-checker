import java.util.ArrayList;

public class Hash{
    private ArrayList<String> words; //this should be the parsed document
    private ArrayList<Integer> wordsHash; //this is words after it's been hashed

    Hash(){
        words = new ArrayList<String>();
        wordsHash = new ArrayList<Integer>();
    }

    Hash(ArrayList<String> input){
        words = input;
        wordsHash = words.hash();
    }

    public ArrayList<String> getWords(){
        return words;
    }

    public ArrayList<Integer> getWordsHash(){
        return wordsHash;
    }

    public void setWords(ArrayList<String> newWords){
        words = newWords;
        wordsHash = hash(words);
    }

    public void changeWord(int i, String word){
        words.set(i, word);
        wordsHash.set(i, hash(words.get(i)));
    }

    public void addWord(int i, String word){
        words.add(i, word);
        wordsHash.add(i,hash(word));
    }

    public String getWord(int i){
        return words.get(i);
    }

    public String wordsToString(){
        String answer = new String("");
        for(String w : words){
            answer += w;
        }
        return answer;
    }

    public String hashToString(){
        String answer = new String("");
        for(Integer i : wordsHash){
            answer += i;
        }
        return answer;
    }

    private static Integer hash(String word){
        //add hashing algorithm here
        return 0;
    }

    private static void hash(ArrayList<String> list){
        for(int i = 0; i < words.size(); i++){
            wordsHash.set(i, hash(list.get(i)));
        }
    }
}
