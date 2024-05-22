package uvg;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;

import org.junit.Test;

public class WeigthedDigraphTest{
   
    
    @Test
    public void testNodes() throws FileNotFoundException, IOException{
        FileManager fM = new FileManager();
        String result = fM.readTXTFile("digraphTest.txt");
        ArrayList<WeightedVertex> wArr = Tokenizer.getVertexsFromString(result);
        WeightedDigraph wDigrap = new WeightedDigraph(wArr);

        Set<String> tSet = wDigrap.getNodes();
        String[] expected = new String[]{"a", "b", "c", "d"};
        int i = 0;
        for(String str : tSet){
            assertTrue(str.equals(expected[i]));
            i++;
        }
    }

    @Test
    public void testEntries() throws FileNotFoundException, IOException{
        FileManager fM = new FileManager();
        String result = fM.readTXTFile("digraphTest.txt");
        ArrayList<WeightedVertex> wArr = Tokenizer.getVertexsFromString(result);
        WeightedDigraph wDigrap = new WeightedDigraph(wArr);

        Set<Map.Entry<String, SortedMap<String, Float>>> entries = wDigrap.getEntry();

        String[] expectedNodes = new String[]{"a", "b", "c", "d"};
        String[] expectedNeighbors = new String[]{"c", "a", "b", "d", "a"};
        Float[] expectedF = new Float[]{3.0f, 2.0f, 7.0f, 1.0f, 6.0f};

        int i = 0;
        int j = 0;
        for(Map.Entry<String, SortedMap<String, Float>> entry: entries){
            assertTrue(entry.getKey().equals(expectedNodes[i]));
            for(Map.Entry<String, Float> subEntry : entry.getValue().entrySet()){
                assertTrue(subEntry.getKey().equals(expectedNeighbors[j]));
                j++;
            }
            i++;
        }
    }
    
    @Test
    public void weightedMatrixTest() throws FileNotFoundException, IOException{
        FileManager fM = new FileManager();
        String result = fM.readTXTFile("digraphTest.txt");
        ArrayList<WeightedVertex> wArr = Tokenizer.getVertexsFromString(result);
        WeightedDigraph wDigrap = new WeightedDigraph(wArr);
        Float[][] wMatrix = wDigrap.getWeigthMatrix();

        Float[] expectedFloats = new Float[]{0.0f, Float.POSITIVE_INFINITY, 3.0f, Float.POSITIVE_INFINITY, 2.0f, 0.0f, Float.POSITIVE_INFINITY, Float.POSITIVE_INFINITY, Float.POSITIVE_INFINITY, 7.0f, 0.0f, 1.0f, 6.0f, Float.POSITIVE_INFINITY, Float.POSITIVE_INFINITY, 0.0f};
        

        int m = 0;
        for(int i = 0; i < wMatrix.length; i++){
            for(int j = 0; j < wMatrix.length; j++){
                assertEquals(expectedFloats[m], wMatrix[i][j], 0);
                m++;
            }
            
        }
    }

    @Test
    public void addNodeTest() throws FileNotFoundException, IOException{
        FileManager fM = new FileManager();
        String result = fM.readTXTFile("digraphTest.txt");
        ArrayList<WeightedVertex> wArr = Tokenizer.getVertexsFromString(result);
        WeightedDigraph wDigrap = new WeightedDigraph(wArr);

        wDigrap.addNode("e");
        Set<String> tSet = wDigrap.getNodes();
        String[] expected = new String[]{"a", "b", "c", "d", "e", "f", "g"};
        int k = 0;
        for(String str : tSet){
            assertTrue(str.equals(expected[k]));
            k++;
        }

        Float[][] wMatrix = wDigrap.getWeigthMatrix();

        Float[] expectedFloats = new Float[]{0.0f, Float.POSITIVE_INFINITY, 3.0f, Float.POSITIVE_INFINITY, Float.POSITIVE_INFINITY, 2.0f, 0.0f, Float.POSITIVE_INFINITY, Float.POSITIVE_INFINITY, Float.POSITIVE_INFINITY, Float.POSITIVE_INFINITY, 7.0f, 0.0f, 1.0f, Float.POSITIVE_INFINITY, 6.0f, Float.POSITIVE_INFINITY, Float.POSITIVE_INFINITY, 0.0f, Float.POSITIVE_INFINITY, Float.POSITIVE_INFINITY, Float.POSITIVE_INFINITY, Float.POSITIVE_INFINITY, Float.POSITIVE_INFINITY, 0.0f};
        

        int m = 0;
        for(int i = 0; i < wMatrix.length; i++){
            for(int j = 0; j < wMatrix.length; j++){
                assertEquals(expectedFloats[m], wMatrix[i][j], 0);
                m++;
            }
            
        }

        wDigrap.addNode("e");
        String[] expected2 = new String[]{"a", "b", "c", "d", "e", "f", "g"};
        k = 0;
        for(String str : tSet){
            assertTrue(str.equals(expected2[k]));
            k++;
        }


    }

