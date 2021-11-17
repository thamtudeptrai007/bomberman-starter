package uet.oop.bomberman.entities;

import javafx.scene.image.Image;

import java.util.List;

public class Oneal extends Enemy {
    public Oneal(int x, int y, Image... images) {
        super(x, y, images);
        SPF = 0.25;
    }
    /**
     * updateProperty.
     */
    @Override
    public void autoUpdate(List<Entity> entities, long now) {

    }
}
