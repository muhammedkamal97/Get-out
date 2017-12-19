package drawables.pickables;

import drawables.pickables.weapons.bullets.Bullet;
import drawables.pickables.weapons.bullets.BulletShootingProperties;
import drawables.pickables.weapons.bullets.bulletDirectionStates.ShootingDirectionState;
import maze.Maze;
import observer.BulletMotionObservable;

import java.awt.*;

public interface Weapon extends Pickable, BulletMotionObservable{
    public void shoot(ShootingDirectionState state, Point initialPosition);
    public void reload();
    public int getNumberOfBullets();
    public void initiateBulletThread(BulletShootingProperties shootingProperties);
}