    @Test
    public void addWeightedVertexTest() throws FileNotFoundException, IOException{
        FileManager fM = new FileManager();
        String result = fM.readTXTFile("digraphTest.txt");
        ArrayList<WeightedVertex> wArr = Tokenizer.getVertexsFromString(result);
        WeightedDigraph wDigrap = new WeightedDigraph(wArr);

        wDigrap.addWeightedVertex("e","a", 7.0f);
        wDigrap.addWeightedVertex("e","b", 7.0f);
        wDigrap.addWeightedVertex("e","c", 7.0f);
        wDigrap.addWeightedVertex("e","d", 7.0f);

        Set<String> tSet = wDigrap.getNodes();
        String[] expected = new String[]{"a", "b", "c", "d", "e", "f", "g"};
        int k = 0;
        for(String str : tSet){
            assertTrue(str.equals(expected[k]));
            k++;
        }

        Float[][] wMatrix = wDigrap.getWeigthMatrix();

        Float[] expectedFloats = new Float[]{0.0f, Float.POSITIVE_INFINITY, 3.0f, Float.POSITIVE_INFINITY, Float.POSITIVE_INFINITY, 2.0f, 0.0f, Float.POSITIVE_INFINITY, Float.POSITIVE_INFINITY, Float.POSITIVE_INFINITY, Float.POSITIVE_INFINITY, 7.0f, 0.0f, 1.0f, Float.POSITIVE_INFINITY, 6.0f, Float.POSITIVE_INFINITY, Float.POSITIVE_INFINITY, 0.0f, Float.POSITIVE_INFINITY, 7.0f, 7.0f, 7.0f, 7.0f, 0.0f};
        

        int m = 0;
        for(int i = 0; i < wMatrix.length; i++){
            for(int j = 0; j < wMatrix.length; j++){
                assertEquals(expectedFloats[m], wMatrix[i][j], 0);
                m++;
            }
            
        }

    }

    @Test
    public void removeWeightedVertexTest() throws FileNotFoundException, IOException{
        FileManager fM = new FileManager();
        String result = fM.readTXTFile("digraphTest.txt");
        ArrayList<WeightedVertex> wArr = Tokenizer.getVertexsFromString(result);
        WeightedDigraph wDigrap = new WeightedDigraph(wArr);

        wDigrap.removeWeightedVertex("a", "c");
        wDigrap.removeWeightedVertex("b", "a");
        wDigrap.removeWeightedVertex("c", "b");

        Float[][] wMatrix = wDigrap.getWeigthMatrix();

        Float[] expectedFloats = new Float[]{0.0f, Float.POSITIVE_INFINITY, Float.POSITIVE_INFINITY, Float.POSITIVE_INFINITY, Float.POSITIVE_INFINITY, 0.0f, Float.POSITIVE_INFINITY, Float.POSITIVE_INFINITY, Float.POSITIVE_INFINITY, Float.POSITIVE_INFINITY, 0.0f, 1.0f, 6.0f, Float.POSITIVE_INFINITY, Float.POSITIVE_INFINITY, 0.0f};
        

        int m = 0;
        for(int i = 0; i < wMatrix.length; i++){
            for(int j = 0; j < wMatrix.length; j++){
                assertEquals(expectedFloats[m], wMatrix[i][j], 0);
                m++;
            }
            
        }
    }
        
    @Test
    public void getGraphCenterTest() throws FileNotFoundException, IOException{
        FileManager fM = new FileManager();
        String result = fM.readTXTFile("digraphTest.txt");
        ArrayList<WeightedVertex> wArr = Tokenizer.getVertexsFromString(result);
        WeightedDigraph wDigrap = new WeightedDigraph(wArr);

        String center = wDigrap.getGraphCenter();
        assertTrue(center.equals("d"));
    }
        
}
