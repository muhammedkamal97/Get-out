package drawables.obstacles.walls;

import View.Graphics.ImagesMaps.MazeMap;
import drawables.obstacles.Wall;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

import java.awt.*;

public class Bricks extends StandardWall implements Wall {

    private final int HEALTH_POINTS = 125;
    public Bricks() {
        setHealthPoints();
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
