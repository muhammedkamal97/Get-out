package drawables.obstacles;

import maze.Maze;

public interface Bomb extends Obstacle {

//    public void takeBullet(int bulletDamage); //parameter
    public void damageDrawableInExplosionRange();
    public void explode();
    public int getDamage();
    public void animateOnExplosion();

 }
