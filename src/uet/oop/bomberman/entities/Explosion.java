package uet.oop.bomberman.entities;

import javafx.scene.image.Image;

import java.util.List;

public class Explosion extends DynamicObject{

    public Explosion(int x, int y, long setupTime, Image... images) {
        super(x, y, images);
    }
    @Override
    public void update(List<Entity> entities, long now) {

    }
}
