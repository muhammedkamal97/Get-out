package View;

import View.Graphics.ImagesMaps.CharactersMap;
import View.Graphics.Sprite.MySprite;
import drawables.characters.Hero;
import drawables.characters.heros.Flash;
import drawables.characters.heros.states.DirectionDownState;
import drawables.characters.heros.states.DirectionLeftState;
import drawables.characters.heros.states.DirectionRightState;
import drawables.characters.heros.states.DirectionUpState;
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
    private int shift;
    private int i = 0;
    private Flash flash;


    private MySprite sprite = new MySprite();
    private int x = 100; // remove it
    private int y = 100; // remove it

    //important to instantiate graphics context
    @FXML
    protected void ButtonActionForNow(ActionEvent event) {

//        animation();
    }

    @FXML
    public void keyPressedSwitch(KeyEvent event) {
        switch (event.getCode()) {
            case J:
            case LEFT:
            case KP_LEFT:
                x -= 1;
                flash.setDirectionState(new DirectionLeftState()); //testing
                flash.drawOnCanvas(gcD,new Point(x,y), 100, 100);
                break;
            case L:
            case RIGHT:
            case KP_RIGHT:
                x += 1;
                flash.setDirectionState(new DirectionRightState()); //testing
                flash.drawOnCanvas(gcD, new Point(x,y),100, 100);
                break;
            case I:
            case UP:
            case KP_UP:
                y -= 1;
                flash.setDirectionState(new DirectionUpState()); //testing
                flash.drawOnCanvas(gcD, new Point(x,y),100, 100);
                break;
            case K:
            case DOWN:
            case KP_DOWN:
                y += 1;
                flash.setDirectionState(new DirectionDownState()); //testing
                flash.drawOnCanvas(gcD,new Point(x,y), 100, 100);
                break;
            default:
                break;
        }

    }

    @FXML
    public void keyReleasedSwitch(KeyEvent event) {
        switch (event.getCode()) {
            case J:
            case LEFT:
            case KP_LEFT:
                flash.setDirectionState(new DirectionLeftState()); //testing
                flash.drawOnReleased(gcD,new Point(x,y), 100, 100);
                break;
            case L:
            case RIGHT:
            case KP_RIGHT:
                flash.setDirectionState(new DirectionRightState()); //testing
                flash.drawOnReleased(gcD,new Point(x,y), 100, 100);
                break;
            case I:
            case UP:
            case KP_UP:
                flash.setDirectionState(new DirectionUpState()); //testing
                flash.drawOnReleased(gcD,new Point(x,y), 100, 100);
                break;
            case K:
            case DOWN:
            case KP_DOWN:
                flash.setDirectionState(new DirectionDownState()); //testing
                flash.drawOnReleased(gcD,new Point(x,y), 100, 100);
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

//        Hero flash = new Flash();
//        RunnerGameAdapter game = new RunnerGameAdapter();
//        game.InitializeMaze(1);
//        GameLoop gl = game.getGameLoop();
//        gl.setHero(flash);
//        gl.initiateLoop();
        flash = new Flash();



        gcD = dynamicCanvas.getGraphicsContext2D();
        gcM = mazeCanvas.getGraphicsContext2D();
        gcS = steadyCanvas.getGraphicsContext2D();
        shift = ((int) (dynamicCanvas.getWidth() - dynamicCanvas.getHeight())) / 2;
    }
}