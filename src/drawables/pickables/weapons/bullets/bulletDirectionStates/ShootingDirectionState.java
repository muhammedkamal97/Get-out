package drawables.pickables.weapons.bullets.bulletDirectionStates;

import drawables.pickables.Weapon;
import drawables.pickables.weapons.bullets.Bullet;
import maze.Maze;

import java.awt.*;

public interface ShootingDirectionState {

    public void startMotion(Point position , Bullet bullet, Weapon weapon);
}
