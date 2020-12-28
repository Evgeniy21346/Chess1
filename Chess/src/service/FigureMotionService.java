package service;

import model.Chessboard;
import model.Figure;

public class FigureMotionService {
    public static boolean isPawnMoveValid(final Chessboard chessboard, final Figure figure, final int destRow, final int destCol) {
        if (figure.isWhite()) {
            return ((figure.getPosition().getX() == destCol && chessboard.getFigure(destCol, destRow) == null) &&
                    ((figure.getPosition().getY() == 6 && destRow == 4 && chessboard.getFigure(destCol, 5) == null) || (figure.getPosition().getY() == destRow + 1))) ||
                    (Math.abs(figure.getPosition().getX() - destCol) == 1 && figure.getPosition().getY() == destRow + 1 && chessboard.getFigure(destCol,destRow) != null);
        }
        else {
            return ((figure.getPosition().getX() == destCol && chessboard.getFigure(destCol, destRow) == null) &&
                    ((figure.getPosition().getY() == 1 && destRow == 3 && chessboard.getFigure(destCol, 2) == null) || (figure.getPosition().getY() == destRow - 1))) ||
                    (Math.abs(figure.getPosition().getX() - destCol) == 1 && figure.getPosition().getY() == destRow - 1 && chessboard.getFigure(destCol,destRow) != null);
        }
    }
    public static boolean isBishopMoveValid(final Chessboard chessboard, final Figure figure, final int destRow, final int destCol) {
        var value = ((Math.abs(figure.getPosition().getY() - destRow) == Math.abs(figure.getPosition().getX() - destCol)));
        if (value) {
            var multiplierX = figure.getPosition().getX() < destCol ? 1 : -1;
            var multiplierY = figure.getPosition().getY() < destRow ? 1 : -1;
            for(int i = 0; i < Math.abs(figure.getPosition().getX() - destCol) - 1; i++) {
                if(chessboard.getFigure(figure.getPosition().getX() + multiplierX, figure.getPosition().getY() + multiplierY) != null)
                    return false;
            }
        }
        return value;
    }
    public static boolean isKingMoveValid(final Figure figure, final int destRow, final int destCol) {
        return Math.abs(destRow - figure.getPosition().getY()) <= 1 || Math.abs(destCol - figure.getPosition().getX()) <= 1;
    }
    public static boolean isKnightMoveValid(final Figure figure, final int destRow, final int destCol) {
        return ((Math.abs(figure.getPosition().getY() - destRow) == 2 && Math.abs(figure.getPosition().getX() - destCol) == 1)
                || (Math.abs(figure.getPosition().getY() - destRow) == 1 && Math.abs(figure.getPosition().getX() - destCol) == 2));
    }
    public static boolean isQueenMoveValid(final Chessboard chessboard, final Figure figure, final int destRow, final int destCol) {
        return isBishopMoveValid(chessboard, figure, destRow, destCol) || isRookMoveValid(chessboard, figure, destRow, destCol);
    }
    public static boolean isRookMoveValid(final Chessboard chessboard, final Figure figure, final int destRow, final int destCol) {
        var value = !((figure.getPosition().getY() != destRow) && (figure.getPosition().getX() != destCol));
        if (value) {
            if (figure.getPosition().getY() != destRow) {
                var multiplierY = figure.getPosition().getY() < destRow ? 1 : -1;
                for (int i = 0; i < Math.abs(figure.getPosition().getY() - destRow); i++) {
                    if (chessboard.getFigure(destCol, figure.getPosition().getY() + multiplierY) != null)
                        return false;
                }
            }
            else {
                var multiplierX = figure.getPosition().getX() < destCol ? 1 : -1;
                for (int i = 0; i < Math.abs(figure.getPosition().getX() - destCol); i++) {
                    if (chessboard.getFigure(figure.getPosition().getX() + multiplierX, destRow) != null)
                        return false;
                }
            }
        }
        return value;
    }

    public static boolean isMoveValid(final Chessboard chessboard, final Figure figure, final int x, final int y) {
        if (x < 0 || y < 0 || x >= 8 || y >= 8) return false;
        return switch (figure.getFigureType()) {
            case PAWN -> isPawnMoveValid(chessboard, figure, y, x);
            case BISHOP -> isBishopMoveValid(chessboard, figure, y, x);
            case KING -> isKingMoveValid(figure, y, x);
            case KNIGHT -> isKnightMoveValid(figure, y, x);
            case QUEEN -> isQueenMoveValid(chessboard, figure, y, x);
            case ROOK -> isRookMoveValid(chessboard, figure, y, x);
        };
    }
}