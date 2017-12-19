package View;

import javafx.animation.AnimationTimer;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class StatusTimerForAnimation extends AnimationTimer {

    private volatile boolean running;

    private Point pt1;
    private Point pt2;
    private Point pt3;
    private ArrayList<BufferedImage> listblue;
    private ArrayList<BufferedImage> listred;
    private ArrayList<BufferedImage> listgreen;
    private long start;
    private int count;
    private GraphicsContext gcAnimation;
    private int width;
    private int height;
    private Image img;

    public StatusTimerForAnimation(Point pt1,Point pt2, Point pt3,
                                   GraphicsContext gcAnimation, int width, int height) {
        this.start = System.currentTimeMillis();
        this.count = 0;
        this.pt1 = pt1;
        this.pt3 = pt3;
        this.pt2 = pt2;
        this.width = width;
        this.height = height;
        this.gcAnimation = gcAnimation;
        System.out.println("blue constructed");
        listblue = new ArrayList<>();
        listgreen = new ArrayList<>();
        listred = new ArrayList<>();
        img = new Image("Fireworks.png");
        BufferedImage buffImg = SwingFXUtils.fromFXImage(img, null);
        int TileW = (int) img.getWidth() / 3;
        int TileH = (int) img.getHeight() / 6;
        for (int j = 0; j < 6; j++) {
            listblue.add(buffImg.getSubimage(0 * TileW, j * TileH, TileW, TileH));
        }
        for (int j = 0; j < 6; j++) {
            listred.add(buffImg.getSubimage(1 * TileW, j * TileH, TileW, TileH));
        }
        for (int j = 0; j < 6; j++) {
            listgreen.add(buffImg.getSubimage(2 * TileW, j * TileH, TileW, TileH));
        }
    }

    @Override
    public void handle(long currentNanoTime) {
        long current = System.currentTimeMillis();
        System.out.println("handle");
        if (current - start >= 200) {
            start = System.currentTimeMillis();
            gcAnimation.clearRect(pt1.x, pt1.y,
                    400, 400);
            Image imgTo = SwingFXUtils.toFXImage(listblue.get(count), null);
            gcAnimation.drawImage(imgTo, pt1.x, pt1.y,
                    400, 400);
            gcAnimation.clearRect(pt2.x, pt2.y,
                    400, 400);
            imgTo = SwingFXUtils.toFXImage(listgreen.get(count), null);
            gcAnimation.drawImage(imgTo, pt2.x, pt2.y,
                    400, 400);
            gcAnimation.clearRect(pt3.x, pt3.y,
                    400, 400);
            imgTo = SwingFXUtils.toFXImage(listred.get(count), null);
            gcAnimation.drawImage(imgTo, pt3.x, pt3.y,
                    400, 400);
            count++;
            System.out.println("blue is in");
        }
        if (count == listblue.size()) {
            gcAnimation.clearRect(0, 0,
                    width / 4, height);
            System.out.println("blue stops");
            this.stop();
        }
    }

    @Override
    public void start() {
        super.start();
        running = true;
    }

    @Override
    public void stop() {
        super.stop();
        running = false;
    }

    public boolean isRunning() {
        return running;
    }
}
