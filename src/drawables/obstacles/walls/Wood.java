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
    public void drawOnCanvas(GraphicsContext gc, Point pt, int widthCell, int heightCell) {
        MazeMap map = MazeMap.getInstance();
        gc.drawImage(map.getBufferedImage("WoodWall1"),
                pt.getX() - (widthCell / 2),
                pt.getY() - (heightCell / 2),
                widthCell, heightCell);
    }

    @Override
    public void destroy() {
        return;
    }
}
