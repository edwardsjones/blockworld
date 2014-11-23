import java.util.Random;
import java.util.ArrayList;

public class BlockWorld {

    private int[][] tiles;
    private int gridSize;
    private int blocks;

    public BlockWorld(int gridSize, int blocks) {
        this.gridSize = gridSize;
        this.blocks = blocks;
    }
    
    public void generateWorld() {

        tiles = new int[gridSize][gridSize];

        for (int i = 0; i < gridSize; i++) {
            for (int j = 0; j < gridSize; j++) {
                tiles[i][j] = -1;
            }
        }
        
        Random rng = new Random();

        for (int i = 0; i <= blocks; i++) {

            boolean generating = true;

            while (generating) {

                int first = rng.nextInt(gridSize);
                int second = rng.nextInt(gridSize); 

                if (tiles[first][second] == -1) {
                    tiles[first][second] = i;
                    generating = false;
                }

            }

        }

    }

    public static int[][] move(String action, int[][] state) {

        int agentRow = -1;
        int agentCol = -1;
        int length = state.length;

        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {

                if (state[i][j] == 0) {
                    agentRow = i;
                    agentCol = j;
                }        

            }
        }

        int store;

        if (action.equals("UP")) {
            store = state[agentRow-1][agentCol];
            state[agentRow-1][agentCol] = 0;
            state[agentRow][agentCol] = store;            
        } else if (action.equals("DOWN")) {
            store = state[agentRow+1][agentCol];
            state[agentRow+1][agentCol] = 0;
            state[agentRow][agentCol] = store;            
        } else if (action.equals("LEFT")) {
            store = state[agentRow][agentCol-1];
            state[agentRow][agentCol-1] = 0;
            state[agentRow][agentCol] = store;            
        } else if (action.equals("RIGHT")) {
            store = state[agentRow][agentCol+1];
            state[agentRow][agentCol+1] = 0;
            state[agentRow][agentCol] = store;            
        } 

        return state;

    }

    public int getGridSize() {
        return gridSize;
    }
    
    public int getProblemSize() {
        return blocks;
    }

    public int[][] getState() {
        int[][] clone = tiles.clone();
        return clone;
    }

    public ArrayList<String> getActions() {

        int agentX = -1;
        int agentY = -1;

        for (int i = 0; i < gridSize; i++) {
            for (int j = 0; j < gridSize; j++) {

                if (tiles[i][j] == 0) {
                    agentX = i;
                    agentY = j;
                }        

            }
        }

        int edge = gridSize - 1;
        ArrayList<String> actions = new ArrayList<String>(0);

        if (agentX != 0) actions.add("UP");
        if (agentX != edge) actions.add("DOWN");

        if (agentY != 0) actions.add("LEFT");
        if (agentY != edge) actions.add("RIGHT");

        return actions;

    }
}
