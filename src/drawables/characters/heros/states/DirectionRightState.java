package drawables.characters.heros.states;

import drawables.pickables.Weapon;
import drawables.pickables.weapons.bullets.bulletDirectionStates.ShootDown;
import drawables.pickables.weapons.bullets.bulletDirectionStates.ShootRight;

import java.awt.*;

public class DirectionRightState implements DirectionState{
    @Override
    public void shoot(Weapon weapon, Point position) {
        weapon.shoot(new ShootRight(),position);
    }
}
