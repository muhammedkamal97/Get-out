package drawables.pickables;

import java.awt.*;

public interface Weapon extends Pickable {
    public void shoot(int direction , Point initialPosition);
}
