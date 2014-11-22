public class Node {

    private Node parent;
    private int[][] state;
    private int depth, pathCost;

    public Node(Node parent, int[][] state, int depth, int pathCost) {
        this.parent = parent;
        this.state = state;
        this.depth = depth;
        this.pathCost = pathCost;
    }

}
