package drawables.obstacles;

import javafx.scene.canvas.GraphicsContext;
import maze.Maze;

import java.awt.*;

public class Gate implements Obstacle{
    private Point position;
    private Maze maze;


    @Override
    public void destroy() {
        //in destructible
    }

    @Override
    public void drawOnCanvas(GraphicsContext gc, Point pt, int widthCell, int heightCell) {

    }

    @Override
    public Point getPosition() {
        return position;
    }

    @Override
    public void setPosition(Point position) {
        this.position = position;
    }

    @Override
    public void takeDamage(int damage) {
        //in destructible
    }

    @Override
    public void setMaze(Maze maze) {
        this.maze = maze;
    }
}
