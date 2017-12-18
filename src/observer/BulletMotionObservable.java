package observer;

import java.awt.*;

/**
 * Created by Mahmoud on 12/18/2017.
 */
public interface BulletMotionObservable {

    public void registerBulletMotionObserver(BulletMotionObserver observer);
    public void notifyBulletMotionObserver(Point pastPosition, Point currentPosition, boolean destroyed);

}
