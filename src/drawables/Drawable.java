package drawables;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import maze.Maze;

import java.awt.*;

public interface Drawable {

    public void drawOnCanvas(GraphicsContext gc, Point pt, int widthCell, int heightCell);

    public Point getPosition();

    public void setPosition(Point position);

    public void takeDamage(int damage);

    public void setMaze(Maze maze);
}
