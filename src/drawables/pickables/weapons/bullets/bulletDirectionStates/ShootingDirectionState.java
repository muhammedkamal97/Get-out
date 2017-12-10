package drawables.pickables.weapons.bullets.bulletDirectionStates;

import drawables.pickables.weapons.bullets.Bullet;

import java.awt.*;

public interface ShootingDirectionState {

    public void startMotion(Point position , Bullet bullet);
}
