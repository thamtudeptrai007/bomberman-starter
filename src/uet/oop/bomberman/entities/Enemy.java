package uet.oop.bomberman.entities;

import javafx.scene.image.Image;

import java.util.List;

public class Enemy extends MoveableObject {
    public Enemy(int x, int y, Image... images) {
        super(x, y, images);
    }

    /**
     * updateProperty.
     */
    @Override
    public void updateProperty(List<Entity> entities, long now) {
    }

    /**
     * check can move to (newX, newY).
     */
    @Override
    public boolean canMove(List<Entity> entities, int newX, int newY) {
        return false;
    }
}
