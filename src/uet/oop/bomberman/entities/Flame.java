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
        for (Entity enity : entities) {
            if (enity instanceof MoveableObject && checkCollision(enity)) {
                MoveableObject moveableObject = (MoveableObject) enity;
                moveableObject.dead();
            }
        }
        img = animation.get(currentImage);
        timer += SPF;
        currentImage = (int) timer;
        if (currentImage == animation.size()) {
            destroy(entities, now);
        }
    }

    public void destroy(List<Entity> entities, long now) {
        entities.remove(this);
        for (int i = 0; i < entities.size(); i++) {
            if (entities.get(i) instanceof Bomb && checkCollision(entities.get(i))) {
                Bomb bomb = (Bomb) entities.get(i);
                bomb.explode(entities, now);
            }
        }
    }
}
