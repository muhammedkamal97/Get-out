package drawables.characters.monsters.Behaviors;

import drawables.pickables.Weapon;
import drawables.pickables.weapons.AK47;

import java.awt.*;

public class RifleShootingBehavior implements ShootingBehavior {
    Weapon rifle = new AK47();
    @Override
    public void shoot(Point initialPosition, int direction) {
        rifle.shoot(direction,initialPosition);
    }
}
