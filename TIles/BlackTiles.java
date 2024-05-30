public class BlackTile extends Tiles {

    public BlackTile(int row, int col) {
        super(row, col);
    }

    @Override
    public String getTileType() {
        return "Black";
    }
}
