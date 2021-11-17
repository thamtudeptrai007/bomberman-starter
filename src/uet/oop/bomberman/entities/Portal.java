package uet.oop.bomberman.entities;

import javafx.scene.image.Image;
import java.util.List;

public class Portal extends StaticObject{
    public Portal(int x, int y, Image img) {
        super(x, y, img);
    }
    @Override
    public void update(List<Entity> entities, long now) {
        for (Entity entity: entities) {
            if (entity instanceof Enemy) {
                Enemy enemy = (Enemy) entity;
                if (enemy.isAlive()) {
                    return;
                }
            }
        }
        for (Entity entity: entities) {
            if (entity instanceof Bomber && entity.getX() == x && entity.getY() == y) {
                System.out.println("Level completed!");
                System.exit(0);
            }
        }
    }
}
