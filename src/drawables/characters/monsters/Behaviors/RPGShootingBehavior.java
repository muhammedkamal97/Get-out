package drawables.characters.monsters.Behaviors;

import drawables.pickables.Weapon;
import drawables.pickables.weapons.RPG;

import java.awt.*;

public class RPGShootingBehavior implements ShootingBehavior{
    Weapon rifle = new RPG();
    @Override
    public void shoot(Point initialPosition, int direction) {
        rifle.shoot(direction,initialPosition);
    }
}
