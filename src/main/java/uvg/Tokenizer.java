package uvg;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Tokenizer {
    
    
    /** 
     * @param str
     * @return ArrayList<Association<String, String>>
     */
    public static ArrayList<WeightedVertex> getVertexsFromString(String str){

        String regex = "([^ ]+) ([^ ]+) (\\d+)";
        Pattern pattern = Pattern.compile(regex); 
        Matcher matcher = pattern.matcher(str);
        ArrayList<WeightedVertex> resultArr = new ArrayList<WeightedVertex>();

        while(matcher.find()){
            resultArr.add(new WeightedVertex(matcher.group(1), matcher.group(2), Integer.parseInt(matcher.group(3))));
        }

        return resultArr;
    }

    public String[] getNodesFromString(String str){

        String regex = "([^ \\d]+)";
        Pattern pattern = Pattern.compile(regex); 
        Matcher matcher = pattern.matcher(str);
        ArrayList<String> arr = new ArrayList<String>();

        while(matcher.find()){
            arr.add(matcher.group(1));
        }

        String[] resultArr = new String[arr.size()];
        for(int i = 0; i < arr.size(); i++){
            resultArr[i] = arr.get(i);
        }

        return resultArr;
    }
}

