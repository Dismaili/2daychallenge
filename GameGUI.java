import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

public class GameGUI extends JFrame {//all the need componenets of the game
    private Logic game;//the logic of how the game functions
    private JTextArea gameLog;//component for loging the numbers of the car
    private JButton rollButton;
    private JLabel redCarFuel;// fuel fo the red car
    private JLabel blueCarFuel;//fuel of the blue car
    private JPanel gridPanel;//the squares which where the squares land basically the outer outline.
    private JLabel[][] gridLabels;// the inside of the  squares

    public GameGUI(int gridSize) {
        this.game = new Logic(gridSize);
        initializeComponents();
    }

    private void initializeComponents() { //sets the setting of the entire systme, basically the name,size,how the inputs effect the window.
        setTitle("Racing Game");
        setSize(1920, 1080);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        gameLog = new JTextArea();
        gameLog.setEditable(false);
        add(new JScrollPane(gameLog), BorderLayout.EAST);

        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(new GridLayout(1, 3));

        rollButton = new JButton("Roll Die");
        rollButton.addActionListener(new RollButtonListener());
        controlPanel.add(rollButton);

        redCarFuel = new JLabel("Red Car Fuel: " + game.getRedCar().getFuel());
        controlPanel.add(redCarFuel);

        blueCarFuel = new JLabel("Blue Car Fuel: " + game.getBlueCar().getFuel());
        controlPanel.add(blueCarFuel);
       
        add(controlPanel, BorderLayout.SOUTH);

        gridPanel = new JPanel();//creats the window that the game is shown in.
        gridPanel.setLayout(new GridLayout(game.getGridSize(), game.getGridSize()));
        gridLabels = new JLabel[game.getGridSize()][game.getGridSize()];
            //populates the window with grid shaped squares which all contain a color and text. The outline and inline.
        for (int i = 0; i < game.getGridSize(); i++) {
            for (int j = 0; j < game.getGridSize(); j++) {
                gridLabels[i][j] = new JLabel();
                gridLabels[i][j].setOpaque(true);//makes the background of the square visiable. 
                gridLabels[i][j].setBorder(BorderFactory.createLineBorder(Color.BLACK));//add a black border to each square.
                gridLabels[i][j].setHorizontalAlignment(SwingConstants.CENTER);//centres the text in the boxes.
                Tile tile = game.getTileAt(i, j);//gets tile insnance at that position, gray cell, black cell.
                if(i == game.getGridSize() - 1 && j == 0){//if its the first square then it has a white color and a flag to signife the start.
                    gridLabels[i][j].setText("🚩");
                    gridLabels[i][j].setBackground(Color.WHITE);
                }
                else if(i == 0 && j == 0){
                    gridLabels[i][j].setText("🏁");// this is the end color its white and has a finish flag.
                    gridLabels[i][j].setBackground(Color.WHITE);
                }else{
                    switch (tile.getTileType()) {//switches the properties of the tile depending on the color.
                    case "Grey":
                        gridLabels[i][j].setBackground(Color.LIGHT_GRAY);
                        GreyTile greyTile = (GreyTile) tile;
                        gridLabels[i][j].setText(String.valueOf(greyTile.getFuelCost()));
                        break;
                    case "Black":
                        gridLabels[i][j].setBackground(Color.BLACK);
                        gridLabels[i][j].setText("B");
                        break;
                    case "Green":
                        gridLabels[i][j].setBackground(Color.GREEN);
                        gridLabels[i][j].setText("G");
                        break;
                }
                }
                gridPanel.add(gridLabels[i][j]);
            }
        }

        add(gridPanel, BorderLayout.CENTER);//  centers everythhing and puts together everything so its clean.

        updateGameLog("Welcome to the Racing Game!");
        updateGrid();
    }

    private void updateGameLog(String message) {
        gameLog.append(message + "\n");
    }

