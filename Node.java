import java.util.ArrayList;

public class Node {

    private Node parent;
    private int[][] state;
    private int depth, pathCost;

    // Action is simply the action performed to get to this node. 
    private String action;

    public Node(Node parent, int[][] state, int depth, String action, int pathCost) {
        this.parent = parent;
        this.state = state;
        this.depth = depth;
        this.action = action;
        this.pathCost = pathCost;
    }

    public Node getParent() {
        return parent;
    }

    public String getActions() {
        return action;
    }

    public int[][] getState() {
        return state.clone();
    }

    public int getDepth() {
        return depth;
    }

    public int getPathCost() {
        return pathCost;
    }

}
