import java.util.Random;

public class GreyTile implements Tile {
    private int fuelCost;

    public GreyTile() {
        Random rand = new Random();
        this.fuelCost = rand.nextInt(3) + 1; // Random fuel cost between 1 and 3
    }

    public int getFuelCost() {
        return fuelCost;
    }
    
    @Override
    public String getTileType() {
        return "Grey";
    }
}
