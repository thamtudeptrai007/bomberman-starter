package uet.oop.bomberman.entities;

import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;

import java.util.List;

public class Bomber extends MoveableObject {

    public Bomber(int x, int y, Image... images) {
        super(x, y, images);
    }

    /**
     * update
     */
    @Override
    public void update(List<Entity> entities) {
        if (moving) {
            move(entities);
            currentImage = (currentImage + 1) % (moveAnimation.get(direction.getValue()).size() * 5);
        }
        else {
            if (currentImage != 0) {
                currentImage = (currentImage + 1) % (moveAnimation.get(direction.getValue()).size() * 5);
            }
        }
        img = moveAnimation.get(direction.getValue()).get(currentImage / 5);
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
