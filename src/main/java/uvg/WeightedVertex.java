package uvg;

public class WeightedVertex {
    private String firstNode;
    private String secondNode;
    private float weight;
    
    public WeightedVertex(String firstNode, String secondNode, float weight){
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

    public float getWeight(){
        return this.weight;
    }

    public boolean equals(WeightedVertex other){
        return firstNode.equals(other.getFirstNode()) && secondNode.equals(other.getSecondNode());
    }

    
}
