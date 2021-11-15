package uet.oop.bomberman.entities;

import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

public class Bomber extends DynamicObject {

    public Bomber(int x, int y, Image img) {
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
