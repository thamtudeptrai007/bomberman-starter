package uet.oop.bomberman.entities;

import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import uet.oop.bomberman.graphics.Animation;
import uet.oop.bomberman.graphics.Sprite;

import java.util.ArrayList;
import java.util.List;

public class Bomber extends MoveableObject {
    private boolean hasNewBomb = false;
    private int bombFlameSize = 1;
    private int numberBomb = 2;
    private boolean bombPass = false;
    private List<Bomb> bombList = new ArrayList<>();

    public Bomber(int x, int y, Image... images) {
        super(x, y, images);
    }

    /**
     * update
     */
    @Override
    public void update(List<Entity> entities, long now) {
        if (hasNewBomb && numberBomb > bombList.size()) {
            addBomb(entities, now);
        }
        if (moving) {
            move(entities);
            timer += SPF;
            currentImage = (int) timer % moveAnimation.get(animationDirection.getValue()).size();
        } else {
            if (currentImage != 0) {
                timer += SPF;
                currentImage = (int) timer % moveAnimation.get(animationDirection.getValue()).size();
            }
        }
        img = moveAnimation.get(animationDirection.getValue()).get(currentImage);
    }

    /**
     * check can move to (newX, newY).
     */
    @Override
    public boolean canMove(List<Entity> entities, int newX, int newY) {
        for (Entity entity:entities) {
            if (entity instanceof Brick || entity instanceof Wall) {
                if (checkCollision(newX, newY, entity)) {
                    return false;
                }
            }
        }
        return true;
    }

    public void addBomb(List<Entity> entities, long now) {
        hasNewBomb = false;
        int posX = (x + Sprite.SCALED_SIZE / 2) / Sprite.SCALED_SIZE;
        int posY = (y + Sprite.SCALED_SIZE / 2) / Sprite.SCALED_SIZE;
        for (Bomb bomb: bombList) {
            if (bomb.getX() == posX * Sprite.SCALED_SIZE && bomb.getY() == posY * Sprite.SCALED_SIZE) {
                return;
            }
        }
        Bomb bomb = new Bomb(posX, posY,
                bombFlameSize, now, Animation.bomb.getFxImages());
        bombList.add(bomb);
        entities.add(bomb);
    }

    public void removeBomb(List<Entity> entities, Bomb bomb) {
        bombList.remove(bomb);
        entities.remove(bomb);
    }

    public void increaseSpeed(int incSpeed) {
        MOVESPEED += incSpeed;
    }
    public void increaseFlameSize() {
        bombFlameSize++;
    }
    public void increaseNumberBomb() {
        numberBomb++;
    }
    /**
     * on key pressed.
     */
    public void press(KeyEvent keyEvent, boolean changeDirection) {
        moving = true;
        Direction lastDirection = direction;
        switch (keyEvent.getCode()) {
            case LEFT:
                direction = Direction.LEFT;
                MOVESPEEDX = -MOVESPEED;
                break;
            case RIGHT:
                direction = Direction.RIGHT;
                MOVESPEEDX = MOVESPEED;
                break;
            case UP:
                direction = Direction.UP;
                MOVESPEEDY = -MOVESPEED;
                break;
            case DOWN:
                direction = Direction.DOWN;
                MOVESPEEDY = MOVESPEED;
                break;
            case SPACE:
                hasNewBomb = true;
            default:
                moving = false;
        }
        if (!changeDirection) {
            int temp = direction.getValue() + lastDirection.getValue();
            if (temp != 1 && temp != 5) {
                direction = lastDirection;
            }
        } else {
            animationDirection = direction;
        }
    }

    /**
     * on key release.
     */
    public void release(KeyEvent keyEvent) {
        switch (keyEvent.getCode()) {
            case LEFT:
            case RIGHT:
                MOVESPEEDX = 0;
                moving = false;
                break;
            case UP:
            case DOWN:
                MOVESPEEDY = 0;
                moving = false;
                break;
            case SPACE:
                break;
            default:
        }
    }

    public List<Bomb> getBombList() {
        return bombList;
    }
}
