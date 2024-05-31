import java.util.Random;

public class GreyTile implements Tile {// grants it all the tile atributes and also checks the tile type so it can be implemented in the game.
    private int fuelCost;

    public GreyTile() {
        Random rand = new Random();
        this.fuelCost = rand.nextInt(3) + 1; //Each gray tyle has a random value this goes from 1 to 3. So u will lose from 1 to 3 numbers of fuel,also this means that the gray tiles will be combined together with each rooll if u roll a 1,3,3 and land on a 2. u lose 9 fuel basically the dice u roll combiens the total of all the gray squares u have passed.
    }

    public int getFuelCost() {
        return fuelCost;
    }// the fuel cost of the gray tile
    
    @Override
    public String getTileType() {
        return "Grey";
    }
}// gets the type of tile and then send it back to make the grid
