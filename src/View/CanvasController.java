package View;

import View.Graphics.ImagesMaps.CharactersMap;
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
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

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
    private GraphicsContext gcD;
    private GraphicsContext gcM;
    private GraphicsContext gcS;   // two global parameters (dimensions cell)
    private int shiftRight; //shift canvas dimension to be centered not needed
    //    private int shiftDown = 40; //remove bar dimension
    private int shiftDown = 0;
    private int cellWidth; // get it from system.sceendimensions
    private int cellHeight;
    private GameLoop gameLoop;
    private Hero hero;

    private int x;
    private int y;

    //important to instantiate graphics context
    @FXML
    protected void ButtonActionForNow(ActionEvent event) {

//        animation();
    }

    @FXML
    public void keyPressedSwitch(KeyEvent event) {
        switch (event.getCode()) {
            case LEFT:
            case KP_LEFT:
                x -= 10;
                if (x < cellWidth-1) {
                    gameLoop.moveHeroLeft();
                    x += cellWidth;
                }
                // my dimensions will be added to hero's point
                // divide my point by cell width & cell height

                gameLoop.lookLeft();
                hero.setDirectionState(new DirectionLeftState()); //testing
                hero.drawOnCanvas(gcD, new Point((int) (hero.getPosition().getX() * cellWidth + x),
                                (int) (hero.getPosition().getY() * cellHeight + y)),
                        cellWidth, cellHeight);
                break;
            case RIGHT:
            case KP_RIGHT:
                x += 10;
                if (x > 0) {
                    gameLoop.moveHeroRight();
                    x -= cellWidth;
                }
                gameLoop.lookRight();
                hero.setDirectionState(new DirectionRightState()); //testing
                hero.drawOnCanvas(gcD, new Point((int) (hero.getPosition().getX() * cellWidth + x),
                                (int) (hero.getPosition().getY() * cellHeight + y)),
                        cellWidth, cellHeight);
                break;
            case UP:
            case KP_UP:
                y -= 10;
                if (y < cellHeight-1) {
                    gameLoop.moveHeroUp();
                    y += cellHeight;
                }
                gameLoop.lookUp();
                hero.setDirectionState(new DirectionUpState()); //testing
                hero.drawOnCanvas(gcD, new Point((int) (hero.getPosition().getX() * cellWidth + x),
                                (int) (hero.getPosition().getY() * cellHeight + y))
                        , cellWidth, cellHeight);
                break;
            case DOWN:
            case KP_DOWN:
                y += 10;
                if (y > 0) {
                    gameLoop.moveHeroDown();
                    y -= cellHeight;
                }
                gameLoop.lookDown();
                hero.setDirectionState(new DirectionDownState()); //testing
                hero.drawOnCanvas(gcD, new Point((int) (hero.getPosition().getX() * cellWidth + x),
                        (int) (hero.getPosition().getY() * cellHeight + y)), cellWidth, cellHeight);
                break;
            default:
                break;
        }

    }

    @FXML
    public void keyReleasedSwitch(KeyEvent event) {
        switch (event.getCode()) {
            case LEFT:
            case KP_LEFT:
                hero.drawOnReleased(gcD, new Point((int) (hero.getPosition().getX() * cellWidth + x),
                        (int) (hero.getPosition().getY() * cellHeight + y)), cellWidth, cellHeight);
                break;
            case RIGHT:
            case KP_RIGHT:
                hero.drawOnReleased(gcD, new Point((int) (hero.getPosition().getX() * cellWidth + x),
                        (int) (hero.getPosition().getY() * cellHeight + y)), cellWidth, cellHeight);
                break;
            case UP:
            case KP_UP:
                hero.drawOnReleased(gcD, new Point((int) (hero.getPosition().getX() * cellWidth + x),
                        (int) (hero.getPosition().getY() * cellHeight + y)), cellWidth, cellHeight);
                break;
            case DOWN:
            case KP_DOWN:
                hero.drawOnReleased(gcD, new Point((int) (hero.getPosition().getX() * cellWidth + x),
                        (int) (hero.getPosition().getY() * cellHeight + y)), cellWidth, cellHeight);
                break;
            case CONTROL:
                gameLoop.shoot();
                break;
            case N:
                gameLoop.holdNextWeapon();
                break;
            case P:
                gameLoop.holdPreviousWeapon();
                break;
            default:
                break;
        }
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

//        hero = new Flash(); // sent from previous scene
        hero = new Hulk();
        RunnerGameAdapter game = new RunnerGameAdapter();
        game.InitializeMaze(1);
        gameLoop = game.getGameLoop();
        gameLoop.setHero(hero);
        gameLoop.initiateLoop();

        setGlobalVariables();
        setMazeLayers();
        x = (int) hero.getPosition().getX()-1;
        y = (int) hero.getPosition().getY() + shiftDown -1;
//        x = 0;
//        y = shiftDown;
    }

    private void setGlobalVariables() {
        gcD = dynamicCanvas.getGraphicsContext2D();
        gcM = mazeCanvas.getGraphicsContext2D();
        gcS = steadyCanvas.getGraphicsContext2D();
//        shiftRight = ((int) (dynamicCanvas.getWidth() - dynamicCanvas.getHeight())) / 2;
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