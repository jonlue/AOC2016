package main.util;

import java.util.Objects;

public class Position {

    public final int x;
    public final int y;
    public final int distance;

    public Position(int x, int y, int distance) {
        this.x = x;
        this.y = y;
        this.distance = distance;
    }


    public Position(Position p) {
        this.x = p.x;
        this.y = p.y;
        this.distance = p.distance;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return x == position.x && y == position.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
