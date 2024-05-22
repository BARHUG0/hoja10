package uvg;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeSet;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws FileNotFoundException, IOException
    {
        FileManager fM = new FileManager();
        String result = fM.readTXTFile("digraphTest.txt");
        ArrayList<WeightedVertex> wArr = Tokenizer.getVertexsFromString(result);
        WeightedDigraph wDigrap = new WeightedDigraph(wArr);
        Set<String> tSet = wDigrap.getNodes();
        Set<Map.Entry<String, SortedMap<String, Float>>> entries = wDigrap.getEntry();
        Float[][] wMatrix = wDigrap.getWeigthMatrix();
        Float[][] fMatrix = wDigrap.getFloydMatrix();

        for(String str : tSet){
            System.out.println(str);
        }
        for(Map.Entry<String, SortedMap<String, Float>> entry: entries){
            System.out.println(entry);
        }
        /*
        for(int i = 0; i < wMatrix.length; i++){
            for(int j = 0; j < wMatrix.length; j++){
                System.out.print(wMatrix[i][j] + "|");
            }
            System.out.println("");
        }
        */
        
        
        for(int i = 0; i < fMatrix.length; i++){
            for(int j = 0; j < fMatrix.length; j++){
                System.out.print(fMatrix[i][j] + "|");
            }
            System.out.println("");
        }

        String center = wDigrap.getGraphCenter();
        System.out.println(center);


    }
}
