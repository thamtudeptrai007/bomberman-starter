package uet.oop.bomberman.entities;

import javafx.scene.image.Image;
import uet.oop.bomberman.graphics.Animation;

import java.util.List;

public class Flame extends DynamicObject {
    private static final double SPF = 0.3;

    public Flame(int x, int y, long setupTime, Image... images) {
        super(x, y, images);
    }

    @Override
    public void update(List<Entity> entities, long now) {
        img = animation.get(currentImage);
        timer += SPF;
        currentImage = (int) timer;
        if (currentImage == animation.size()) {
            entities.remove(this);
        }
    }
}
