package drawables.roads;

import java.awt.Point;

import drawables.Drawable;
import javafx.scene.canvas.Canvas;

public class Road implements Drawable {

    private Point position;

    @Override
    public void drawOnCanvas(Canvas canvas) {

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
