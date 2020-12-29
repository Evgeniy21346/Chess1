package model;

import java.util.Objects;

public class Point implements Cloneable {
    private int x;
    private int y;

    public Point() {
        this(0,0);
    }

    public Point(final int x, final int y) {
        this.x = x;
        this.y = y;
    }

    public Point(final Point point) {
        this(point.getX(), point.getY());
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(final int x) {
        this.x = x;
    }

    public void setY(final int y) {
        this.y = y;
    }

    @Override
    public String toString() {
        return String.format("{x: %s, y: %s}", getX(), getY());
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        Point point = (Point)super.clone();
        point.setX(getX());
        point.setY(getY());
        return point;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Point point = (Point) o;
        return x == point.x && y == point.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}