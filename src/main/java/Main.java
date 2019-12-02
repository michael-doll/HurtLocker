import com.sun.org.apache.xerces.internal.impl.xpath.regex.Match;
import com.sun.org.apache.xml.internal.serialize.LineSeparator;
import org.apache.commons.io.IOUtils;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.*;

public class Main {

    public static void main(String[] args) throws Exception{
        String output = (new Main()).readRawDataToString();
        ArrayList<String> stringList = cleanText(output);
        ArrayList<Item> items = makinItems(stringList);
        //Testing
        int count = 1;
        for(Item item: items){
            System.out.println(item.getPrice() + count);
            count++;
        }
    }
    public String readRawDataToString() throws Exception{
        ClassLoader classLoader = getClass().getClassLoader();
        String result = IOUtils.toString(classLoader.getResourceAsStream("RawData.txt"));
        return result;
    }

    //clean up text file to readable lines
    public static ArrayList<String> cleanText(String junk){
        ArrayList<String> lines = new ArrayList<String>();
        Pattern linePattern = Pattern.compile("[^##]*6", Pattern.CASE_INSENSITIVE);
        Matcher lineMatcher = linePattern.matcher(junk);
        while(lineMatcher.find()){
            lines.add(lineMatcher.group());
        }
//        for (int i = 0; i <lines.size() ; i++) {
//            System.out.println(lines.get(i));
//        }
        return lines;
    }
    public static String findName (String line){
        Pattern namePattern = Pattern.compile("(?<=name:).*(?=;price)", Pattern.CASE_INSENSITIVE); //"(?<=name:).*"
        Matcher nameMatcher = namePattern.matcher(line);
        while(nameMatcher.find()){
            System.out.println(nameMatcher.group() + "**********************");
            return nameMatcher.group();
        }
        return null;
    }

    public static String findPrice (String line){
        Pattern pricePattern = Pattern.compile("(?<=price:).*(?=;type)", Pattern.CASE_INSENSITIVE);
        Matcher priceMatcher = pricePattern.matcher(line);
        while(priceMatcher.find()){
            return priceMatcher.group();
        }
        return null;
    }
    public static ArrayList<Item> makinItems(ArrayList<String> lines){
        ArrayList<Item> items = new ArrayList<Item>();
        for(String line : lines) {
            String name = findName(line);
            String price = findPrice(line);
            Item item = new Item(name, price);
            items.add(item);
        }
        return items;
    }

}