    private void updateFuelLabels() {
        redCarFuel.setText("Red Car Fuel: " + game.getRedCar().getFuel());
        blueCarFuel.setText("Blue Car Fuel: " + game.getBlueCar().getFuel());
    }//updates fuel labels and changes the visible fuel amount after each turn.

    private void updateGrid() {//changes the postions of the cars in the grid.
        int[] redPos = game.getRedCar().getPosition();
        int[] bluePos = game.getBlueCar().getPosition();
        int gridSize = game.getGridSize();

        // Clear all grid labels
        for (int i = 0; i < gridSize; i++) {
            for (int j = 0; j < gridSize; j++) {
                gridLabels[i][j].setText("");
            }
        }


        for (int i = 0; i < gridSize; i++) {
            for (int j = 0; j < gridSize; j++) {
                Tile tile = game.getTileAt(i, j);
                gridLabels[i][j].setHorizontalAlignment(SwingConstants.CENTER);
                if(i == game.getGridSize() - 1 && j == 0){
                    gridLabels[i][j].setText("🚩");
                    gridLabels[i][j].setBackground(Color.WHITE);
                }
                else if(i == 0 && j == 0){
                    gridLabels[i][j].setText("🏁");
                    gridLabels[i][j].setBackground(Color.WHITE);
                }else{
                    switch (tile.getTileType()) {
                    case "Grey":
                        gridLabels[i][j].setBackground(Color.LIGHT_GRAY);
                        GreyTile greyTile = (GreyTile) tile;
                        gridLabels[i][j].setText(String.valueOf(greyTile.getFuelCost()));
                        break;
                    case "Black":
                        gridLabels[i][j].setBackground(Color.BLACK);
                        gridLabels[i][j].setText("B");
                        break;
                    case "Green":
                        gridLabels[i][j].setBackground(Color.GREEN);
                        gridLabels[i][j].setText("G");
                        break;
                }
                }
                gridLabels[i][j].setOpaque(true);
                gridLabels[i][j].setBorder(BorderFactory.createLineBorder(Color.BLACK));
            }
        }

        if (redPos[0] >= 0 && redPos[0] < gridSize && redPos[1] >= 0 && redPos[1] < gridSize) {
            if (gridLabels[redPos[0]][redPos[1]].getText().isEmpty()) {
                gridLabels[redPos[0]][redPos[1]].setText(game.getRedCar().getSymbol());
            } else {
                gridLabels[redPos[0]][redPos[1]].setText(gridLabels[redPos[0]][redPos[1]].getText() + game.getRedCar().getSymbol());
            }
        } 

        if (bluePos[0] >= 0 && bluePos[0] < gridSize && bluePos[1] >= 0 && bluePos[1] < gridSize) {
            if (gridLabels[bluePos[0]][bluePos[1]].getText().isEmpty()) {
                gridLabels[bluePos[0]][bluePos[1]].setText(game.getBlueCar().getSymbol());
            } else {
                gridLabels[bluePos[0]][bluePos[1]].setText(gridLabels[bluePos[0]][bluePos[1]].getText() + game.getBlueCar().getSymbol());
            }
        } 
    }

    private class RollButtonListener implements ActionListener {
        private boolean isRedTurn = true;

        @Override
        public void actionPerformed(ActionEvent e) {// this is the player turn such as moving the player,dice turn and updates the log at side of the screen.
            Car currentCar = isRedTurn ? game.getRedCar() : game.getBlueCar();
            game.playTurn();
            updateGameLog("Player " + currentCar.getName() + " rolled a " + game.getPreviousDieRoll());
            updateGameLog((isRedTurn ? "Red" : "Blue") + " car moved to position " + Arrays.toString(currentCar.getPosition()) +
                    " with " + currentCar.getFuel() + " fuel left.");
            updateFuelLabels();
            updateGrid();
            isRedTurn = !isRedTurn;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            GameGUI gui = new GameGUI(10);
            gui.setVisible(true);
        });
    }
}
