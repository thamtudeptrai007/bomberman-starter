package uet.oop.bomberman.graphics;

import javafx.scene.image.Image;

/**
 * Đối tượng hoạt ảnh lưu trữ các ảnh trong hoạt ảnh.
 */
public class Animation {
    private Sprite[] sprites;

    /*
     * Bomber animation.
     */
    public static Animation player_up = new Animation(Sprite.player_up, Sprite.player_up_1, Sprite.player_up_2);
    public static Animation player_down = new Animation(Sprite.player_down, Sprite.player_down_1, Sprite.player_down_2);
    public static Animation player_left = new Animation(Sprite.player_left, Sprite.player_left_1, Sprite.player_left_2);
    public static Animation player_right = new Animation(Sprite.player_right, Sprite.player_right_1, Sprite.player_right_2);
    public static Animation player_dead = new Animation(Sprite.player_dead1, Sprite.player_dead2, Sprite.player_dead3);

    /*
     * Character.
     */
    // Balloom
    public static Animation balloom_left = new Animation(Sprite.balloom_left1, Sprite.balloom_left2, Sprite.balloom_left3);
    public static Animation balloom_right = new Animation(Sprite.balloom_right1, Sprite.balloom_right2, Sprite.balloom_right3);
    public static Animation balloom_dead = new Animation(Sprite.balloom_dead, Sprite.mob_dead1, Sprite.mob_dead2, Sprite.mob_dead3);

    // ONEAL

    // Doll

    // Minvo

    // Kondoria

    /*
     * Bomb
     */
    public static Animation bomb = new Animation(Sprite.bomb, Sprite.bomb_1, Sprite.bomb_2, Sprite.bomb_1);

    /*
     * FlameSegment.
     */

    /*
     * Brick FlameSegment
     */

    /*
     * Powerups
     */


    public Animation(Sprite... images) {
        this.sprites = new Sprite[images.length];
        System.arraycopy(images, 0, this.sprites, 0, images.length);
    }

    public Image[] getFxImages() {
        Image[] images = new Image[sprites.length];
        for (int i = 0; i < sprites.length; i++) {
            images[i] = sprites[i].getFxImage();
        }
        return images;
    }
}
