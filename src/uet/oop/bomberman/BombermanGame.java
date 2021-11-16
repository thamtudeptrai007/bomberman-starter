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

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BombermanGame extends Application {

    public static final int WIDTH = 20;
    public static final int HEIGHT = 15;
    public static final int FPS = 15;
    public static final long TPS = 1_000_000_000 / FPS;

    private GraphicsContext gc;
    private Canvas canvas;
    private List<Entity> entities = new ArrayList<>();
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
                if (now - lastUpdate >= TPS) {
                    update(now);
                    render();
                    lastUpdate = now;
                }
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
    }

    public void createMap() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("res/levels/Level1.txt"));
        int level = Integer.parseInt(br.readLine());
        int height = Integer.parseInt(br.readLine());
        int width = Integer.parseInt(br.readLine());
        char [][] map = new char[height][width];
        String line;
        int rowNum = 0;
        while ((line = br.readLine()) != null) {
            for (int i = 0; i < line.length(); i++) {
                map[rowNum][i] = line.charAt(i);
            }
            rowNum++;
        }

        for (int x = 0; x < height; x++)
            for (int y = 0; y < width; y++) {
                Entity object = null;
                switch (map[x][y]) {
                    case '#':
                        object = new Wall(x, y, Sprite.wall.getFxImage());
                        break;
                    case '*':
                        object = new Brick(x, y, Sprite.brick.getFxImage());
                        break;
                    //case 'x':
                    //case '1':
                    //case 'b':
                    //case 'f':
                    //case 's':
                        //break;
                    //case 'p':
                        //object = new Bomber(x, y, Sprite.bom)
                        //break;
                    //case '2':
                        //object = new
                        //break;
                    default:
                        object = new Grass(x, y, Sprite.grass.getFxImage());
                        break;
                }
                entities.add(object);
            }
        /*for (int i = 0; i < WIDTH; i++) {
            for (int j = 0; j < HEIGHT; j++) {
                Entity object;
                if (j == 0 || j == HEIGHT - 1 || i == 0 || i == WIDTH - 1) {
                    object = new Wall(i, j, Sprite.wall.getFxImage());
                } else {
                    object = new Grass(i, j, Sprite.grass.getFxImage());
                }
                entities.add(object);
            }
        }*/
    }

    public void update(long now) {
        for (int i = 0; i < entities.size(); i++) {
            entities.get(i).update(entities, now);
        }
    }

    public void render() {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        entities.forEach(g -> g.render(gc));
    }

    public void keyboard(Scene scene, Bomber bomberman) {
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
