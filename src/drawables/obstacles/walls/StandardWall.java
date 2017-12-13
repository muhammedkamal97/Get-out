package drawables.obstacles.walls;

import drawables.obstacles.Wall;
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




    protected void setHealthPoints(){
        healthPoints =getHealthPoints();
    }
    protected abstract int getHealthPoints();
}
