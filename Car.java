import java.awt.Color;

public class Car {
    private Color color;
    private String name;
    private String symbol;
    private int[] position;
    private int fuel;
    private int freezeCounter = 0;

    public Car(Color color, String name, String symbol) {
        this.color = color;
        this.name = name;
        this.symbol = symbol;
        this.position = new int[2];
        this.fuel = 120;  // Initial fuel amount
    }

    public int[] getPosition() {
        return position;
    }

    public void setPosition(int row, int col) {
        this.position[0] = row;
        this.position[1] = col;
    }

    public void setRow(int row) {
        this.position[0] = row;
    }

    public void setCol(int col) {
        this.position[1] = col;
    }

    public int getFuel() {
        return fuel;
    }

    public void setFuel(int fuel) {
        if(fuel < 0){
            this.fuel = 0;
        }
        else if(fuel > 240){
            this.fuel = 240;
        }else{
            this.fuel = fuel;
        }
    }

    public void move(int row, int col) {
        this.position[0] = row;
        this.position[1] = col;
    }

    public void updatePosition(int gridSize) {
        boolean goLeft = (this.position[0] == 0) || (this.position[0] % 2 != 0);
        if (goLeft) {
            moveLeft(gridSize);
        } else {
            moveRight(gridSize);
        }
    }

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
    }

    private void moveRight(int gridSize) {
        if (this.position[1] < gridSize - 1) {
            this.position[1]++;
        } else if (this.position[0] > 0) {
            this.position[1] = gridSize - 1;
            this.position[0]--;
        }
    }
}
