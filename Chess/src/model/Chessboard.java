package model;

public class Chessboard {
    private final Figure[][] board;
    private final int size;
    private boolean whiteMotion;

    public Chessboard() {
        size = 8;
        board = new Figure[size][size];
        resetBoard();
    }

    public void resetBoard() {
        whiteMotion = true;

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

    public boolean isWhiteMotion() {
        return whiteMotion;
    }

    public void setWhiteMotion(boolean whiteMotion) {
        this.whiteMotion = whiteMotion;
    }
}
