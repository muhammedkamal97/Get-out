package observer;

import drawables.pickables.weapons.bullets.Bullet;

import java.awt.*;

/**
 * Created by Mahmoud on 12/18/2017.
 */
public interface BulletMotionObservable {

    public void registerBulletMotionObserver(BulletMotionObserver observer);
    public void notifyBulletMotionObserver(Bullet bullet, Point pastPosition, Point currentPosition, boolean destroyed);

}
