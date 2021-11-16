package uet.oop.bomberman.entities;

import javafx.scene.image.Image;

public class Bomb extends DynamicObject {
    public static final int TIMETOEXPLODE = 2;
    private long setupTime;

    public Bomb(int x, int y, long setupTime, Image... images) {
        super(x, y, images);
        this.setupTime = setupTime;
    }

    /**
     * update
     */
    @Override
    public void update() {
        try {
            img = animation.get(currentImage / 15);
            currentImage = (currentImage + 1) % (animation.size() * 15);
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
