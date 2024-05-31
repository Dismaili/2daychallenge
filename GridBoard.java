import java.util.Random;

public class GridBoard {

    private Tile[][] grid;

    public GridBoard(int size) {
        createBoard(size);
    }

    private void createBoard(int size) {//TODO set this to a percentage please :D
        
        final int BLACK_TILES = (int)(size * size * 0.0);
        final int GREEN_TILES = (int)(size * size * 0.15);
        // creates the number of black and green tiles which are a total of 30%, 15% are green and the other 15% are black.
        // Create a simple board of only grey tiles so its all with a random 1-3 value.
        Tile[][] board = new Tile[size][size];
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                board[row][col] = new GreyTile();
            }
        }

        Random random = new Random();
        int blackTilesPlaced = 0;
        int greenTilesPlaced = 0;

        // Places 15% of the board to have black tiles in every row and every col.
        while (blackTilesPlaced < BLACK_TILES) {
            int row = random.nextInt(size);
            int col = random.nextInt(size);
            if((row != 0 && col != 0) && (row != size-1 && col != 0)){
                if (board[row][col].getTileType().equals("Grey")) {
                    board[row][col] = new BlackTile();
                    blackTilesPlaced++;
                }
            }
        }

        // Places green tiles randomly to a sum of it being 15% of all tiles on the board.
        while (greenTilesPlaced < GREEN_TILES) {
            int row = random.nextInt(size);
            int col = random.nextInt(size);
            if((row != 0 && col != 0) && (row != size-1 && col != 0)){
                if (board[row][col].getTileType().equals("Grey")) {
                    board[row][col] = new GreenTile();
                    greenTilesPlaced++;
                }
            }
        }

        this.grid = board;
    }

    public Tile[][] getGrid() {
        return grid;
    }
}
