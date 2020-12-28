package view;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import model.Chessboard;
import model.Point;
import service.ChessboardService;

public class GameView {
    private final Chessboard chessboard;

    @FXML private Button[][] buttons;
    private Point touch;

    @FXML private GridPane panel;
    @FXML private Label turn_label;

    public GameView() {
        chessboard = new Chessboard();
    }

    public void init() {
        buttons = new Button[9][9];
        touch = null;
        buttons[0][0] = createButton("");
        buttons[0][1] = createButton("a");
        buttons[0][2] = createButton("b");
        buttons[0][3] = createButton("c");
        buttons[0][4] = createButton("d");
        buttons[0][5] = createButton("e");
        buttons[0][6] = createButton("f");
        buttons[0][7] = createButton("g");
        buttons[0][8] = createButton("h");

        buttons[1][0] = createButton("8");
        buttons[2][0] = createButton("7");
        buttons[3][0] = createButton("6");
        buttons[4][0] = createButton("5");
        buttons[5][0] = createButton("4");
        buttons[6][0] = createButton("3");
        buttons[7][0] = createButton("2");
        buttons[8][0] = createButton("1");

        for (var i = 0; i < 8; i++) {
            for (var j = 0; j < 8; j++) {
                buttons[i + 1][j + 1] = createButton("");
                int finalJ = j;
                int finalI = i;
                var color = ((i + 1) % 2 ==1 ^ (j+1)%2==0 ? "#FFFFFF" : "#d7d7d7" );

                buttons[i + 1][j + 1].setStyle("-fx-font-size: 24px; -fx-background-color: " + color +";");
                buttons[i + 1][j + 1].setOnMouseClicked(event -> {
                    if (touch != null) {
                        var result = ChessboardService.executeStep(chessboard, touch.getX(), touch.getY(), finalJ, finalI);
                        if (result != null) {
                            Alert alert = new Alert(Alert.AlertType.INFORMATION, result, ButtonType.YES);
                            alert.setHeaderText("");
                            alert.showAndWait();
                        }
                        buttons[finalI + 1][finalJ + 1].setStyle("-fx-font-size: 24px; -fx-background-color: " + color +";");
                        buttons[touch.getY() + 1][touch.getX() + 1].setStyle(
                                "-fx-font-size: 24px;" +
                                        "-fx-background-color: " + ((touch.getY() + 1) % 2 ==1 ^ (touch.getX()+1)%2==0 ? "#FFFFFF" : "#d7d7d7" ) +";");
                        touch = null;
                        printChessboard(chessboard);
                        return;
                    }
                    if(chessboard.getFigure(finalJ, finalI) != null) {
                        if (touch == null) {
                            touch = new Point(finalJ, finalI);
                            buttons[finalI + 1][finalJ + 1].setStyle("-fx-border-color: #0000FF; -fx-font-size: 24px; -fx-background-color: " + color +";");
                        }
                    }
                });
            }
        }
        for (var i = 0; i < 9; i++) {
            for (var j = 0; j < 9; j++) {
                if(buttons[i][j] != null)
                    panel.add(buttons[i][j], j, i);
                else
                    panel.add(createButton(""), j, i);
            }
        }
        printChessboard(chessboard);
    }

    public void printChessboard(final Chessboard chessboard) {
        for (var i = 0; i < chessboard.getSize(); i++) {
            for (var j = 0; j < chessboard.getSize(); j++) {
                buttons[i + 1][j + 1].setText(FigureView.getImage(chessboard.getFigure(j, i)));
            }
        }
        turn_label.setText(chessboard.isWhiteMotion() ? "White turn" : "Black turn");
    }

    private Button createButton(String text) {
        var button = new Button(text);
        button.setMinSize(70, 70);
        button.setMaxSize(70, 70);
        button.setStyle("-fx-font-size: 24px");
        return button;
    }
}
