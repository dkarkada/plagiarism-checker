import java.util.ArrayList;
import java.io.FileReader;
import java.io.IOException;

public class FileParser{
    private File document;
    private ArrayList<String> parsedText;

    FileParser(File d){
        document = d;
        parsedText = new ArrayList<String>();

        FileReader f = new FileReader(document);
        String next = f.readLine();
        while(!next.isEmpty() && next != null){
            parsedText.addAll(parse(next));
        }
        f.close();
    }

    public File getFile(){
        return document;
    }

    public void setFile(File newDoc){
        document = newDoc;
    }

    private static String deleteQuotes(String s){
        //goes through string and deletes quotation marks and apostrophes
    }

    private ArrayList<String> parse(String s){
        ArrayList<String> answer = new ArrayList<String>();
        //write a parsing method
        return answer;
    }
}
