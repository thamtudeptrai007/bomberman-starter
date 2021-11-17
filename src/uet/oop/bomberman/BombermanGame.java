package uet.oop.bomberman;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import uet.oop.bomberman.entities.*;
import uet.oop.bomberman.graphics.Animation;
import uet.oop.bomberman.graphics.Sprite;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BombermanGame extends Application {

    public static final int WIDTH = 31;
    public static final int HEIGHT = 13;
    public static final int FPS = 24;
    public static final long TPF = 1_000_000_000 / FPS;

    private GraphicsContext gc;
    private Canvas canvas;
    private List<Entity> entities = new ArrayList<>();
    private List<Entity> stillObjects = new ArrayList<>();
    private List<KeyEvent> keyEvents = new ArrayList<>();


    public static void main(String[] args) {
        Application.launch(BombermanGame.class);
    }

    @Override
    public void start(Stage stage) throws IOException {
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
            private long lastUpdate = 0;

            @Override
            public void handle(long now) {
                if (now - lastUpdate >= TPF) {
                    update(now);
                    render();
                    lastUpdate = now;
                }
            }
        };
        timer.start();

        createMap(scene);
    }

    public void createMap(Scene scene) throws IOException {
        FileInputStream fis = new FileInputStream("res/levels/Level1.txt");
        Scanner scanner = new Scanner(fis);
        int level = scanner.nextInt();
        int height = scanner.nextInt();
        int width = scanner.nextInt();
        char[][] map = new char[width][height];
        scanner.nextLine();
        String line;
        for (int i = 0; i < height; i++) {
            line = scanner.nextLine();
            for (int j = 0; j < line.length(); j++)
                map[j][i] = line.charAt(j);
        }

        for (int i = 0; i < WIDTH; i++) {
            for (int j = 0; j < HEIGHT; j++) {
                Entity object;
                if (j == 0 || j == HEIGHT - 1 || i == 0 || i == WIDTH - 1) {
                    object = new Wall(i, j, Sprite.wall.getFxImage());
                } else {
                    object = new Grass(i, j, Sprite.grass.getFxImage());
                }
                stillObjects.add(object);
            }
        }

        for (int y = 0; y < height; y++)
            for (int x = 0; x < width; x++) {
                Entity object = null;
                switch (map[x][y]) {
                    case '#':
                        object = new Wall(x, y, Sprite.wall.getFxImage());
                        break;
                    case '*':
                        object = new Brick(x, y, Sprite.brick.getFxImage());
                        break;
                    case 'x':
                        object = new Brick(x, y, Sprite.brick.getFxImage());
                        break;
                    case 'b':
                        entities.add(new BombItem(x, y, Sprite.powerup_bombs.getFxImage()));
                        object = new Brick(x, y, Sprite.brick.getFxImage());
                        break;
                    case 'f':
                        entities.add(new FlameItem(x, y, Sprite.powerup_flames.getFxImage()));
                        object = new Brick(x, y, Sprite.brick.getFxImage());
                        break;
                    case 's':
                        entities.add(new SpeedItem(x, y, Sprite.powerup_speed.getFxImage()));
                        object = new Brick(x, y, Sprite.brick.getFxImage());
                        break;
                    case 'p':
                        Bomber bomber = new Bomber(x, y, Sprite.player_right.getFxImage());
                        bomber.setMoveAnimation(Direction.LEFT, Animation.player_left.getFxImages());
                        bomber.setMoveAnimation(Direction.RIGHT, Animation.player_right.getFxImages());
                        bomber.setMoveAnimation(Direction.UP, Animation.player_up.getFxImages());
                        bomber.setMoveAnimation(Direction.DOWN, Animation.player_down.getFxImages());
                        bomber.setDeadAnimation(Animation.player_dead.getFxImages());
                        keyboard(scene, bomber);
                        object = bomber;
                        break;
                    case '1':
                        Balloom balloom = new Balloom(x, y, Animation.balloom_left.getFxImages());
                        balloom.setMoveAnimation(Direction.LEFT, Animation.balloom_left.getFxImages());
                        balloom.setMoveAnimation(Direction.RIGHT, Animation.balloom_right.getFxImages());
                        balloom.setMoveAnimation(Direction.UP, Animation.balloom_right.getFxImages());
                        balloom.setMoveAnimation(Direction.DOWN, Animation.balloom_left.getFxImages());
                        balloom.setDeadAnimation(Animation.balloom_dead.getFxImages());
                        object = balloom;
                        break;
                    case '2':
                        Oneal oneal = new Oneal(x, y, Animation.oneal_left.getFxImages());
                        oneal.setMoveAnimation(Direction.LEFT, Animation.oneal_left.getFxImages());
                        oneal.setMoveAnimation(Direction.RIGHT, Animation.oneal_right.getFxImages());
                        oneal.setMoveAnimation(Direction.UP, Animation.oneal_right.getFxImages());
                        oneal.setMoveAnimation(Direction.DOWN, Animation.oneal_left.getFxImages());
                        oneal.setDeadAnimation(Animation.oneal_dead.getFxImages());
                        object = oneal;
                        break;
                    default:
                }
                if (object != null) {
                    entities.add(object);
                }
            }
    }

    public void update(long now) {
        boolean isOverGame = true;
        for (int i = 0; i < entities.size(); i++) {
            if (entities.get(i) instanceof Bomber) {
                isOverGame = false;
            }
            entities.get(i).update(entities, now);
        }
        if (isOverGame) {
            System.out.println("Game over");
            System.exit(0);
        }
    }

    public void render() {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        stillObjects.forEach(g -> g.render(gc));
        entities.forEach(g -> g.render(gc));
    }

    public void keyboard(Scene scene, Bomber bomberman) {
        scene.setOnKeyPressed(keyEvent -> {
            bomberman.press(keyEvent, keyEvents.isEmpty()
                    && (keyEvent.getCode() == KeyCode.LEFT
                    || keyEvent.getCode() == KeyCode.RIGHT
                    || keyEvent.getCode() == KeyCode.DOWN
                    || keyEvent.getCode() == KeyCode.UP));
            keyEvents.add(keyEvent);
        });
        scene.setOnKeyReleased(keyEvent -> {
            bomberman.release(keyEvent);
            keyEvents.removeIf(e -> e.getCode().equals(keyEvent.getCode()));
            if (keyEvents.size() > 0) {
                bomberman.press(keyEvents.get(0), true);
            }
        });
    }
}
