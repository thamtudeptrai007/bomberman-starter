package uet.oop.bomberman.entities;

import javafx.scene.image.Image;

public class Enemy extends DynamicObject {
    public Enemy(int x, int y, Image img) {
        super(x, y, img);
    }

    /**
     * update
     */
    @Override
    public void update() {
        img = imgList.get(direction.getValue()).get(currentImage);
        currentImage = (currentImage + 1) % imgList.get(direction.getValue()).size();
    }
}
