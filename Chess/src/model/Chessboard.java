package model;

public class Chessboard {
    private final Figure[][] board;
    private final int size;
    private boolean whiteMove;
    private boolean isComplete;

    public Chessboard() {
        size = 8;
        board = new Figure[size][size];
        whiteMove = true;
        isComplete = false;
    }

    public Figure[][] getBoard() {
        return board;
    }

    public Figure getFigure(final int x, final int y) {
        if (x < 0 || y < 0 || x >= size || y >= size) throw new RuntimeException(String.format("Point (%s, %s) out of bounds of board", x, y));
        return board[y][x];
    }
    public void setFigure(final Figure figure, final int x, final int y) {
        if (x < 0 || y < 0 || x >= size || y >= size) throw new RuntimeException(String.format("Point (%s, %s) out of bounds of board", x, y));
        board[y][x] = figure;
    }

    public int getSize() {
        return size;
    }

    public boolean isWhiteMove() {
        return whiteMove;
    }

    public boolean isComplete() {
        return isComplete;
    }

    public void setWhiteMove(boolean whiteMove) {
        this.whiteMove = whiteMove;
    }

    public void setComplete(boolean complete) {
        isComplete = complete;
    }
}
