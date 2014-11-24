public class Solution { 

    private Node goalNode;
    private int status;

    public Solution(Node goalNode, int status) {
        this.goalNode = goalNode;
        this.status = status;    
    }

    public Node getNode() {
        return goalNode;
    }

    // 0 = failure, 1 = success, 2 = cutoff
    public int getStatus() {
        return status;
    }

}
