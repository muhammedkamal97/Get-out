package drawables.obstacles;

import maze.Maze;
import observer.ObservableExplosiveBomb;

public interface Bomb extends Obstacle, ObservableExplosiveBomb {

//    public void takeBullet(int bulletDamage); //parameter
    public void damageDrawableInExplosionRange();
    public void explode();
    public int getDamage();
    public void animateOnExplosion();

 }
