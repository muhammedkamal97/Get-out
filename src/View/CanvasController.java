package View;

import View.Graphics.ImagesMaps.CharactersMap;
import View.Graphics.ImagesMaps.MazeMap;
import View.Graphics.Sprite.MySprite;
import drawables.Drawable;
import drawables.characters.Hero;
import drawables.characters.heros.Flash;
import drawables.characters.heros.Hulk;
import drawables.characters.heros.states.DirectionDownState;
import drawables.characters.heros.states.DirectionLeftState;
import drawables.characters.heros.states.DirectionRightState;
import drawables.characters.heros.states.DirectionUpState;
import drawables.roads.Road;
import gameCore.RunnerGameAdapter;
import gameLoop.GameLoop;
import javafx.animation.AnimationTimer;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import javax.swing.text.LabelView;
import java.awt.*;
import java.net.URL;
import java.util.ResourceBundle;

public class CanvasController implements Initializable {

    @FXML
    private Canvas dynamicCanvas;
    @FXML
    private Canvas steadyCanvas;
    @FXML
    private Canvas mazeCanvas;
    @FXML
    private ImageView weaponImage;
    @FXML
    private Label trias;
    @FXML
    private ProgressBar healthBar;
    private double initialHealth;
    private GraphicsContext gcD;
    private GraphicsContext gcM;
    private GraphicsContext gcS;   // two global parameters (dimensions cell)
    private int shiftDown = 30; //remove bar dimension
    private int cellWidth; // get it from system.sceendimensions
    private int cellHeight;
    private GameLoop gameLoop;
    private Hero hero;
    private Point currentPosition;

    private int x;
    private int y;

    //TODO
    //Weapons Icons //set in map
    //no draw function // need to remove draw function
    //bullet will be an ellipse moving
    //send hero as a parameter
    //modify stylesheet

    ////////////
    //TODO HERE
    //health bar //done
    //shield bar
    //score bar
    //Weapon Icon //done
    //Menu button return to main menu //done (only action missing)

    //important to instantiate graphics context
    @FXML
    protected void MenuButtonAction(ActionEvent event) {
//        animation();
    }

    @FXML
    public void keyPressedSwitch(KeyEvent event) {
        switch (event.getCode()) {
            case LEFT:
            case KP_LEFT:
                x -= 10;
                System.out.println(-cellWidth);
                System.out.println(x);
                if (x < (-cellWidth / 6)) {
                    gameLoop.moveHeroLeft();
                    if (!hero.getPosition().equals(currentPosition)) {
                        x += cellWidth;
                    } else {
                        x += 10;
                    }
                }
                gameLoop.lookLeft();
                hero.setDirectionState(new DirectionLeftState()); //testing
                currentPosition = hero.getPosition();
                System.out.println(currentPosition);
                hero.drawOnCanvas(gcD, new Point((int) (currentPosition.getX() * cellWidth + x),
                                (int) (currentPosition.getY() * cellHeight + y + shiftDown)),
                        cellWidth, cellHeight);
                break;
            case RIGHT:
            case KP_RIGHT:
                x += 10;
                System.out.println(x);
                if (x > cellWidth / 6) {
                    gameLoop.moveHeroRight();
                    if (!hero.getPosition().equals(currentPosition)) {
                        x -= cellWidth;
                    } else {
                        x -= 10;
                    }
                }
                gameLoop.lookRight();
                hero.setDirectionState(new DirectionRightState()); //testing
                currentPosition = hero.getPosition();
                System.out.println(currentPosition);
                hero.drawOnCanvas(gcD, new Point((int) (currentPosition.getX() * cellWidth + x),
                                (int) (currentPosition.getY() * cellHeight + y + shiftDown)),
                        cellWidth, cellHeight);
                break;
            case UP:
            case KP_UP:
                y -= 10;
                if (y < -(cellHeight / 6)) {
                    gameLoop.moveHeroUp();
                    if (!hero.getPosition().equals(currentPosition)) {
                        y += cellHeight;
                    } else {
                        y += 10;
                    }
                }
                gameLoop.lookUp();
                hero.setDirectionState(new DirectionUpState()); //testing
                currentPosition = hero.getPosition();
                hero.drawOnCanvas(gcD, new Point((int) (currentPosition.getX() * cellWidth + x),
                                (int) (currentPosition.getY() * cellHeight + y + shiftDown))
                        , cellWidth, cellHeight);
                break;
            case DOWN:
            case KP_DOWN:
                y += 10;
                if (y > cellHeight / 6) {
                    gameLoop.moveHeroDown();
                    if (!hero.getPosition().equals(currentPosition)) {
                        y -= cellHeight;
                    } else {
                        y -= 10;
                    }
                }
                gameLoop.lookDown();
                hero.setDirectionState(new DirectionDownState()); //testing
                currentPosition = hero.getPosition();
                hero.drawOnCanvas(gcD, new Point((int) (currentPosition.getX() * cellWidth + x),
                        (int) (currentPosition.getY() * cellHeight + y + shiftDown)), cellWidth, cellHeight);
                break;
            default:
                break;
        }

    }

