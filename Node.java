public class Node {

    private Node parent;
    private int[][] state;
    private int depth, pathCost;
    private String[] actions;

    public Node(Node parent, int[][] state, int depth, String[] actions) {
        this.parent = parent;
        this.state = state;
        this.depth = depth;
        this.actions = actions;
    }

    public Node getParent() {
        return parent;
    }

}
