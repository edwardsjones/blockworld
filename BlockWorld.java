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
        
        for (int i = 0; i <= blocks; i++) {

            if (i == 0) {
                tiles[1][0] = i;
            } else if (i % 4 == 1) {
                placeTopLeft(i);
            } else if (i % 4 == 2) {
                placeTopRight(i);
            } else if (i % 4 == 3) {
                placeBottomRight(i);
            } else if (i % 4 == 0) {
                placeBottomLeft(i);
            }

        }

    }

    private void placeTopLeft(int number) {

        boolean blockPlaced = false;
        int i = 0;

        while (!blockPlaced) {
            if (tiles[0][i] == -1) {
                tiles[0][i] = number;
                blockPlaced = true; 
            } else {
                i++;
            }
        }

    }

    private void placeTopRight(int number) {

        boolean blockPlaced = false;
        int i = 0;

        while (!blockPlaced) {
            if (tiles[i][gridSize-1] == -1) {
                tiles[i][gridSize-1] = number;
                blockPlaced = true;
            } else {
                i++;
            }
        }

    }

    private void placeBottomRight(int number) {

        boolean blockPlaced = false;
        int i = gridSize - 1;

        while (!blockPlaced) {
            if (tiles[gridSize - 1][i] == -1) {
                tiles[gridSize - 1][i] = number;
                blockPlaced = true;
            } else {
                i--;
            }
        }

    }

    private void placeBottomLeft(int number) {

        boolean blockPlaced = false;
        int i = gridSize - 1;

        while (!blockPlaced) {
            if (tiles[i][0] == -1) {
                tiles[i][0] = number;
                blockPlaced = true;
            } else {
                i--;
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
        int[][] clone = new int[gridSize][gridSize];
        for (int i = 0; i < gridSize; i++) {
            clone[i] = tiles[i].clone(); 
        }
        return clone;
    }

    public ArrayList<String> getActions(int[][] state) {

        int agentX = -1;
        int agentY = -1;

        for (int i = 0; i < gridSize; i++) {
            for (int j = 0; j < gridSize; j++) {

                if (state[i][j] == 0) {
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
