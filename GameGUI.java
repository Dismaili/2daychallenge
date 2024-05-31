import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

public class GameGUI extends JFrame {
    private Logic game;
    private JTextArea gameLog;
    private JButton rollButton;
    private JLabel redCarFuel;
    private JLabel blueCarFuel;
    private JPanel gridPanel;
    private JLabel[][] gridLabels;

    public GameGUI(int gridSize) {
        this.game = new Logic(gridSize);
        initializeComponents();
    }

    private void initializeComponents() {
        setTitle("Racing Game");
        setSize(600, 600);
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

        gridPanel = new JPanel();
        gridPanel.setLayout(new GridLayout(game.getGridSize(), game.getGridSize()));
        gridLabels = new JLabel[game.getGridSize()][game.getGridSize()];

        for (int i = 0; i < game.getGridSize(); i++) {
            for (int j = 0; j < game.getGridSize(); j++) {
                gridLabels[i][j] = new JLabel();
                gridLabels[i][j].setOpaque(true);
                gridLabels[i][j].setBorder(BorderFactory.createLineBorder(Color.BLACK));
                Tile tile = game.getTileAt(i, j);
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
                gridPanel.add(gridLabels[i][j]);
            }
        }

        add(gridPanel, BorderLayout.CENTER);

        updateGameLog("Welcome to the Racing Game!");
        updateGrid();
    }

    private void updateGameLog(String message) {
        gameLog.append(message + "\n");
    }

    private void updateFuelLabels() {
        redCarFuel.setText("Red Car Fuel: " + game.getRedCar().getFuel());
        blueCarFuel.setText("Blue Car Fuel: " + game.getBlueCar().getFuel());
    }

    private void updateGrid() {
        int[] redPos = game.getRedCar().getPosition();
        int[] bluePos = game.getBlueCar().getPosition();
        int gridSize = game.getGridSize();

        // Clear all grid labels
        for (int i = 0; i < gridSize; i++) {
            for (int j = 0; j < gridSize; j++) {
                gridLabels[i][j].setText("");
            }
        }

        // Update grid with both car positions and tile details
        for (int i = 0; i < gridSize; i++) {
            for (int j = 0; j < gridSize; j++) {
                Tile tile = game.getTileAt(i, j);
                switch (tile.getTileType()) {
                    case "Grey":
                        gridLabels[i][j].setText(String.valueOf(((GreyTile) tile).getFuelCost()));
                        break;
                    case "Black":
                        gridLabels[i][j].setText("B");
                        break;
                    case "Green":
                        gridLabels[i][j].setText("G");
                        break;
                }
                gridLabels[i][j].setOpaque(true);
                gridLabels[i][j].setBorder(BorderFactory.createLineBorder(Color.BLACK));
            }
        }

        // Update positions of red and blue cars
        if (redPos[0] >= 0 && redPos[0] < gridSize && redPos[1] >= 0 && redPos[1] < gridSize) {
            if (gridLabels[redPos[0]][redPos[1]].getText().isEmpty()) {
                gridLabels[redPos[0]][redPos[1]].setText(game.getRedCar().getSymbol());
            } else {
                gridLabels[redPos[0]][redPos[1]].setText(gridLabels[redPos[0]][redPos[1]].getText() + game.getRedCar().getSymbol());
            }
        } else {
            System.out.println("Error: Red car position out of bounds!");
        }

        if (bluePos[0] >= 0 && bluePos[0] < gridSize && bluePos[1] >= 0 && bluePos[1] < gridSize) {
            if (gridLabels[bluePos[0]][bluePos[1]].getText().isEmpty()) {
                gridLabels[bluePos[0]][bluePos[1]].setText(game.getBlueCar().getSymbol());
            } else {
                gridLabels[bluePos[0]][bluePos[1]].setText(gridLabels[bluePos[0]][bluePos[1]].getText() + game.getBlueCar().getSymbol());
            }
        } else {
            System.out.println("Error: Blue car position out of bounds!");
        }
    }

    private class RollButtonListener implements ActionListener {
        private boolean isRedTurn = true;

        @Override
        public void actionPerformed(ActionEvent e) {
            Car currentCar = isRedTurn ? game.getRedCar() : game.getBlueCar();
            game.playTurn();  // Update this line to reflect your game's turn logic
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
