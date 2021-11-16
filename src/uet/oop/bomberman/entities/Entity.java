package uet.oop.bomberman.entities;

import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import uet.oop.bomberman.graphics.Sprite;

import java.util.List;

public abstract class Entity {
    //Tọa độ X tính từ góc trái trên trong Canvas
    protected int x;

    //Tọa độ Y tính từ góc trái trên trong Canvas
    protected int y;

    protected Image img;

    /**
     * Khởi tạo đối tượng, chuyển từ tọa độ đơn vị sang tọa độ trong canvas.
     */
    public Entity(int xUnit, int yUnit, Image img) {
        this.x = xUnit * Sprite.SCALED_SIZE;
        this.y = yUnit * Sprite.SCALED_SIZE;
        this.img = img;
    }

    public static Entity getAt(int xUnit, int yUnit, List<Entity> entities) {
        for (Entity entity : entities) {
            if (entity.getXUnit() == xUnit && entity.getYUnit() == yUnit
                    && (entity instanceof Brick || entity instanceof Wall)) {
                return entity;
            }
        }
        return null;
    }

    public void render(GraphicsContext gc) {
        gc.drawImage(img, x, y);
    }

    public abstract void update(List<Entity> entities, long now);

    public boolean checkCollision(Entity entity) {
        int myleft = this.x;
        int myright = this.x + Sprite.SCALED_SIZE - 1;
        int mytop = this.y;
        int mybottom = this.y + Sprite.SCALED_SIZE - 1;
        int otherleft = entity.getX();
        int otherright = entity.getX() + Sprite.SCALED_SIZE - 1;
        int othertop = entity.getY();
        int otherbottom = entity.getY() + Sprite.SCALED_SIZE - 1;
        return (mybottom >= othertop) && (mytop <= otherbottom) && (myright >= otherleft) && (myleft <= otherright);
    }

    public static boolean checkCollision(int myleft, int mytop, Entity entity) {
        int myright = myleft + Sprite.SCALED_SIZE - 1;
        int mybottom = mytop + Sprite.SCALED_SIZE - 1;
        int otherleft = entity.getX();
        int otherright = entity.getX() + Sprite.SCALED_SIZE - 1;
        int othertop = entity.getY();
        int otherbottom = entity.getY() + Sprite.SCALED_SIZE - 1;
        return (mybottom >= othertop) && (mytop <= otherbottom) && (myright >= otherleft) && (myleft <= otherright);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getXUnit() {
        return x / Sprite.SCALED_SIZE;
    }

    public int getYUnit() {
        return y / Sprite.SCALED_SIZE;
    }

}
