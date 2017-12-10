package drawables.characters.heros.states;

import drawables.pickables.Weapon;
import drawables.pickables.weapons.bullets.bulletDirectionStates.ShootDown;
import drawables.pickables.weapons.bullets.bulletDirectionStates.ShootLeft;

import java.awt.*;

public class DirectionLeftState implements DirectionState {
    @Override
    public void shoot(Weapon weapon, Point position) {
        weapon.shoot(new ShootLeft(),position);
    }
}
