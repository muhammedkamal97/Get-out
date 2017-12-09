package drawables.obstacles;

import maze.Maze;

public interface Bomb extends Obstacle {

    public void takeBullet(int bulletDamage);
    public void damageDrawableInExplosionRange(Maze maze);
    public void explode();
    public int getDamage();

 }
