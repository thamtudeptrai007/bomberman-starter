package uet.oop.bomberman.entities;

import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

public class Bomber extends DynamicObject {

    public Bomber(int x, int y, Image... images) {
        super(x, y, images);
    }

    /**
     * update
     */
    @Override
    public void update() {

    }
}
