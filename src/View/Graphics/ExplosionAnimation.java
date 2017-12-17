package View.Graphics;


import com.sun.xml.internal.bind.v2.runtime.output.SAXOutput;
import javafx.animation.AnimationTimer;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class ExplosionAnimation {

    private Point position;
    private ArrayList<BufferedImage> list;

    public ExplosionAnimation(Point pt) {
        this.position = pt;
        this.list = new ArrayList<>();
        constructSprite();
    }

    private void constructSprite() {
        Image img = new Image("expl2.png");
        BufferedImage buffImg = SwingFXUtils.fromFXImage(img, null);
        int TileW = (int) img.getWidth() / 5;
        int TileH = (int) img.getHeight() / 5;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 5; j++) {
                list.add(buffImg.getSubimage(j * TileW, i * TileH, TileW, TileH));
            }
        }
        for (int j = 0; j < 4; j++) {
            list.add(buffImg.getSubimage(j * TileW, 4 * TileH, TileW, TileH));
        }
    }

    public void startAnimation(GraphicsContext gc, int cellWidth, int cellHeight, int range) {
        new AnimationTimer() {
            long start = System.currentTimeMillis();
            int count = 0;

            public void handle(long currentNanoTime) {
                long current = System.currentTimeMillis();
                if (current - start >= 200) {
                    start = System.currentTimeMillis();
                    gc.clearRect(position.getX()*cellWidth - (range * cellWidth-cellWidth/2),
                            position.getY()*cellHeight - (range * cellHeight-cellHeight/2),
                            range*2 * cellWidth, range*2 * cellHeight);
                    Image imgTo = SwingFXUtils.toFXImage(list.get(count), null);
                    gc.drawImage(imgTo,
                            position.getX()*cellWidth - (range * cellWidth-cellWidth/2),
                            position.getY()*cellHeight - (range * cellHeight-cellHeight/2),
                            range*2 * cellWidth, range*2 * cellHeight);
                    count++;
                }
                if (count == list.size()) {
                    gc.clearRect(position.getX()*cellWidth - (range * cellWidth-cellWidth/2),
                            position.getY()*cellHeight - (range * cellHeight-cellHeight/2),
                            range*2 * cellWidth, range*2 * cellHeight);
                    this.stop();
                }
            }
        }.start();
    }
}
