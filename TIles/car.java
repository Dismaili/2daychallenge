import java.awt.Color;

public class car {
    private Color colour;
    private int row;
    private int col;
    private String name;
    private int turnsUntilPlay;
    private int fuel;
    private String symbol;


  public car(Color colour,String name, String symbol){
    this.name = name;//name of the car either blue or red
    this.symbol = symbol;//symbol for the gui
    this.fuel = 120;//how much fuel is in the car at the begining of the match
    this.row = 0;//the starting row of cars
    this.col = 0://the starting column of cars
    this.colour = colour;// colours of the cars either red or blue
    this.turnsUntilPlay = 0;// how many turns does the play choose to lose for him to gain the extra gas, this only occurs if the player has 0 fuel and needs to choose to lose rools from 1 to 6, each roll the player skips is worth 20 fuel.in total a max of 120 6 rolls.
  }   
  



  public Color getColour() {
    return colour;
}
public String getSymbol() {
    return symbol;
}
public int getFuel() {
    return fuel;
}
public String getName(){
    return name;
}

public int getCol() {
    return col;
}
    public int getRow() {
    return row;
}
public int getTurnsUntilPlay() {
    return turnsUntilPlay;
}
public void setFuel(int fuel) {
    this.fuel = fuel;
}
    public void setName(String name){
    this.name = name;
}
    public void setRow(int row) {
    this.row = row;
}
public void setCol(int col) {
    this.col = col;
}
public void setTurnsUntilPlay(int turnsUntilPlay) {
    this.turnsUntilPlay = turnsUntilPlay;
}
public boolean canMoveTheCar() {
    return turnsUntilPlay <= 0;//u can move the car which is another function in the game gui. If the player gains fuel and now can contineu playign the game. It basically means if ur not waiting for more fuel u can play
}

public void decrementTurnsUntilPlay() {
    if (turnsUntilPlay > 0) {
        turnsUntilPlay--;//its also for the game gui its connected with the turnsuntilplay basically if u have more than 1 turn left it subtracts by one and if u have 1 its goes to 0 makig it possible for u to play the next roll.
}//4-1=3 next turn 3-1=2 next turn 2-1=1 next turn 1-1=0 next turn u play. it basically prevents u for playing for the choosen amount of rolls.
}
}
