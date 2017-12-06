package drawables.pickables.weapons.bullets;

import java.awt.*;

public interface Bullet extends Cloneable{

    public int getDamage();
    public void startMotionAfterShooting(Point initialPosition , int direction);
}
