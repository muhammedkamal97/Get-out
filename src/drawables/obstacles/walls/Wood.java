package drawables.obstacles.walls;

import View.Graphics.ImagesMaps.MazeMap;
import drawables.obstacles.Wall;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

import java.awt.*;

public class Wood extends StandardWall implements Wall {

    private final int HEALTH_POINTS = 80;
    public Wood() {
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
