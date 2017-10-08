import java.util.ArrayList;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

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
        String withoutQuotes = s.replace("'","");//goes through string and deletes apostrophes
        withoutQuotes = s.replace("\"", "");//goes through string and deletes quotation marks
        withoutQuotes = s.replace("-","");//goes through string and deletes hyphens
        return withoutQuotes;
    }

    private ArrayList<String> parse(String s){
        String line = deleteQuotes(s);
        String[] parsedLine;
        ArrayList<String> answer = new ArrayList<String>();
        parsedLine = line.split("\\P{L}+");//splits string whenever it hits something that isn't a letter
        //write a parsing method
        return answer;
    }
}
