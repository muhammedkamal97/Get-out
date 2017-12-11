package drawables.obstacles.traps;

import drawables.obstacles.Trap;
import maze.Maze;

import java.awt.*;

public abstract class StandardTrap implements Trap {

    private Point position;
    protected int damage;
    protected Maze maze;

    @Override
    public int getDamage() {
        return this.damage;
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
    public void setMaze(Maze maze) {
        this.maze = maze;
    }
    
}
