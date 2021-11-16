package uet.oop.bomberman.entities;

import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

public abstract class MoveableObject extends DynamicObject {
    public static final int DEFAULTMOVESPEED = 4;
    protected int MOVESPEED = DEFAULTMOVESPEED;
    protected int MOVESPEEDX = 0;
    protected int MOVESPEEDY = 0;
    protected double SPF = 0.8;
    protected Direction direction = Direction.RIGHT;
    protected Direction animationDirection = Direction.RIGHT;
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
        boolean changeAnimationDirection = false;
        switch (direction) {
            case LEFT:
            case RIGHT:
                newX += MOVESPEEDX;
                break;
            case DOWN:
            case UP:
                newY += MOVESPEEDY;
                break;
        }
        if (canMove(entities, newX, newY)) {
            x = newX;
            y = newY;
        } else {
            changeAnimationDirection = true;
            newX = x;
            newY = y;
        }
        switch (direction) {
            case LEFT:
            case RIGHT:
                newY += MOVESPEEDY;
                if (MOVESPEEDY > 0) {
                    animationDirection = Direction.DOWN;
                }
                if (MOVESPEEDY < 0){
                    animationDirection = Direction.UP;
                }
                break;
            case DOWN:
            case UP:
                newX += MOVESPEEDX;
                if (MOVESPEEDX > 0) {
                    animationDirection = Direction.RIGHT;
                }
                if (MOVESPEEDX < 0){
                    animationDirection = Direction.LEFT;
                }
                break;
        }
        if (canMove(entities, newX, newY)) {
            x = newX;
            y = newY;
        } else {
            changeAnimationDirection = false;
        }
        if (!changeAnimationDirection) {
            animationDirection = direction;
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
