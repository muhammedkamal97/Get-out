package drawables.pickables.weapons.bullets.bulletDirectionStates;

import drawables.pickables.Weapon;
import drawables.pickables.weapons.bullets.Bullet;
import drawables.pickables.weapons.bullets.BulletShootingProperties;
import maze.Maze;

import java.awt.*;

public class ShootDown extends Thread implements ShootingDirectionState{
    @Override
    public void startMotion(Point position, Bullet bullet, Weapon weapon) {
        BulletShootingProperties properties = new BulletShootingProperties(position,bullet,weapon,this);
        weapon.initiateBulletThread(properties);
    }
}
