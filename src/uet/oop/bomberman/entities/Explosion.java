package uet.oop.bomberman.entities;

import javafx.scene.image.Image;

import java.util.List;

public class Explosion extends DynamicObject{
    private static final double SPF = 0.5;

    public Explosion(int x, int y, long setupTime, Image... images) {
        super(x, y, images);
    }
    @Override
    public void update(List<Entity> entities, long now) {
        img = animation.get(currentImage);
        timer += SPF;
        currentImage = (int)timer;
        if (currentImage == animation.size()) {
            entities.remove(this);
        }
    }
}
