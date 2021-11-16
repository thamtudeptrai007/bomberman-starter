package uet.oop.bomberman.entities;

import javafx.scene.image.Image;

import java.util.List;

public class FlameItem extends Item{
    public FlameItem(int x, int y, Image img) {
        super(x, y, img);
    }

    @Override
    public void update(List<Entity> entities, long now) {
        for (Entity entity:entities) {
            if (entity instanceof Bomber && checkCollision(entity)) {
                Bomber bomber = (Bomber)entity;
                entities.remove(this);
                bomber.increaseFlameSize();
                return;
            }
        }
    }
}
