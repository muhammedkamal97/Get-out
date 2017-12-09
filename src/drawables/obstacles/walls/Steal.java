package drawables.obstacles.walls;

import drawables.obstacles.Wall;
import javafx.scene.canvas.Canvas;

import java.awt.*;

public class Steal implements Wall {

    private Point position;

    @Override
    public void takeBullet(int bulletDamage) {
        return;
    }

    @Override
    public void drawOnCanvas(Canvas canvas) {
        //TODO
    }

    @Override
    public Point getPosition() {
        return this.position;
    }

    @Override
    public void setPosition(Point position) {
        this.position = position;
    }

    @Override
    public void destroy() {
        return;
    }
}
