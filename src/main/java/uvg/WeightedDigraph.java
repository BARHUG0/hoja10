package uvg;

import java.util.ArrayList;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

public class WeightedDigraph {
    private SortedMap<String, SortedMap<String, Float>> digraph;
    private Float[][] weigthMatrix;
    private Float[][] floydMatrix;
    private Set<String> nodes;
    private int nodesAmount; 


    public WeightedDigraph(ArrayList<WeightedVertex> wArr){  
        generateDigraph(wArr);
        nodesAmount = digraph.size();
        nodes = digraph.keySet();
        generateWeigthMatrix();
        generateFloyd();
    } 

    public Set<String> getNodes(){
        return nodes;
    }

    public Set<Map.Entry<String, SortedMap<String, Float>>> getEntry(){
        return digraph.entrySet();
    }

    public Float[][] getWeigthMatrix(){
        return this.weigthMatrix;
    }

    public Float[][] getFloydMatrix(){
        return this.floydMatrix;
    }

    private void generateDigraph(ArrayList<WeightedVertex> wArr){
        digraph = new TreeMap<String, SortedMap<String, Float>>();
        for(WeightedVertex w : wArr){
            if(digraph.containsKey(w.getFirstNode())){
                digraph.get(w.getFirstNode()).put(w.getSecondNode(), w.getWeight());
            }else{
                TreeMap<String, Float> tMap = new TreeMap<String,Float>();
                tMap.put(w.getSecondNode(), w.getWeight());
                digraph.put(w.getFirstNode(), tMap);
            }
        }
    }

    private void generateWeigthMatrix(){
        weigthMatrix = new Float[nodesAmount][nodesAmount];
        for(int i = 0; i < nodesAmount; i++){
            for(int j = 0; j < nodesAmount; j++){
                if(i == j){
                    weigthMatrix[i][j] = 0.0f;
                }else{
                    weigthMatrix[i][j] = Float.POSITIVE_INFINITY;
                }
                
            }
        }

        int i = 0;
        for(String node : nodes){
            for(String neighbor : digraph.get(node).keySet()){
                weigthMatrix[i][getNodeIndex(neighbor)] = digraph.get(node).get(neighbor);
            }
            i++;
        }
    }

    public void generateFloyd(){
        copyWeigthMatrix();
        float ij = 0;
        float ik = 0;
        float kj = 0;

        for (int k = 0; k < nodesAmount; k++){
            for(int i = 0; i < nodesAmount; i++){
                for(int j = 0; j < nodesAmount; j++){
                    ij = floydMatrix[i][j];
                    ik = floydMatrix[i][k];
                    kj = floydMatrix[k][j];
                    
                    if(ik + kj < ij){
                        floydMatrix[i][j] = minDistance(ij, ik, kj);
                    }else{
                        floydMatrix[i][j] = minDistance(ij, ik, kj);
                    }

                }
            }
        }
    }

    private float minDistance(float ij, float ik, float kj){
        if(ik + kj < ij){
            return ik + kj;
        }
        return ij;
    }

    private void updateDigraph(){
        nodesAmount = digraph.size();
        nodes = digraph.keySet();
        generateWeigthMatrix();
        generateFloyd();
    }

    public void addNode(String node){
        TreeMap<String, Float> tMap = new TreeMap<String,Float>();
        digraph.put(node, tMap);
        updateDigraph();
    }

    public void addWeightedVertex(String startingNode, String endNode, Float weigth){
        if(!digraph.containsKey(endNode)){
            TreeMap<String, Float> eMap = new TreeMap<String,Float>();
            digraph.put(endNode, eMap);
        }
        if(!digraph.containsKey(startingNode)){
            TreeMap<String, Float> sMap = new TreeMap<String,Float>();
            sMap.put(endNode, weigth);
            digraph.put(startingNode, sMap);
        }else{
            digraph.get(startingNode).put(endNode, weigth);
        }
        updateDigraph();
    }

    public void removeWeightedVertex(String startingNode, String endNode){
        if(digraph.containsKey(startingNode) && digraph.containsKey(endNode)){
            digraph.get(startingNode).remove(endNode);
        }
        updateDigraph();
    }


    private int getNodeIndex(String node){
        int i = 0;
        for(String current : nodes){
            if(node.equals(current)){
                return i;
            }
            i++;
        }
        return -1;
    }

    private String getNodeByIndex(int index){
        int i = 0;
        for(String current : nodes){
            if(i == index){
                return current;
            }
            i++;
        }
        return "";
    }

    private void copyWeigthMatrix(){
        floydMatrix = new Float[nodesAmount][nodesAmount];
        for(int i = 0; i < nodesAmount; i++){
            for(int j = 0; j < nodesAmount; j++){
                floydMatrix[i][j] = weigthMatrix[i][j];
            }
        }
    }
    
    public String getGraphCenter(){
        updateDigraph();
        float[] eccentricity = new float[nodesAmount];
        float max = 0.0f;
        for(int j = 0; j < nodesAmount; j++){
            for(int i = 0; i < nodesAmount; i++){
                if(floydMatrix[i][j] > max){
                    max = floydMatrix[i][j];
                }
            }
            eccentricity[j] = max;
            max = 0;
        }

        float min = Float.POSITIVE_INFINITY;
        for(int k = 0; k < nodesAmount; k++){
            if(eccentricity[k] < min){
                min = eccentricity[k];
            }
        }

        for(int m = 0; m < nodesAmount; m++){
            if(eccentricity[m] == min){
                return getNodeByIndex(m);
            }
        }
        return null;

    }

    

}
