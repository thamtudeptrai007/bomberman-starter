package uet.oop.bomberman;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import uet.oop.bomberman.entities.*;
import uet.oop.bomberman.graphics.Animation;
import uet.oop.bomberman.graphics.Sprite;

import java.util.ArrayList;
import java.util.List;

public class BombermanGame extends Application {

    public static final int WIDTH = 20;
    public static final int HEIGHT = 15;

    private GraphicsContext gc;
    private Canvas canvas;
    private List<Entity> entities = new ArrayList<>();
    private List<KeyEvent> keyEvents = new ArrayList<>();


    public static void main(String[] args) {
        Application.launch(BombermanGame.class);
    }

    @Override
    public void start(Stage stage) {
        // Tao Canvas
        canvas = new Canvas(Sprite.SCALED_SIZE * WIDTH, Sprite.SCALED_SIZE * HEIGHT);
        gc = canvas.getGraphicsContext2D();

        // Tao root container
        Group root = new Group();
        root.getChildren().add(canvas);

        // Tao scene
        Scene scene = new Scene(root);

        // Them scene vao stage
        stage.setScene(scene);
        stage.show();

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                update(now);
                render();
            }
        };
        timer.start();

        createMap();

        Bomber bomberman = new Bomber(1, 1, Sprite.player_right.getFxImage());
        bomberman.setMoveAnimation(Direction.LEFT, Animation.player_left.getFxImages());
        bomberman.setMoveAnimation(Direction.RIGHT, Animation.player_right.getFxImages());
        bomberman.setMoveAnimation(Direction.UP, Animation.player_up.getFxImages());
        bomberman.setMoveAnimation(Direction.DOWN, Animation.player_down.getFxImages());
        entities.add(bomberman);

        keyboard(scene, bomberman);

        DynamicObject bomb = new Bomb(10, 7, 0, Animation.bomb.getFxImages());
        entities.add(bomb);
        DynamicObject bomb1 = new Bomb(9, 6, 0, Animation.bomb.getFxImages());
        entities.add(bomb1);
        DynamicObject bomb2 = new Bomb(9, 7, 0, Animation.bomb.getFxImages());
        entities.add(bomb2);
        DynamicObject bomb3 = new Bomb(10, 6, 0, Animation.bomb.getFxImages());
        entities.add(bomb3);
    }

    public void createMap() {
        for (int i = 0; i < WIDTH; i++) {
            for (int j = 0; j < HEIGHT; j++) {
                Entity object;
                if (j == 0 || j == HEIGHT - 1 || i == 0 || i == WIDTH - 1) {
                    object = new Wall(i, j, Sprite.wall.getFxImage());
                } else {
                    object = new Grass(i, j, Sprite.grass.getFxImage());
                }
                entities.add(object);
            }
        }
    }

    public void update(long now) {
        for (Entity entity: entities) {
            entity.update(entities);
        }
    }

    public void render() {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        entities.forEach(g -> g.render(gc));
    }

    public void keyboard(Scene scene,Bomber bomberman) {
        scene.setOnKeyPressed(keyEvent -> {
            keyEvents.add(keyEvent);
            bomberman.press(keyEvent);
        });
        scene.setOnKeyReleased(keyEvent -> {
            bomberman.release(keyEvent);
            keyEvents.removeIf(e -> e.getCode().equals(keyEvent.getCode()));
            if (keyEvents.size() > 0) {
                bomberman.press(keyEvents.get(keyEvents.size() - 1));
            }
        });
    }
}
