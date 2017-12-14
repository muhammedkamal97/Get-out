package drawables.roads;

import java.awt.Point;
import View.Graphics.ImagesMaps.MazeMap;
import drawables.Drawable;
import javafx.scene.canvas.GraphicsContext;

public class Road implements Drawable {

    private Point position;

    @Override
    public void drawOnCanvas(GraphicsContext gc, Point pt, int widthCell, int heightCell) {
        MazeMap map = MazeMap.getInstance();
        gc.drawImage(map.getBufferedImage("GrassRoad"),
                pt.getX() - (widthCell / 2),
                pt.getY() - (heightCell / 2),
                widthCell, heightCell);
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
    public void takeDamage(int damage) {
        return;
        //do nothing its un destroyable or change into black
    }

}