    @FXML
    public void keyReleasedSwitch(KeyEvent event) {
//        switch (event.getCode()) {
//            case LEFT:
//            case KP_LEFT:
//                hero.drawOnReleased(gcD, new Point((int) (currentPosition.getX() * cellWidth + x),
//                        (int) (currentPosition.getY() * cellHeight + y + shiftDown)), cellWidth, cellHeight);
//                break;
//            case RIGHT:
//            case KP_RIGHT:
//                hero.drawOnReleased(gcD, new Point((int) (currentPosition.getX() * cellWidth + x),
//                        (int) (currentPosition.getY() * cellHeight + y + shiftDown)), cellWidth, cellHeight);
//                break;
//            case UP:
//            case KP_UP:
//                hero.drawOnReleased(gcD, new Point((int) (currentPosition.getX() * cellWidth + x),
//                        (int) (currentPosition.getY() * cellHeight + y + shiftDown)), cellWidth, cellHeight);
//                break;
//            case DOWN:
//            case KP_DOWN:
//                hero.drawOnReleased(gcD, new Point((int) (currentPosition.getX() * cellWidth + x),
//                        (int) (currentPosition.getY() * cellHeight + y + shiftDown)), cellWidth, cellHeight);
//                break;
//            case CONTROL:
//                gameLoop.shoot();
//                break;
//            case N:
//                gameLoop.holdNextWeapon();
//                break;
//            case P:
//                gameLoop.holdPreviousWeapon();
//                break;
//            default:
//                break;
//        }
    }

    private void setWeaponIcon() {
        String wep = hero.getCurrentWeapon().getClass().getSimpleName();
        weaponImage.setImage(MazeMap.getInstance().getBufferedImage(wep));
    }

    private void setHealthBar() {
        double currentHealth = hero.getHealthPoints();
        healthBar.setProgress(currentHealth / initialHealth);
    }

    //put animation in a class and calls it's methods
    private void animation() {
        new AnimationTimer() {
            long start = System.currentTimeMillis();
            boolean flag = false;

            public void handle(long currentNanoTime) {
                Image left = new Image("Trap.png");
                Image leftG = new Image("Gift.gif");
                long current = System.currentTimeMillis();
                if (!flag && (current - start >= 5000)) {
                    start = System.currentTimeMillis();
                    System.out.println("in");
                    gcM.clearRect(0, 0, dynamicCanvas.getWidth(), dynamicCanvas.getHeight());
                    gcM.drawImage(leftG, 64, 64, 50, 50);
                    flag = true;
                } else if (flag && (current - start >= 5000)) {
                    start = System.currentTimeMillis();
                    System.out.println("in1");
                    gcM.clearRect(0, 0, dynamicCanvas.getWidth(), dynamicCanvas.getHeight());
                    gcM.drawImage(left, 64, 64, 50, 50);
                    flag = false;
                }
            }
        }.start();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ///Abdelrahman

        hero = new Flash(); // sent from previous scene
//        hero = new Hulk();
        RunnerGameAdapter game = new RunnerGameAdapter();
        game.InitializeMaze(1);
        gameLoop = game.getGameLoop();
        gameLoop.setHero(hero);
        gameLoop.initiateLoop();

        setGlobalVariables();
        setMazeLayers();
        initialHealth = hero.getHealthPoints();
        x = 0;
        y = 0;
    }

    private void setGlobalVariables() {

        gcD = dynamicCanvas.getGraphicsContext2D();
        gcM = mazeCanvas.getGraphicsContext2D();
        gcS = steadyCanvas.getGraphicsContext2D();

        weaponImage.setImage(MazeMap.getInstance().getBufferedImage("Trap"));
        healthBar.setProgress(1.0);
        healthBar.setStyle("-fx-accent: red;");
//        Trials.setText(String.valueOf(hero.getTrials));

        Point pt = gameLoop.getMazeDimensions();
        shiftDown += ((dynamicCanvas.getHeight() - shiftDown) % (pt.getX())) / 2;
        cellHeight = (int) ((dynamicCanvas.getHeight() - shiftDown) / (pt.getX()));
        cellWidth = (int) ((dynamicCanvas.getWidth()) / (pt.getY()));
    }


    private void setMazeLayers() {
        setMovingObjectsLayer();
        setPickablesLayer();
        setRoadAndWallsLayer();
    }

    private void setMovingObjectsLayer() {
        Drawable[][] maze = gameLoop.getMovingObjectsLayer();
        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[i].length; j++) {
                if (maze[j][i] != null) {
                    maze[j][i].drawOnCanvas(gcD, new Point(
                                    (int) (maze[j][i].getPosition().getX() * cellWidth),
                                    (int) (maze[j][i].getPosition().getY() * cellHeight + shiftDown)),
                            cellWidth, cellHeight);
                }
            }
        }
    }

    private void setPickablesLayer() {
        Drawable[][] maze = gameLoop.getPickablesLayer();
        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[i].length; j++) {
                if (maze[j][i] != null) {
                    maze[j][i].drawOnCanvas(gcM, new Point(
                                    (int) (maze[j][i].getPosition().getX() * cellWidth),
                                    (int) (maze[j][i].getPosition().getY() * cellHeight + shiftDown)),
                            cellWidth, cellHeight);
                }
            }
        }
    }

    private void setRoadAndWallsLayer() {
        Drawable[][] maze = gameLoop.getRoadAndWallsLayer();
        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[i].length; j++) {
                if (maze[j][i] != null && !(maze[j][i] instanceof Road)) {
                    maze[j][i].drawOnCanvas(gcS, new Point(
                                    (int) (maze[j][i].getPosition().getX() * cellWidth),
                                    (int) (maze[j][i].getPosition().getY() * cellHeight + shiftDown)),
                            cellWidth, cellHeight);
                }
            }
        }
    }
}