package drawables.pickables.weapons.bullets;

import drawables.pickables.weapons.bullets.bulletDirectionStates.ShootingDirectionState;

import java.awt.*;

public interface Bullet extends Cloneable{

    public int getDamage();
    public void startMotionAfterShooting(Point initialPosition , ShootingDirectionState state);
}
