package uet.oop.bomberman.entities;

import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import uet.oop.bomberman.graphics.Sprite;

import java.util.ArrayList;
import java.util.List;

public abstract class DynamicObject extends Entity {

    protected List<List<Image>> imgList = new ArrayList<>();
    protected int currentImage = 0;
    protected Direction direction = Direction.DEFAULT;

    public DynamicObject(int x, int y, Image img) {
        super(x, y, img);
        for (int i = 0; i < 5; i++) {
            imgList.add(new ArrayList<>());
        }
    }

    /**
     * add list images to imageList of direction dir.
     */
    public void addImage(Direction dir, Image... images) {
        for (Image image: images) {
            imgList.get(dir.getValue()).add(image);
        }
    }

}
