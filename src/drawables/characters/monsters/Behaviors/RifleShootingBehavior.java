package drawables.characters.monsters.Behaviors;

import drawables.characters.heros.states.DirectionState;
import drawables.pickables.Weapon;
import drawables.pickables.weapons.AK47;
import drawables.pickables.weapons.bullets.bulletDirectionStates.ShootingDirectionState;

import java.awt.*;

public class RifleShootingBehavior implements ShootingBehavior {
    Weapon rifle = new AK47();
    @Override
    public void shoot(Point initialPosition, DirectionState state) {
        state.shoot(rifle, initialPosition);
    }
}
