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
    @Override
    public void takeBullet(int bulletDamage) {
        return;
    }

    @Override
    public void drawOnCanvas(GraphicsContext gc, Point pt, int width, int height) {
        //TODO
    }

    /**
     * can't be destroyed
     */
    @Override
    public void destroy() {

        return;
    }
}
