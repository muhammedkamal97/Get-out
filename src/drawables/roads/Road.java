package drawables.roads;

import java.awt.Point;
import java.awt.image.BufferedImage;

import View.Animation.MazeLayer;
import drawables.Drawable;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Road implements Drawable {

    private Point position;
    private BufferedImage roadImg;

    public Road () {
        MazeLayer layer = new MazeLayer();
        roadImg = layer.getBufferedImage("road");
    }

    @Override
    public void drawOnCanvas(GraphicsContext gc, Point pt, int width, int height) {
        Image img = SwingFXUtils.toFXImage(roadImg, null );

        gc.drawImage(img, pt.getX(), pt.getY(), width, height );
        //get the road image and put on canvas

    }

    @Override
    public Point getPosition() {
        return this.position;
    }

    @Override
    public void setPosition(Point position) {
        this.position = position;
    }

}
