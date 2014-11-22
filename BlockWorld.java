import java.util.Random;

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

        for (int i = 0; i < blocks; i++) {

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

}
