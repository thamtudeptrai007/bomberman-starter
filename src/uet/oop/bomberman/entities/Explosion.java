package uet.oop.bomberman.entities;

import javafx.scene.image.Image;

public class Explosion extends Bomb{

    public Explosion(int x, int y, long setupTime, Image... images) {
        super(x, y, setupTime, images);
        this.setupTime = setupTime;
    }
}
