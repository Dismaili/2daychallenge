import java.awt.Color;
import javax.swing.JOptionPane;

public class Logic {
    private Car redCar;
    private Car blueCar;
    private Tile[][] grid;
    private boolean isRedTurn = true;

    public Logic(int gridSize) {
        this.redCar = new Car(Color.RED, "Red", "R");
        this.blueCar = new Car(Color.BLUE, "Blue", "B");

        // Set different initial positions for the cars
        this.redCar.setPosition(gridSize - 1, 0);
        this.blueCar.setPosition(gridSize - 1,0);

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

    public void playTurn() {
        Car currentCar = isRedTurn ? redCar : blueCar;
        currentCar.executeTurn(currentCar);
        isRedTurn = !isRedTurn;
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
    



       
        private void solutionOutOfFuel(Car car) {
           
            Object[] options = {"Return to Starting position", "Lose dise rolls  in the game"};
            int choice = JOptionPane.showOptionDialog(null, "Out of Fuel! Choose one of these two options :",
                    "Out of Fuel", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
              
            if (choice == 0) {
                car.setPosition(grid.lenght-1,0);        
                car.setFuel(random.nextInt(120) + 1);    
            } 
            else {
                int freezeCounter = Integer.parseInt(JOptionPane.showInputDialog("Enter number of turns to lose (1-6):"));    
                car.setFreezeCounter(freezeCounter);                                                                                
                car.setFuel(car.getFuel() + freezeCounter * 20);                                                              
            }
        }

}
