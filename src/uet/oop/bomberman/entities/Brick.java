package uet.oop.bomberman.entities;

import javafx.scene.image.Image;

import java.util.List;

public class Brick extends StaticObject{
    public Brick(int x, int y, Image img) {
        super(x, y, img);
    }

    @Override
    public void update(List<Entity> entities) {

    }
}
