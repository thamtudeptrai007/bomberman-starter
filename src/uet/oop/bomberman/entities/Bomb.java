package uet.oop.bomberman.entities;

import javafx.scene.image.Image;
import uet.oop.bomberman.graphics.Animation;
import uet.oop.bomberman.graphics.Sprite;

import java.util.List;

public class Bomb extends DynamicObject {
    public static final int TIMETOEXPLODE = 2;
    private static final double SPF = 0.5;
    protected long setupTime;
    private int size;

    public Bomb(int x, int y, int size, long setupTime, Image... images) {
        super(x, y, images);
        this.setupTime = setupTime;
        this.size = size;
    }

    /**
     * update
     */
    @Override
    public void update(List<Entity> entities, long now) {
        if (now - setupTime >= 2_000_000_000) {
            entities.remove(this);
            makeExplosion(entities, now);
            return;
        }
        img = animation.get(currentImage);
        timer += SPF;
        currentImage = (int) timer % animation.size();
    }

    public void makeExplosion(List<Entity> entities, long now) {
        int posX = x / Sprite.SCALED_SIZE;
        int posY = y / Sprite.SCALED_SIZE;
        entities.add(new Explosion(posX, posY,
                now, Animation.bomb_exploded.getFxImages()));
        if (size == 1) {
            return;
        }
        entities.add(new Explosion(posX + size - 1, posY, now,
                Animation.explosion_horizontal_right.getFxImages()));
        entities.add(new Explosion(posX - size + 1, posY, now,
                Animation.explosion_horizontal_left.getFxImages()));
        entities.add(new Explosion(posX, posY - size + 1, now,
                Animation.explosion_vertical_top.getFxImages()));
        entities.add(new Explosion(posX, posY + size - 1, now,
                Animation.explosion_vertical_down.getFxImages()));
        for (int i = 1; i < size - 1; i++) {
            entities.add(new Explosion(posX + i, posY, now,
                    Animation.explosion_horizontal.getFxImages()));
            entities.add(new Explosion(posX - i, posY, now,
                    Animation.explosion_horizontal.getFxImages()));
            entities.add(new Explosion(posX, posY - i, now,
                    Animation.explosion_vertical.getFxImages()));
            entities.add(new Explosion(posX, posY + i, now,
                    Animation.explosion_vertical.getFxImages()));
        }
    }

    public long getSetupTime() {
        return setupTime;
    }

    public void setSetupTime(long setupTime) {
        this.setupTime = setupTime;
    }
}
