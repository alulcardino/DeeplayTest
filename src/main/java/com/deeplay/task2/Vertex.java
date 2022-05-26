package com.deeplay.task2;

import java.util.Objects;

public class Vertex {
    private final int y;
    private final int x;

    public Vertex(int y, int x) {
        this.y = y;
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public int getX() {
        return x;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vertex vertex = (Vertex) o;
        return y == vertex.y && x == vertex.x;
    }

    @Override
    public int hashCode() {
        return Objects.hash(y, x);
    }
}
