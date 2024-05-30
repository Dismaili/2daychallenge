import java.util.Random;

public class GridBoard {

    public static void main(String[] args) {
        // Define constants for the board dimensions and colors
        final int SIZE = 10;
        final int BLACK_TILES = 4;
        final int GREEN_TILES = 4;

        // Create a board filled with grey tiles
        Tiles[][] board = new Tiles[SIZE][SIZE];
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                board[row][col] = new GreyTile();
            }
        }

        Random random = new Random();
        int blackTilesPlaced = 0;
        int greenTilesPlaced = 0;

        // Place black tiles randomly
        while (blackTilesPlaced < BLACK_TILES) {
            int row = random.nextInt(SIZE);
            int col = random.nextInt(SIZE);
            if (board[row][col].getColor().equals("Grey")) {
                board[row][col] = new BlackTile();
                blackTilesPlaced++;
            }
        }

        // Place green tiles randomly
        while (greenTilesPlaced < GREEN_TILES) {
            int row = random.nextInt(SIZE);
            int col = random.nextInt(SIZE);
            if (board[row][col].getColor().equals("Grey")) {
                board[row][col] = new GreenTile();
                greenTilesPlaced++;
            }
        }

        // Print the board
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                System.out.print(board[row][col].getColor() + "\t");
            }
            System.out.println();
        }
    }
}
