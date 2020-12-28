package service;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import model.Chessboard;
import model.FigureType;
import model.Point;

public class ChessboardService {
    public static String executeStep(final Chessboard chessboard, int x1, int y1, int x2, int y2){
        if (chessboard.getFigure(x1, y1) == null) {
            return "Selected empty cell";
        }
        if ((chessboard.getFigure(x1, y1).isWhite() && !chessboard.isWhiteMotion())
                || (!chessboard.getFigure(x1, y1).isWhite() && chessboard.isWhiteMotion())) {
            return "It's not your turn";
        }
        if (!FigureMotionService.isMoveValid(chessboard, chessboard.getFigure(x1, y1), x2, y2)) {
            return "This piece doesn't move like that";
        }
        if(chessboard.getFigure(x2, y2) != null) {
            if (chessboard.getFigure(x1, y1).isWhite()
                    && chessboard.getFigure(x2, y2).isWhite()) {
                return "White cannot land on white";
            }
            if (!chessboard.getFigure(x1, y1).isWhite()
                    && !chessboard.getFigure(x2, y2).isWhite()) {
                return "Black cannot land on black";
            }
        }

        var figure = chessboard.getFigure(x2, y2);
        chessboard.setFigure(chessboard.getFigure(x1, y1), x2, y2);
        chessboard.getFigure(x2, y2).setPosition(new Point(x2, y2));
        chessboard.setFigure(null, x1, y1);
        chessboard.setWhiteMotion(!chessboard.isWhiteMotion());

        if(figure != null && figure.getFigureType() == FigureType.KING) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION, chessboard.isWhiteMotion() ? "Black is win" : "White is win", ButtonType.YES);
            alert.setHeaderText("");
            alert.showAndWait();
            chessboard.resetBoard();
        }
        return null;
    }
}
