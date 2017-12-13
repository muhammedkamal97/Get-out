package drawables.obstacles.walls;

import drawables.obstacles.Wall;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

import java.awt.*;

public class Steal extends StandardWall implements Wall {

    /**
     * can't be destroyed
     * @param bulletDamage
     */
    private final int HEALTH_POINTS = 100;
    public Steal() {
        setHealthPoints();
    }

    @Override
    public void takeDamage(int damage) {
        //do nothing
    }



    @Override
    protected int getHealthPoints() {
        return HEALTH_POINTS;
    }

    @Override
    public void drawOnCanvas(GraphicsContext gc, Point pt, int width, int height) {
        //TODO
    }

    @Override
    public void destroy() {
        return;
    }
}
