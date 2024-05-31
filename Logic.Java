import java.awt.Color;
import javax.swing.JOptionPane;
import java.util.Random;

public class Logic {
    private Car redCar;
    private Car blueCar;
    private Tile[][] grid;
    private boolean isRedTurn = true;
    private int previousDieRoll = 0;

    public Logic(int gridSize) {
        this.redCar = new Car(Color.RED, "Red", "🏎️");
        this.blueCar = new Car(Color.BLUE, "Blue", "🚎");

        // sets the initial positions for the cars
        this.redCar.setPosition(gridSize - 1, 0);
        this.blueCar.setPosition(gridSize - 1,0);

        // initializes the grid
        this.grid = new Tile[gridSize][gridSize];
        initializeGrid(gridSize);

        //shows insturcitosn on starts
        JOptionPane.showMessageDialog(
                        null,
                        "Welcome to The Racing Game! The objective of the game is to reach the top left corner of the grid.\n"
                        + "\nThe game is played by two players, Red and Blue. "
                        + "\nEach player takes turns rolling a dice and moving their car on the grid. "
                        + "\nThe grid is made up of different tiles, each with a different effect. "
                        + "\nGrey tiles will cost you fuel, Black tiles will send you back to the start, and Green tiles will give you extra fuel. "
                        + "\nThe game ends when one of the players reaches the top left corner of the grid. "
                        + "\nGood luck!"
        );
    }

    //initializes the grid 
    private void initializeGrid(int gridSize) {
        GridBoard board = new GridBoard(gridSize);
        this.grid = board.getGrid();
    }

    //returns the red car
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

    public int getPreviousDieRoll() {
        return previousDieRoll;
    }

    public void playTurn() {
        Car currentCar = isRedTurn ? redCar : blueCar;
        if(currentCar.hasFuel()){
            if(currentCar.isFrozen()){
                currentCar.decrementFreeze();
                isRedTurn = !isRedTurn;
                JOptionPane.showMessageDialog(
                        null,
                        "Player " + currentCar.getName() + " is frozen for " + currentCar.getFreezeCounter() + " more turns!"
                );
                return;
            }else{
                int diceRoll = rollDice();
                executeTurn(currentCar,diceRoll);
            }
        }else{
            onCarRunsOutOfFuel(currentCar);
            isRedTurn = !isRedTurn;
            return;
        }
        
        isRedTurn = !isRedTurn;
    }

    public void executeTurn(Car car,int dieRoll) {
        for(int i = 0;i < dieRoll; i++){
            if(!car.hasFuel()){
                onCarRunsOutOfFuel(car);
                return;
            }
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

            int[] newCarPosition = car.getPosition();
            if(newCarPosition[0] == 0 && newCarPosition[1] == 0){
                JOptionPane.showMessageDialog(
                        null,
                        "Player " + car.getName() + " has won the game!"
                );
                System.exit(0);
            }
        }
    }
    
       
    private void onCarRunsOutOfFuel(Car car) {
        
        Object[] buttons = {"Reset to start", "Lose dise rolls  in the game"};
        int selection = JOptionPane.showOptionDialog(
            null, 
            "You have no fuel in the tank! Please select one option :",
            "Out of Fuel",
            JOptionPane.DEFAULT_OPTION,
            JOptionPane.INFORMATION_MESSAGE,
            null,
            buttons,
            buttons[0]
        );
            
        if (selection == 0) {
            car.setPosition(grid.length-1,0);        
            car.setFuel((int)(Math.random() * 120) + 1);    
        } 
        else {
            int freezeCounter = Integer.parseInt(JOptionPane.showInputDialog("Enter number of turns to lose (1-6):"));    
            car.setFreeze(freezeCounter);                                                                                
            car.setFuel(car.getFuel() + freezeCounter * 20);                                                              
        }

        
    }

}
