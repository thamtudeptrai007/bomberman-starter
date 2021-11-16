package uet.oop.bomberman.entities;

import javafx.scene.image.Image;
import uet.oop.bomberman.graphics.Animation;

import java.util.List;

public class Bomb extends DynamicObject {
    public static final int TIMETOEXPLODE = 2;
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
            return;
        }
        try {
            img = animation.get(currentImage);
            currentImage = (currentImage + 1) % animation.size();
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(0);
        }
    }

    public long getSetupTime() {
        return setupTime;
    }

    public void setSetupTime(long setupTime) {
        this.setupTime = setupTime;
    }
}
