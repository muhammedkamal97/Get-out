package drawables.obstacles.bombs;

import drawables.obstacles.Bomb;
import maze.Maze;

import java.awt.*;

public abstract class StandardBomb implements Bomb {

    private Point position;
    protected int damage;
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
    public int getDamage() {
        return this.damage;
    }

    @Override
    public void setMaze(Maze maze) {
        this.maze = maze;
    }
}
