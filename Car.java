import java.awt.Color;

public class Car {
    private Color color;//color of the car of the players can be either blue or red.
    private String name;// name of the cars either blue or red.
    private String symbol;// symbols used to represent the car in the gui, need for the gui.
    private int[] position;// where the car is at the moment its on the x and y position of the car.
    private int fuel;// how much fuel does the car posses 
    private int freezeCounter = 0;// how many turns does the play choose to lose for him to gain the extra gas, this only occurs if the player has 0 fuel and needs to choose to lose rools from 1 to 6, each roll the player skips is worth 20 fuel.in total a max of 120 6 rolls.

    public Car(Color color, String name, String symbol) {// car constructor.
        this.color = color;
        this.name = name;
        this.symbol = symbol;
        this.position = new int[2];// the 2d array of the car the position of the car.
        this.fuel = 120;  // Starting amount of fuel that the cars have
    }

    public int[] getPosition() {
        return position;
    }

    public void setPosition(int row, int col) {
        this.position[0] = row;
        this.position[1] = col;
    }// the inital starying poistion of the car in which row and colom.

    public void setRow(int row) {
        this.position[0] = row;
    }

    public void setCol(int col) {
        this.position[1] = col;
    }

    public int getFuel() {
        return fuel;
    }

    public boolean hasFuel(){
        return fuel > 0;
    }

    public String getSymbol() {
        return symbol;
    }

    public String getName(){
        return name;
    }

    public int getFreezeCounter(){
        return freezeCounter;
    }

    public boolean isFrozen(){
        return freezeCounter > 0;
    }

    public void setFreeze(int freezeCounter){
        this.freezeCounter = freezeCounter;
    }

    public void decrementFreeze(){
        if(freezeCounter > 0){
            freezeCounter--;
        }
    }

    public void setFuel(int fuel) {
        if(fuel < 0){
            this.fuel = 0;
        }// sets the fuel to a min cap of it being 0
        else if(fuel > 240){
            this.fuel = 240;
        }else{
            this.fuel = fuel;
        }// sets the max cap of the fuel of 240 this is for realistic purposes as u would have in normal games a reward for ur luck in the game, this is however more a logical than a actuall situation that can occur in a 10x10 grid.
    }

    public void move(int row, int col) {
        this.position[0] = row;
        this.position[1] = col;
    }// update the postion of the car in the 2d array.

    // This method checks if the car should go left or right on the screen depending on the row it is on
    private boolean goLeft(int gridSize){
        if (this.position[0] == 0) {
            return true;
        }
        return (gridSize % 2 == 0) 
                ? ((this.position[0] % 2 == 0) && (this.position[0] != gridSize - 1))
                : (this.position[0] % 2 != 0);
    }

    public void updatePosition(int gridSize) {
        boolean goLeft = goLeft(gridSize);// checks if the car should go left or right on the screen.
        System.out.println("goLeft: " + goLeft);
        System.out.println("Position: " + this.position[0] + ", " + this.position[1]);
        if (goLeft) {
            moveLeft(gridSize);
        } else {
            moveRight(gridSize);
        }
    }// the logic behind wether a car should move left on the screen or right.

    private void moveLeft(int gridSize) {

        if (this.position[1] > 0) {
            this.position[1]--;
        } else if (this.position[0] > 0) {
            if (this.position[0] == 1) {
                this.position[1] = gridSize - 1;
                this.position[0] = 0;
            } else {
                this.position[1] = 0;
                this.position[0]--;
            }
        }
    }// if it needs to move left it basically says that it can no longer move right so then it should go up by on row and then goes left.

    private void moveRight(int gridSize) {
        if (this.position[1] < gridSize - 1) {
            this.position[1]++;
        } else if (this.position[0] > 0) {
            this.position[1] = gridSize - 1;
            this.position[0]--;
        }
    }
}// if it needs to move right it basically says that it can no longer move left so then it should go up by on row and then goes right.
