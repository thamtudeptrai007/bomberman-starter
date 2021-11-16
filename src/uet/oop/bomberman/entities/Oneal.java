package uet.oop.bomberman.entities;

import javafx.scene.image.Image;

import java.util.List;

public class Oneal extends Enemy {
    public Oneal(int x, int y, Image... images) {
        super(x, y, images);
        SPF = 0.25;
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

    /**
     * updateProperty.
     */
    @Override
    public void autoUpdate(List<Entity> entities, long now) {

    }
}
