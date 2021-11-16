package uet.oop.bomberman.entities;

import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

public abstract class MoveableObject extends DynamicObject {
    public static final int DEFAULTMOVESPEED = 4;
    protected int MOVESPEED = DEFAULTMOVESPEED;
    protected double SPF = 1;
    protected Direction direction = Direction.RIGHT;
    protected boolean running = true;
    protected boolean moving = false;
    protected List<List<Image>> moveAnimation = new ArrayList<>();
    protected List<Image> deadAnimation = new ArrayList<>();

    public MoveableObject(int x, int y, Image... images) {
        super(x, y, images);
        for (int i = 0; i < Direction.NUMBEROFDIRECTION; i++) {
            moveAnimation.add(new ArrayList<>());
        }
    }

    public void move(List<Entity> entities) {
        int newX = x;
        int newY = y;
        switch (direction) {
            case RIGHT:
                newX += MOVESPEED;
                break;
            case LEFT:
                newX -= MOVESPEED;
                break;
            case UP:
                newY -= MOVESPEED;
                break;
            case DOWN:
                newY += MOVESPEED;
                break;
        }
        if (canMove(entities, newX, newY)) {
            x = newX;
            y = newY;
        }
    }

    public abstract boolean canMove(List<Entity> entities, int newX, int newY);

    public void setMoveAnimation(Direction direction, Image... images) {
        moveAnimation.get(direction.getValue()).clear();
        Collections.addAll(moveAnimation.get(direction.getValue()), images);
    }

    public void setDeadAnimation(Image... images) {
        deadAnimation.clear();
        Collections.addAll(deadAnimation, images);
    }
}
