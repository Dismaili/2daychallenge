import java.awt.Color;

public class Logic {
    private Car redCar;
    private Car blueCar;
    private Tile[][] grid;

    public Logic(int gridSize) {
        this.redCar = new Car(Color.RED, "Red", "R");
        this.blueCar = new Car(Color.BLUE, "Blue", "B");

        // Set different initial positions for the cars
        this.redCar.setPosition(gridSize - 1, 0);
        this.blueCar.setPosition(gridSize - 1, 1);

        this.grid = new Tile[gridSize][gridSize];
        initializeGrid(gridSize);
    }

    private void initializeGrid(int gridSize) {
        GridBoard board = new GridBoard(gridSize);
        this.grid = board.getGrid();
    }

    public Car getRedCar() {
        return redCar;
    }

    public Car getBlueCar() {
        return blueCar;
    }

    public int getGridSize() {
        return grid.length;
    }

    public Tile getTileAt(int row, int col) {
        return grid[row][col];
    }

    public void playTurn(Car car) {
        car.updatePosition(grid.length);
        Tile currentTile = grid[car.getPosition()[0]][car.getPosition()[1]];
        if(currentTile.getTileType().equals("Black")) {
            car.setPosition(grid.length - 1, 0);
        } else if(currentTile.getTileType().equals("Green")){
            int addFuel = (int)(car.getFuel() * 0.5);
            car.setFuel(car.getFuel() + addFuel);
        }else{
            GreyTile greyTile = (GreyTile) currentTile;
            int fuelCost = greyTile.getFuelCost();
            System.out.println("Fuel cost: " + fuelCost);
            System.out.println("New fuel: " + (car.getFuel() - fuelCost));
            car.setFuel(car.getFuel() - fuelCost);
        }
    }
}