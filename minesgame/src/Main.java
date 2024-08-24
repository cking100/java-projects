import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.util.Random;

public class Main extends Application {
    private static final int GRID_SIZE = 11;
    private static final int NUM_MINES = 10;
    private Button[][] gridButtons = new Button[GRID_SIZE][GRID_SIZE];
    private boolean[][] mines = new boolean[GRID_SIZE][GRID_SIZE];
    private boolean[][] revealed = new boolean[GRID_SIZE][GRID_SIZE];
    private boolean extraLifeUsed = false;
    private int score = 0;
    private Label scoreLabel = new Label("Score: 0");

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        GridPane gridPane = new GridPane();
        HBox powerUpBox = new HBox();
        BorderPane root = new BorderPane();

      
        initializeGrid();
        initializePowerUps(powerUpBox);
        setupGridButtons(gridPane);

        root.setCenter(gridPane);
        root.setBottom(powerUpBox);
        root.setTop(scoreLabel);

        Scene scene = new Scene(root, 450, 500);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Mines game");
        primaryStage.show();
    }

    private void initializeGrid() {
        Random random = new Random();

        extraLifeUsed = false;
        score = 0;
        scoreLabel.setText("Score: 0"); 

        for (int i = 0; i < GRID_SIZE; i++) {
            for (int j = 0; j < GRID_SIZE; j++) {
                mines[i][j] = false;
                revealed[i][j] = false;
            }
        }

        for (int i = 0; i < NUM_MINES; i++) {
            int row, col;
            do {
                row = random.nextInt(GRID_SIZE);
                col = random.nextInt(GRID_SIZE);
            } while (mines[row][col]);

            mines[row][col] = true;
        }
    }

    private void initializePowerUps(HBox powerUpBox) {
        Button mineDetectorButton = new Button("Mine Detector");
        mineDetectorButton.setOnAction(e -> useMineDetector());

        Button extraLifeButton = new Button("Extra Life");
        extraLifeButton.setOnAction(e -> useExtraLife());

        powerUpBox.getChildren().addAll(mineDetectorButton, extraLifeButton);
    }

    private void setupGridButtons(GridPane gridPane) {
        gridPane.getChildren().clear(); 

        for (int row = 0; row < GRID_SIZE; row++) {
            for (int col = 0; col < GRID_SIZE; col++) {
                Button button = new Button();
                button.setPrefSize(50, 40);
                gridButtons[row][col] = button;

                
                int finalRow = row;
                int finalCol = col;
                button.setOnAction(e -> handleButtonClick(finalRow, finalCol));

                gridPane.add(button, col, row);
            }
        }
    }

    private void handleButtonClick(int row, int col) {
        if (revealed[row][col]) return;

        if (mines[row][col]) {
            if (!extraLifeUsed) {
                extraLifeUsed = true;
                showAlert("Boom!", "You hit a mine, but your extra life saved you!");
            } else {
                revealMine(row, col);
                showAlert("Game Over", "You hit a mine! Game over!");
                disableGrid();
            }
        } else {
            revealSafeCell(row, col);
            score++; 
            updateScore();
            if (checkWinCondition()) {
                showAlert("Congratulations!", "You won the game!");
                disableGrid();
            }
        }
    }

    private void revealMine(int row, int col) {
        gridButtons[row][col].setStyle("-fx-background-color: red;");
        revealed[row][col] = true;
    }

    private void revealSafeCell(int row, int col) {
        if (revealed[row][col]) return;

        revealed[row][col] = true;
        gridButtons[row][col].setStyle("-fx-background-color: green;");
        gridButtons[row][col].setDisable(true);
    }

    private void useMineDetector() {
        boolean mineDetected = false;

        for (int row = 0; row < GRID_SIZE; row++) {
            for (int col = 0; col < GRID_SIZE; col++) {
                if (mines[row][col] && !revealed[row][col]) {
                    gridButtons[row][col].setStyle("-fx-background-color: yellow;");
                    gridButtons[row][col].setText("?");
                    mineDetected = true;
                    break;
                }
            }
            if (mineDetected) break;
        }

        if (!mineDetected) {
            showAlert("Mine Detector", "No more hidden mines detected.");
        }
    }

    private void useExtraLife() {
        if (!extraLifeUsed) {
            extraLifeUsed = true;
            showAlert("Extra Life", "You have activated the extra life! You can now survive one mine hit.");
        } else {
            showAlert("Extra Life", "You have already used your extra life.");
        }
    }

    private boolean checkWinCondition() {
        for (int row = 0; row < GRID_SIZE; row++) {
            for (int col = 0; col < GRID_SIZE; col++) {
                if (!mines[row][col] && !revealed[row][col]) {
                    return false;
                }
            }
        }
        return true;
    }

    private void disableGrid() {
        for (int row = 0; row < GRID_SIZE; row++) {
            for (int col = 0; col < GRID_SIZE; col++) {
                gridButtons[row][col].setDisable(true);
            }
        }
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void updateScore() {
        scoreLabel.setText("Score: " + score);
    }
}
