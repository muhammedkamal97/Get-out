package drawables.obstacles.walls;

import View.Graphics.ImagesMaps.MazeMap;
import drawables.obstacles.Wall;
import javafx.scene.canvas.GraphicsContext;
import maze.Maze;

import java.awt.*;

public abstract class StandardWall implements Wall {

    private Point position;
    protected Maze maze;
    private int healthPoints;


    @Override
    public Point getPosition() {
        return this.position;
    }

    @Override
    public void setPosition(Point position) {
        this.position = position;
    }

    @Override
    public void setMaze(Maze maze) {
        this.maze = maze;
    }

    public void takeDamage(int damage) {
        healthPoints -= damage;
        if(healthPoints <= 0) {
            maze.removeWall(this);
            destroy();
        }
    }

    @Override
    public void drawOnCanvas(GraphicsContext gc, Point pt, int widthCell, int heightCell) {
        MazeMap map = MazeMap.getInstance();
        gc.drawImage(map.getBufferedImage(this.getClass().getName()),
                pt.getX() - (widthCell / 2),
                pt.getY() - (heightCell / 2),
                widthCell, heightCell);
    }

    protected void setHealthPoints(){
        healthPoints =getHealthPoints();
    }
    protected abstract int getHealthPoints();
}
