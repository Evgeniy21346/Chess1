package view;

import model.Figure;

public class FigureView {
    public static String getImage(final Figure figure) {
        if (figure == null) return " ";

        var type = figure.getFigureType();
        var isWhite = figure.isWhite();
        return switch (type) {
            case KING -> isWhite ? "\u2654" :"\u265A";
            case KNIGHT -> isWhite ? "\u2658" :"\u265E";
            case PAWN -> isWhite ? "\u2659" :"\u265F";
            case QUEEN -> isWhite ? "\u2655" :"\u265B";
            case ROOK -> isWhite ? "\u2656" :"\u265C";
            case BISHOP -> isWhite ? "\u2657" :"\u265D";
        };
    }
}
