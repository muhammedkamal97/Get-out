package View;

import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;

public class Controller {

    @FXML
    public Canvas heroCanvas;
    @FXML
    public Canvas mazeCanvas;
    private GraphicsContext gcH;
    private GraphicsContext gcM;
    private int i = 10; // remove it

    //important to instantiate graphics context
    @FXML
    protected void ButtonActionForNow(ActionEvent event) {
        gcH = heroCanvas.getGraphicsContext2D();
        gcM = mazeCanvas.getGraphicsContext2D();
        gcM.setFill(Color.GREEN);
        gcM.fillOval(50, 100, 200, 200);
        gcH.setFill(Color.RED);
        gcH.fillRect(300, 100, 200, 200);
        i = 0;
        animation();
    }

    @FXML
    public void keyPressedSwitch(KeyEvent event) {
        gcH = heroCanvas.getGraphicsContext2D();
        gcM = mazeCanvas.getGraphicsContext2D();
        switch (event.getCode()) {
            case J:
            case LEFT:
            case KP_LEFT:
                i += 10;
                gcH.clearRect(0, 0, heroCanvas.getHeight(), heroCanvas.getWidth());
                gcH.fillOval(400 - i, 100, 100, 100);
                System.out.println("to the left");
                break;
            case L:
            case RIGHT:
            case KP_RIGHT:
                i += 10;
                gcH.clearRect(0, 0, heroCanvas.getHeight(), heroCanvas.getWidth());
                gcH.fillOval(400 + i, 100, 100, 100);
                break;
            case I:
            case UP:
            case KP_UP:
                i += 10;
                gcH.clearRect(0, 0, heroCanvas.getHeight(), heroCanvas.getWidth());
                gcH.fillOval(400, 100 - i, 100, 100);
                break;
            case K:
            case DOWN:
            case KP_DOWN:
                i += 10;
                gcH.clearRect(0, 0, heroCanvas.getHeight(), heroCanvas.getWidth());
                gcH.fillOval(400, 100 + i, 100, 100);
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
                    gcM.clearRect(0, 0, heroCanvas.getHeight(), heroCanvas.getWidth());
                    gcM.drawImage(leftG, 64, 64);
                    flag = true;
                } else if (flag && (current - start >= 5000)) {
                    start = System.currentTimeMillis();
                    System.out.println("in1");
                    gcM.clearRect(0, 0, heroCanvas.getHeight(), heroCanvas.getWidth());
                    gcM.drawImage(left, 64, 64);
                    flag = false;
                }
            }
        }.start();
    }
}