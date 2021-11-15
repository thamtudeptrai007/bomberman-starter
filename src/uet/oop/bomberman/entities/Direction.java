package uet.oop.bomberman.entities;

public enum Direction {
    DOWN(4), LEFT(1), RIGHT(2), DEFAULT(0), UP(3);
    private final int value;

    Direction(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
