package uet.oop.bomberman.entities;

import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import uet.oop.bomberman.graphics.Sprite;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class DynamicObject extends Entity {

    protected List<Image> animation = new ArrayList<>();
    protected int currentImage = 0;

    public DynamicObject(int x, int y, Image... images) {
        super(x, y, images[0]);
        setAnimation(images);
    }

    /**
     * add list images to imageList of direction dir.
     */
    public void setAnimation(Image... images) {
        animation.clear();
        Collections.addAll(animation, images);
    }

}
