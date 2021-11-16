package uet.oop.bomberman.entities;

import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

public class MoveableObject extends DynamicObject {
    public static final int MOVESPEED = 1;
    protected Direction direction = Direction.RIGHT;
    protected boolean moving = false;
    protected List<List<Image>> moveAnimation = new ArrayList<>();
    protected List<Image> deadAnimation = new ArrayList<>();

    public MoveableObject(int x, int y, Image... images) {
        super(x, y, images);
        for (int i = 0; i < Direction.NUMBEROFDIRECTION; i++) {
            moveAnimation.add(new ArrayList<>());
        }
    }

    @Override
    public void update() {
        if (moving) {
            move();
        }
    }

    public void move() {
        int newX = x;
        int newY = y;
        switch (direction) {
            case RIGHT:
                newY += MOVESPEED;
                break;
            case LEFT:
                newY -= MOVESPEED;
                break;
            case UP:
                newX -= MOVESPEED;
                break;
            case DOWN:
                newX += MOVESPEED;
                break;
        }
        if (canMove(newX, newY)) {
            x = newX;
            y = newY;
        }
    }

    public boolean canMove(int newX, int newY) {
        return true;
    }

    public void setMoveAnimation(Direction direction, Image... images) {
        moveAnimation.get(direction.getValue()).clear();
        Collections.addAll(moveAnimation.get(direction.getValue()), images);
    }

    public void setDeadAnimation(Image... images) {
        deadAnimation.clear();
        Collections.addAll(deadAnimation, images);
    }
}
