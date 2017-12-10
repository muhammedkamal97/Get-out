package drawables.characters.heros.states;

import drawables.pickables.Weapon;
import drawables.pickables.weapons.bullets.bulletDirectionStates.ShootDown;

import java.awt.*;

public class DirectionDownState implements DirectionState {
    @Override
    public void shoot(Weapon weapon, Point position) {
        weapon.shoot(new ShootDown(),position);
    }
}

