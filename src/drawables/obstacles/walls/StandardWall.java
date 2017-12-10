package drawables.obstacles.walls;

import drawables.obstacles.Wall;
import maze.Maze;

import java.awt.*;

public abstract class StandardWall implements Wall {

    private Point position;
    protected Maze maze;

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


}
