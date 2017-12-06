package drawables.characters;


import drawables.Drawable;
import drawables.characters.commands.Command;
import drawables.pickables.weapons.bullets.Bullet;
import maze.Maze;

import java.awt.*;

public interface MovingObject extends Drawable{

    public void move(Command moveCommand , Maze maze);
    public int getHealthPoints();
    public void setDirection(int direction);
    public int getDirection();
    public void takeBullet(Bullet bullet);
    public void receiveExplosionDamage(int damage);
}
