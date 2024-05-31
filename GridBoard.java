import java.util.Random;

public class GridBoard {

    private Tile[][] grid;

    public GridBoard(int size) {
        createBoard(size);
    }

    private void createBoard(int size) {
        // Define constants for the board dimensions and colors
        final int BLACK_TILES = 4;
        final int GREEN_TILES = 4;

        // Create a board filled with grey tiles
        Tile[][] board = new Tile[size][size];
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                board[row][col] = new GreyTile();
            }
        }

        Random random = new Random();
        int blackTilesPlaced = 0;
        int greenTilesPlaced = 0;

        // Place black tiles randomly
        while (blackTilesPlaced < BLACK_TILES) {
            int row = random.nextInt(size);
            int col = random.nextInt(size);
            if (board[row][col].getTileType().equals("Grey")) {
                board[row][col] = new BlackTile();
                blackTilesPlaced++;
            }
        }

        // Place green tiles randomly
        while (greenTilesPlaced < GREEN_TILES) {
            int row = random.nextInt(size);
            int col = random.nextInt(size);
            if (board[row][col].getTileType().equals("Grey")) {
                board[row][col] = new GreenTile();
                greenTilesPlaced++;
            }
        }

        this.grid = board;
    }

    // Optionally, add a getter for the grid if needed
    public Tile[][] getGrid() {
        return grid;
    }
}
