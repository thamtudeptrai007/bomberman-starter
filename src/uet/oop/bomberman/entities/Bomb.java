package uet.oop.bomberman.entities;

import javafx.scene.image.Image;
import uet.oop.bomberman.graphics.Animation;
import uet.oop.bomberman.graphics.Sprite;

import java.util.List;

public class Bomb extends DynamicObject {
    public static final int TIMETOEXPLODE = 2;
    private static final double SPF = 0.25;
    private long setupTime;
    private int size;
    private Bomber owner;
    private boolean ownerPassEnable = true;

    public Bomb(int x, int y, int size, long setupTime, Bomber owner, Image... images) {
        super(x, y, images);
        this.setupTime = setupTime;
        this.size = size;
        this.owner = owner;
    }

    /**
     * update
     */
    @Override
    public void update(List<Entity> entities, long now) {
        if (now - setupTime >= 2_000_000_000) {
            explode(entities, now);
            return;
        }
        if (ownerPassEnable && !checkCollision(owner)) {
            ownerPassEnable = false;
        }
        img = animation.get(currentImage);
        timer += SPF;
        currentImage = (int) timer % animation.size();
    }

    public void explode(List<Entity> entities, long now) {
        owner.removeBomb(entities, this);
        int posX = getXUnit();
        int posY = getYUnit();
        entities.add(new Flame(posX, posY,
                now, Animation.bomb_exploded.getFxImages()));
        createFlameRight(entities, now);
        createFlameLeft(entities, now);
        createFlameTop(entities, now);
        createFlameBottom(entities, now);
    }

    public void createFlameRight(List<Entity> entities, long now) {
        int posX = getXUnit();
        int posY = getYUnit();
        for (int i = 1; i <= size; i++) {
            Entity entity = getAt(posX + i, posY, entities);
            if (entity != null) {
                if (entity instanceof Brick) {
                    entities.add(new BrickExplode(posX + i, posY, now,
                            Animation.brick_explode.getFxImages()));
                    entities.remove(entity);
                }
                break;
            }
            if (i < size) {
                entities.add(new Flame(posX + i, posY, now,
                        Animation.explosion_horizontal.getFxImages()));
            } else {
                entities.add(new Flame(posX + size, posY, now,
                        Animation.explosion_horizontal_right.getFxImages()));
            }
        }
    }

    public void createFlameLeft(List<Entity> entities, long now) {
        int posX = getXUnit();
        int posY = getYUnit();
        for (int i = 1; i <= size; i++) {
            Entity entity = getAt(posX - i, posY, entities);
            if (entity != null) {
                if (entity instanceof Brick) {
                    entities.add(new BrickExplode(posX - i, posY, now,
                            Animation.brick_explode.getFxImages()));
                    entities.remove(entity);
                }
                break;
            }
            if (i < size) {
                entities.add(new Flame(posX - i, posY, now,
                        Animation.explosion_horizontal.getFxImages()));
            } else {
                entities.add(new Flame(posX - size, posY, now,
                        Animation.explosion_horizontal_left.getFxImages()));
            }
        }
    }

    public void createFlameTop(List<Entity> entities, long now) {
        int posX = getXUnit();
        int posY = getYUnit();
        for (int i = 1; i <= size; i++) {
            Entity entity = getAt(posX, posY - i, entities);
            if (entity != null) {
                if (entity instanceof Brick) {
                    entities.add(new BrickExplode(posX, posY - i, now,
                            Animation.brick_explode.getFxImages()));
                    entities.remove(entity);
                }
                break;
            }
            if (i < size) {
                entities.add(new Flame(posX, posY - i, now,
                        Animation.explosion_vertical.getFxImages()));
            } else {
                entities.add(new Flame(posX, posY - size, now,
                        Animation.explosion_vertical_top.getFxImages()));
            }
        }
    }

    public void createFlameBottom(List<Entity> entities, long now) {
        int posX = getXUnit();
        int posY = getYUnit();
        for (int i = 1; i <= size; i++) {
            Entity entity = getAt(posX, posY + i, entities);
            if (entity != null) {
                if (entity instanceof Brick) {
                    entities.add(new BrickExplode(posX, posY + i, now,
                            Animation.brick_explode.getFxImages()));
                    entities.remove(entity);
                }
                break;
            }
            if (i < size) {
                entities.add(new Flame(posX, posY + i, now,
                        Animation.explosion_vertical.getFxImages()));
            } else {
                entities.add(new Flame(posX, posY + size, now,
                        Animation.explosion_vertical_down.getFxImages()));
            }
        }
    }

    public long getSetupTime() {
        return setupTime;
    }

    public void setSetupTime(long setupTime) {
        this.setupTime = setupTime;
    }

    public boolean isOwnerPassEnable() {
        return ownerPassEnable;
    }
}
