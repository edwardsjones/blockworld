import java.util.Stack;
import java.util.ArrayList;
import java.util.ArrayDeque;

public class Solver {

    public static void main(String[] args) {

        int gridSize = Integer.valueOf(args[0]);
        int problemSize = Integer.valueOf(args[1]);

        BlockWorld problem = new BlockWorld(gridSize, problemSize);
        problem.generateWorld();

        Solver solver = new Solver();
        solver.visualiseState(problem.getState());

        Node start = new Node(null, problem.getState(), 0, "START", 0); 
        Stack<Node> fringe = solver.expandDF(start, problem);

    }

    // to be used in depthFirstSearch as it returns a stack
    private Stack<Node> expandDF(Node parent, BlockWorld problem) {
    
        Stack<Node> successors = new Stack<Node>();
        ArrayList<String> possibleActions = problem.getActions();

        // state in the node is always used.
        for (String action : possibleActions) {
            Node child = new Node(parent, BlockWorld.move(action, parent.getState()), parent.getDepth() + 1, action, parent.getPathCost() + 1);
            //check if state already visited?
            successors.push(child); 
        } 

        return successors;

    }

    private void depthFirstSearch(BlockWorld problem, Stack fringe) {

    } 

    private void visualiseState(int[][] state) {
        int size = state.length;
        for (int i = 0; i < size; i++) {
            System.out.printf("|\t");
            for (int j = 0; j < size; j++) {
                System.out.printf(state[i][j] + "\t");
            }
            System.out.printf("|\n");
        } 
    }

}
