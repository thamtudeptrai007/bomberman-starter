package uet.oop.bomberman.entities;

import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import uet.oop.bomberman.graphics.Animation;
import uet.oop.bomberman.graphics.Sprite;

import java.util.List;

public class Bomber extends MoveableObject {
    private boolean hasNewBomb = false;
    private int bombSize = 4;

    public Bomber(int x, int y, Image... images) {
        super(x, y, images);
    }
    /**
     * update
     */
    @Override
    public void update(List<Entity> entities, long now) {
        if (hasNewBomb) {
            hasNewBomb = false;
            int posX = (x + Sprite.SCALED_SIZE / 2) / Sprite.SCALED_SIZE;
            int posY = (y + Sprite.SCALED_SIZE / 2)/ Sprite.SCALED_SIZE;
            Bomb bomb = new Bomb(posX, posY,
                    bombSize, now, Animation.bomb.getFxImages());
            entities.add(bomb);
        }
        if (moving) {
            move(entities);
        }
        if (currentImage != 0) {
            timer += SPF;
            currentImage = (int) timer % moveAnimation.get(direction.getValue()).size();
        }
        img = moveAnimation.get(direction.getValue()).get(currentImage);
    }

    /**
     * check can move to (newX, newY).
     */
    @Override
    public boolean canMove(List<Entity> entities, int newX, int newY) {
        return true;
    }

    /**
     * on key pressed.
     */
    public void press(KeyEvent keyEvent) {
        switch (keyEvent.getCode()) {
            case LEFT:
                direction = Direction.LEFT;
                moving = true;
                break;
            case RIGHT:
                direction = Direction.RIGHT;
                moving = true;
                break;
            case UP:
                direction = Direction.UP;
                moving = true;
                break;
            case DOWN:
                direction = Direction.DOWN;
                moving = true;
                break;
            case SPACE:
                hasNewBomb = true;
                break;
            default:
        }
    }

    /**
     * on key release.
     */
    public void release(KeyEvent keyEvent) {
        switch (keyEvent.getCode()) {
            case LEFT:
            case RIGHT:
            case UP:
            case DOWN:
                moving = false;
                break;
            case SPACE:
                break;
            default:
        }
    }
}
