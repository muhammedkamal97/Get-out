package drawables;

import javafx.scene.canvas.Canvas;

import java.awt.*;

public interface Drawable {

    public void drawOnCanvas(Canvas canvas);
    public Point getPosition();
    public void setPosition(Point position);
}
