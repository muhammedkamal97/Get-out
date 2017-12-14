package drawables.obstacles.walls;

import View.Graphics.ImagesMaps.MazeMap;
import drawables.obstacles.Wall;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

import java.awt.*;

public class Steel extends StandardWall implements Wall {

    /**
     * can't be destroyed
     * @param bulletDamage
     */
    private final int HEALTH_POINTS = 100;
    public Steel() {
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
    public void destroy() {
        return;
    }
}
