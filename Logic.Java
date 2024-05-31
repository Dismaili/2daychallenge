import java.awt.Color;
import javax.swing.JOptionPane;

public class Logic {
    private Car redCar;
    private Car blueCar;
    private Tile[][] grid;
    private boolean isRedTurn = true;
    private int previousDieRoll = 0;

    public Logic(int gridSize) {
        this.redCar = new Car(Color.RED, "Red", "🏎️");
        this.blueCar = new Car(Color.BLUE, "Blue", "🚎");

        // Sets the initial positions for the cars
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

    public int rollDice(){
        int rollValue = (int)(Math.random() * 6) + 1;
        System.out.println("Dice roll: " + rollValue);
        this.previousDieRoll = rollValue;

        return rollValue;
    }

    public void playTurn() {
        Car currentCar = isRedTurn ? redCar : blueCar;
        if(currentCar.hasFuel()){
            if(currentCar.isFrozen()){
                currentCar.decrementFreeze();
                isRedTurn = !isRedTurn;
                return;
            }else{
                int diceRoll = rollDice();
                executeTurn(currentCar,diceRoll);
            }
        }
        
        isRedTurn = !isRedTurn;
    }

    public void executeTurn(Car car,int dieRoll) {
        for(int i = 0;i < dieRoll; i++){
            System.out.println("Ran for loop #" + i+1);
            car.updatePosition(grid.length);
            Tile currentTile = grid[car.getPosition()[0]][car.getPosition()[1]];
            if(i == dieRoll - 1){
                if(currentTile.getTileType().equals("Black")) {
                car.setPosition(grid.length - 1, 0);
                }else if(currentTile.getTileType().equals("Green")){
                    int addFuel = (int)(car.getFuel() * 0.5);
                    car.setFuel(car.getFuel() + addFuel);
                }
            }else{
                if(currentTile.getTileType().equals("Grey")){
                    GreyTile greyTile = (GreyTile) currentTile;
                    int fuelCost = greyTile.getFuelCost();
                    car.setFuel(car.getFuel() - fuelCost);
                }
            }
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
