package uet.oop.bomberman.entities;

import javafx.scene.image.Image;

import java.util.List;

public abstract class Enemy extends MoveableObject {
    public Enemy(int x, int y, Image... images) {
        super(x, y, images);
    }

    @Override
    public void update(List<Entity> entities, long now) {
        if (!alive) {
            timer += SPF;
            currentImage = (int) (timer >= 0 ? timer : 0) % deadAnimation.size();
            img = deadAnimation.get(currentImage);
            if (currentImage == deadAnimation.size() - 1) {
                entities.remove(this);
                return;
            }
            return;
        }
        autoUpdate(entities, now);
        if (moving) {
            move(entities);
        }
        timer += SPF;
        currentImage = (int) timer % moveAnimation.get(animationDirection.getValue()).size();
        img = moveAnimation.get(animationDirection.getValue()).get(currentImage);
    }

    public abstract  void autoUpdate(List<Entity> entities, long now);
    /**
     * check can move to (newX, newY).
     */
    @Override
    public boolean canMove(List<Entity> entities, int newX, int newY) {
        for (Entity entity:entities) {
            if (entity instanceof Brick || entity instanceof Wall || entity instanceof Bomb) {
                if (checkCollision(newX, newY, entity)) {
                    return false;
                }
            }
        }
        return true;
    }
}
