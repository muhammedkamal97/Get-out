package drawables.obstacles.bombs;

import drawables.characters.Hero;
import drawables.obstacles.Bomb;
import maze.Maze;

import java.awt.*;

public abstract class StandardBomb implements Bomb {

    private Point position;
    private int healthPoints;
    private int range;
    private int temp;
    private int damage;
    private Maze maze;

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

    @Override
    public void takeBullet(int bulletDamage) {
        this.healthPoints -= bulletDamage;
        if (this.healthPoints <= 0) {
            this.explode();
        }
    }


    @Override
    public void explode() {
        this.animateOnExplosion();
        this.damageDrawableInExplosionRange();
        this.destroy();
    }

    @Override
    public void takeDamage(int damage) {
        this.healthPoints -= damage;
        if (healthPoints <= 0){
            explode();
        }
    }

    @Override
    public void damageDrawableInExplosionRange() {

        for (int i = (-range); i <= range; i++) {
            for (int j = (-range); j <= range; j++) {
                maze.getItemInPosition(new Point(position.x +i,position.y + j))
                        .takeDamage(damage);
                //display black marks for explosion;
            }
        }

    }


    @Override
    public void destroy() {

        //add a gift
        maze.removeBomb(this);
        return;
    }


    protected void setDamage() {
        damage = getBombOriginalDamage();
    }

    protected void setBombRange() {
        damage = getBombRange();
    }

    protected void setHealthPoints(){
        healthPoints = getHealthPoints();
    }

    protected Maze getMaze(){
        return maze;
    }


    protected abstract int getHealthPoints();
    protected abstract int getBombOriginalDamage();
    protected abstract int getBombRange();

}
