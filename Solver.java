import java.util.Stack;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.ArrayDeque;
import java.util.Collections;

public class Solver {

    private int problemSize; 
    private int gridSize;

    public static void main(String[] args) {

        int gridSize = Integer.valueOf(args[0]);
        int problemSize = Integer.valueOf(args[1]);

        BlockWorld problem = new BlockWorld(gridSize, problemSize);
        problem.generateWorld();

        Solver solver = new Solver();
        solver.setProblemSize(problemSize);
        solver.setGridSize(gridSize);
        solver.visualiseState(problem.getState());
    
        /*
        Stack<Node> stackFringe = new Stack<Node>();
        Solution solutionDFS = solver.depthFirstSearch(problem, stackFringe);
        solver.showSolution(solutionDFS);

        ArrayDeque<Node> queueFringe = new ArrayDeque<Node>();
        Solution solutionBFS = solver.breadthFirstSearch(problem, queueFringe);
        solver.showSolution(solutionBFS);
        */
        
        Solution solutionIDS = solver.iterativeDeepeningSearch(problem);
        solver.showSolution(solutionIDS);

    }

    private ArrayList<Node> expand(Node parent, BlockWorld problem) {
    
        ArrayList<Node> successors = new ArrayList<Node>();
        ArrayList<String> possibleActions = problem.getActions(parent.getState());
        Collections.shuffle(possibleActions);

        // state in the node is always used (problem should never change)
        for (String action : possibleActions) {
            Node child = new Node(parent, BlockWorld.move(action, parent.getState()), parent.getDepth() + 1, action, parent.getPathCost() + 1);
            successors.add(child); 
        } 

        return successors;

    }

    private Solution iterativeDeepeningSearch(BlockWorld problem) {
        int depth = 5;
        while (true) {
            Solution result = depthLimitedSearch(problem, depth);
            if (result.getStatus() != 2) 
                return result;
            depth++;
        }
    }

    private Solution depthLimitedSearch(BlockWorld problem, int limit) {
        Node start = new Node(null, problem.getState(), 0, "START", 0);
        Solution answer = depthRecursor(start, problem, limit);
        return answer;
    }

    //null object for fail, same for cutoff, new for solution
    private Solution depthRecursor(Node start, BlockWorld problem, int limit) {
        boolean cutoffReached = false;
        if (goalTest(start)) {
            Solution successSolution = new Solution(start, 1);
            return successSolution;
        } else if (start.getDepth() == limit) {
            Solution cutoffSolution = new Solution(start, 2);
            return cutoffSolution;
        } else {
            ArrayList<Node> successors = expand(start, problem);
            for (Node each : successors) {
                Solution result = depthRecursor(each, problem, limit);
                if (result.getStatus() == 2) {
                    cutoffReached = true;
                } else if (result.getStatus() != 0) {
                    return result;
                }
            }
        }
        if (cutoffReached) {
            Solution cutoffSol = new Solution(start, 2);
            return cutoffSol;
        } else {
            Solution failSolution = new Solution(start, 0);
            return failSolution;
        }
    }

    private Solution breadthFirstSearch(BlockWorld problem, ArrayDeque<Node> fringe) {
        
        Node start = new Node(null, problem.getState(), 0, "START", 0);
        ArrayList<int[][]> visited = new ArrayList<int[][]>();
        fringe.add(start);
        visited.add(start.getState());

        while (!fringe.isEmpty()) {

            Node current = fringe.poll();
            if (goalTest(current))
                return new Solution(current, 1);

            visited.add(current.getState());
            ArrayList<Node> next = expand(current, problem);
            for (Node each : next) {
                if (!contained(visited, each.getState())) 
                    fringe.add(each);
            }

        }

        return new Solution(start, 0);

    }

    private Solution depthFirstSearch(BlockWorld problem, Stack<Node> fringe) {

        Node start = new Node(null, problem.getState(), 0, "START", 0);
        ArrayList<int[][]> visited = new ArrayList<int[][]>();
        fringe.push(start);
        visited.add(start.getState());

        while (!fringe.isEmpty()) {

            Node current = fringe.pop();
            if (goalTest(current))
                return new Solution(current, 1);

            visited.add(current.getState());
            ArrayList<Node> next = expand(current, problem);
            for (Node each : next) {
                if (!contained(visited, each.getState())) 
                    fringe.push(each);
            }

        }

        return new Solution(start, 0);

    } 

    private void visualiseState(int[][] state) {

        for (int i = 0; i < gridSize; i++) {
            System.out.printf("|\t");
            for (int j = 0; j < gridSize; j++) {
                System.out.printf(state[i][j] + "\t");
            }
            System.out.printf("|\n");
        } 
        System.out.printf("|\n");
    }

    private boolean goalTest(Node node) {
        int[][] state = node.getState(); 
        for (int i = 0; i < gridSize; i++) {
            if (state[gridSize-1][i] >= 1) {
                int counter = 0;
                for (int j = gridSize - 1; j >= (gridSize - problemSize); j--) {
                    if (state[j][i] > 0) counter++;       
                }
                if (counter == problemSize) return true;
            }
        }
        return false;
    }

    private boolean contained(ArrayList<int[][]> visited, int[][] testState) {
        for (int[][] each : visited) {
            if (Arrays.deepEquals(each, testState)) {
                return true;
            }
        }
        return false;
    }

    private void showSolution(Solution solution) {
        Node solved = solution.getNode();
        System.out.println("Cost of solution: " + solved.getPathCost());
        System.out.println("Depth of node: " + solved.getDepth());
        Stack<String> winningActions = new Stack<String>();
        Node current = solved;
        do {
            winningActions.push(current.getAction());
            if (current.getDepth() != 0) 
                current = current.getParent();
        } while (!current.getAction().equals("START")); 
        
        System.out.printf("Moves from start to finish:\t");
        while (!winningActions.isEmpty()) {
            System.out.printf(winningActions.pop() + "\t");
        }
        System.out.printf("|\n");
    }

    private void setProblemSize(int problemSize) {
        this.problemSize = problemSize;
    }

    private void setGridSize(int gridSize) {
        this.gridSize = gridSize;
    }

}
