package View;

import View.Graphics.Sprite.MySprite;
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

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable{

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


    private MySprite sprite = new MySprite();
    private int i = 10; // remove it



    //important to instantiate graphics context
    @FXML
    protected void ButtonActionForNow(ActionEvent event) {

        gcM.setFill(Color.GREEN);
        gcM.fillOval(50, 100, 200, 200);
        gcD.setFill(Color.RED);
        gcD.fillRect(300, 100, 200, 200);
        i = 0;
        Image img = new Image("run.png");
        sprite.SpriteSheetBuffer((int) img.getWidth(), (int) img.getHeight(),
                4, 4, SwingFXUtils.fromFXImage(img, null));

//        animation();
    }

    @FXML
    public void keyPressedSwitch(KeyEvent event) {
        switch (event.getCode()) {
            case J:
            case LEFT:
            case KP_LEFT:
                i += 10;
                gcD.clearRect(0, 0, dynamicCanvas.getWidth(), dynamicCanvas.getHeight());
                gcD.fillOval(400 - i, 100, 100, 100);
                System.out.println("to the left");
                break;
            case L:
            case RIGHT:
            case KP_RIGHT:
                i += 10;
                gcD.clearRect(0, 0, dynamicCanvas.getWidth(), dynamicCanvas.getHeight());
                gcD.fillOval(400 + i, 100, 100, 100);
                break;
            case I:
            case UP:
            case KP_UP:
                i += 10;
                gcD.clearRect(0, 0, dynamicCanvas.getWidth(), dynamicCanvas.getHeight());
                gcD.fillOval(400, 100 - i, 100, 100);
                break;
            case K:
            case DOWN:
            case KP_DOWN:
                i += 10;
                gcD.clearRect(0, 0, dynamicCanvas.getWidth(), dynamicCanvas.getHeight());
                gcD.fillOval(400, 100 + i, 100, 100);
                break;
            case P: // to be removed
                gcD.clearRect(0, 0, dynamicCanvas.getWidth(), dynamicCanvas.getHeight());
//                sprite.drawNextSprite(gcD, 125, 640,64);

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
                Image left = new Image("heart.png");
                Image leftG = new Image("Cod.png");
                long current = System.currentTimeMillis();
                if (!flag && (current - start >= 5000)) {
                    start = System.currentTimeMillis();
                    System.out.println("in");
                    gcM.clearRect(0, 0, dynamicCanvas.getWidth(), dynamicCanvas.getHeight());
                    gcM.drawImage(leftG, 64, 64);
                    flag = true;
                } else if (flag && (current - start >= 5000)) {
                    start = System.currentTimeMillis();
                    System.out.println("in1");
                    gcM.clearRect(0, 0, dynamicCanvas.getWidth(), dynamicCanvas.getHeight());
                    gcM.drawImage(left, 64, 64);
                    flag = false;
                }
            }
        }.start();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        gcD = dynamicCanvas.getGraphicsContext2D();
        gcM = mazeCanvas.getGraphicsContext2D();
        gcS = steadyCanvas.getGraphicsContext2D();
        shift = ((int)(dynamicCanvas.getWidth() - dynamicCanvas.getHeight()))/2;
    }
}