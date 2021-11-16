package uet.oop.bomberman.entities;

import javafx.scene.image.Image;

import java.util.List;

public abstract class Enemy extends MoveableObject {
    public Enemy(int x, int y, Image... images) {
        super(x, y, images);
    }

    @Override
    public void update(List<Entity> entities, long now) {
        autoUpdate(entities, now);
        if (moving) {
            move(entities);
        }
        timer += SPF;
        currentImage = (int) timer % moveAnimation.get(direction.getValue()).size();
        img = moveAnimation.get(direction.getValue()).get(currentImage);
    }

    public abstract  void autoUpdate(List<Entity> entities, long now);
    /**
     * check can move to (newX, newY).
     */
    @Override
    public boolean canMove(List<Entity> entities, int newX, int newY) {
        return true;
    }
}
