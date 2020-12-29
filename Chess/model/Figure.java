package model;

public class Figure {
    private Point position;
    private boolean isWhite;
    private FigureType figureType;

    public Figure(final FigureType type, final boolean isWhite) {
        this(type, new Point(), isWhite);
    }

    public Figure(final FigureType type, final Point position, final boolean isWhite) {
        this.figureType = type;
        this.position = position;
        this.isWhite = isWhite;
    }

    public Point getPosition() {
        return position;
    }

    public void setPosition(final Point position) {
        this.position = position;
    }

    public boolean isWhite() {
        return isWhite;
    }

    public FigureType getFigureType() {
        return figureType;
    }

    public void setFigureType(final FigureType figureType) {
        this.figureType = figureType;
    }
}
