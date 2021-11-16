package uet.oop.bomberman.entities;

import javafx.scene.image.Image;
import uet.oop.bomberman.graphics.Animation;
import uet.oop.bomberman.graphics.Sprite;

import java.util.List;

public class Balloom extends Enemy{
    public Balloom(int x, int y, Image... images) {
        super(x, y, images);
        SPF = 0.25;
        MOVESPEED = 2;
    }
    /**
     * updateProperty.
     */
    @Override
    public void autoUpdate(List<Entity> entities, long now) {
        if (Math.round(Math.random()) == 0) {
            animationDirection = Direction.LEFT;
        } else {
            animationDirection = Direction.RIGHT;
        }
        int last = 5;
        if (moving) {
            last = direction.getValue();
        }
        int rand = (int)Math.floor(Math.random() * 8.0);
        if (Math.random() >= 0.1) {
            rand = last;
        }
        moving = false;
        switch (rand) {
            case 0:
                direction = Direction.RIGHT;
                MOVESPEEDX = MOVESPEED;
                moving = true;
                break;
            case 1:
                direction = Direction.LEFT;
                MOVESPEEDX = -MOVESPEED;
                moving = true;
                break;
            case 2:
                direction = Direction.UP;
                MOVESPEEDY = -MOVESPEED;
                moving = true;
                break;
            case 3:
                direction = Direction.DOWN;
                MOVESPEEDY = MOVESPEED;
                moving = true;
                break;
            default:
        }
    }
}
