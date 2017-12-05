package drawables.obstacles;


import drawables.Drawable;
import drawables.characters.Hero;
import maze.Maze;

public interface Bomb extends Obstacle {

    public void takeBullet();
    public void damageDrawableInExplosionRange(Drawable drawable);
    public void explode();

 }
