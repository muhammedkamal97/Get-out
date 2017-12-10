package drawables.characters.monsters.Behaviors;

import drawables.characters.heros.states.DirectionState;
import drawables.pickables.Weapon;
import drawables.pickables.weapons.RPG;
import drawables.pickables.weapons.bullets.bulletDirectionStates.ShootingDirectionState;

import java.awt.*;

public class RPGShootingBehavior implements ShootingBehavior{
    Weapon rifle = new RPG();
    @Override
    public void shoot(Point initialPosition, DirectionState state) {
       state.shoot(rifle, initialPosition);
    }
}
