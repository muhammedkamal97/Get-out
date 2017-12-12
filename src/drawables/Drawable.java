package drawables;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

import java.awt.*;

public interface Drawable {

    public void drawOnCanvas(GraphicsContext gc, Point pt, int width, int height);
    public Point getPosition();
    public void setPosition(Point position);
}
