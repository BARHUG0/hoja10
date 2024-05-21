package uvg;

public class WeightedVertex {
    private String firstNode;
    private String secondNode;
    private int weight;
    
    public WeightedVertex(String firstNode, String secondNode, int weight){
        this.firstNode = firstNode;
        this.secondNode = secondNode;
        this.weight = weight;
    }

    public String getFirstNode() {
        return this.firstNode;
    }

    public String getSecondNode() {
        return this.secondNode;
    }

    public int getWeight(){
        return this.weight;
    }

    public boolean equals(WeightedVertex other){
        return firstNode.equals(other.getFirstNode()) && secondNode.equals(other.getSecondNode());
    }

}
