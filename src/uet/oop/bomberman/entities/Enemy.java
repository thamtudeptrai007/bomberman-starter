package uet.oop.bomberman.entities;

import javafx.scene.image.Image;

public class Enemy extends DynamicObject {
    public Enemy(int x, int y, Image... images) {
        super(x, y, images);
    }

    /**
     * update
     */
    @Override
    public void update() {
    }
}
