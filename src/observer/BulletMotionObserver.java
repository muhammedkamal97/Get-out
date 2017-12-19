package observer;

import java.awt.*;

/**
 * Created by Mahmoud on 12/18/2017.
 */
public interface BulletMotionObserver {

    public void updateBulletMotionObserver(Point pastPosition, Point currentPosition, boolean destroyed);


}
