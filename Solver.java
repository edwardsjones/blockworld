import java.util.ArrayList;

public class Solver {

    public static void main(String[] args) {

        int gridSize = Integer.valueOf(args[0]);
        int problemSize = Integer.valueOf(args[1]);

        BlockWorld problem = new BlockWorld(gridSize, problemSize);
        problem.generateWorld();
        problem.getActions();

    }

}
