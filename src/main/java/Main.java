import com.sun.org.apache.xerces.internal.impl.xpath.regex.Match;
import org.apache.commons.io.IOUtils;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.*;

public class Main {

    public String readRawDataToString() throws Exception{
        ClassLoader classLoader = getClass().getClassLoader();
        String result = IOUtils.toString(classLoader.getResourceAsStream("RawData.txt"));
        return result;
    }
    //clean up text file to readable lines
    public static ArrayList<String> cleanText(String junk){
        ArrayList<String> lines = new ArrayList<String>();
        Pattern linePattern = Pattern.compile("[^##]*", Pattern.CASE_INSENSITIVE);
        Matcher lineMatcher = linePattern.matcher(junk);
        while(lineMatcher.find()){
            lines.add(lineMatcher.group());
        }
        for (int i = 0; i <lines.size() ; i++) {
            System.out.println(lines.get(i));
        }
        return lines;
    }
    public static void main(String[] args) throws Exception{
        String output = (new Main()).readRawDataToString();
        cleanText(output);
        System.out.println(output);

    }
}
