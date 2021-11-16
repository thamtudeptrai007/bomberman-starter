package uet.oop.bomberman.entities;

import javafx.scene.image.Image;

import java.util.List;

public class Wall extends StaticObject {

    public Wall(int x, int y, Image img) {
        super(x, y, img);
    }

    @Override
    public void update(List<Entity> entities, long now) {

    }
}
