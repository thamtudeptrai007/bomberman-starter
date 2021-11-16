package uet.oop.bomberman.entities;

public enum Direction {
    DOWN(3), LEFT(1), RIGHT(0), UP(2);
    private final int value;
    public static final int NUMBEROFDIRECTION = 4;

    Direction(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
