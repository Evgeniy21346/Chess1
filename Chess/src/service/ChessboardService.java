package service;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import model.Chessboard;
import model.Figure;
import model.FigureType;
import model.Point;

public class ChessboardService {
    public static String executeStep(final Chessboard chessboard, int x1, int y1, int x2, int y2){
        if (chessboard.getFigure(x1, y1) == null) {
            return "Selected empty cell";
        }
        if ((chessboard.getFigure(x1, y1).isWhite() && !chessboard.isWhiteMove())
                || (!chessboard.getFigure(x1, y1).isWhite() && chessboard.isWhiteMove())) {
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
        chessboard.setWhiteMove(!chessboard.isWhiteMove());

        if(figure != null && figure.getFigureType() == FigureType.KING) {
            chessboard.setComplete(true);
        }
        return null;
    }

    public static void resetBoard(final Chessboard chessboard) {
        chessboard.setWhiteMove(true);
        chessboard.setComplete(false);
        var board = chessboard.getBoard();

        for(var i = 2; i < 7; i++) {
            for(var j = 2; j < 7; j++) {
                board[i][j] = null;
            }
        }

        for (var i = 0; i < 8; i++) {
            board[1][i] = new Figure(FigureType.PAWN, new Point(i, 1), false);
            board[6][i] = new Figure(FigureType.PAWN, new Point(i, 6), true);
        }
        board[0][0] = new Figure(FigureType.ROOK, new Point(0, 1), false);
        board[7][0] = new Figure(FigureType.ROOK, new Point(0, 7), true);
        board[0][7] = new Figure(FigureType.ROOK, new Point(7, 0), false);
        board[7][7] = new Figure(FigureType.ROOK, new Point(7, 7), true);
        board[0][1] = new Figure(FigureType.KNIGHT, new Point(1, 0), false);
        board[7][1] = new Figure(FigureType.KNIGHT, new Point(1, 7), true);
        board[0][6] = new Figure(FigureType.KNIGHT, new Point(6, 0), false);
        board[7][6] = new Figure(FigureType.KNIGHT, new Point(6, 7), true);
        board[0][2] = new Figure(FigureType.BISHOP, new Point(2, 0), false);
        board[7][2] = new Figure(FigureType.BISHOP, new Point(2, 7), true);
        board[0][5] = new Figure(FigureType.BISHOP, new Point(5, 0), false);
        board[7][5] = new Figure(FigureType.BISHOP, new Point(5, 7), true);
        board[0][3] = new Figure(FigureType.QUEEN, new Point(3, 0), false);
        board[7][3] = new Figure(FigureType.QUEEN, new Point(3, 7), true);
        board[0][4] = new Figure(FigureType.KING, new Point(4, 0), false);
        board[7][4] = new Figure(FigureType.KING, new Point(4, 7), true);
    }
}
