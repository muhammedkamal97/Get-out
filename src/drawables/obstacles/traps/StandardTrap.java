package drawables.obstacles.traps;

import drawables.obstacles.Trap;
import maze.Maze;

import java.awt.*;

public abstract class StandardTrap implements Trap {

    private Point position;
    private int damage;
    protected Maze maze;
    private int healthPoints;

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

    @Override
    public void destroy() {
        maze.removeTrap(this);
    }

    @Override
    public void takeDamage(int damage) {
        healthPoints -= damage;
        if (healthPoints <= 0){
            this.destroy();
        }
    }

    protected void setDamage(){
        damage = getOriginalDamage();
    }

    protected void setHealthPoints(){
        healthPoints =getHealthPoints();
    }

    protected abstract int getOriginalDamage();
    protected abstract int getHealthPoints();




}
