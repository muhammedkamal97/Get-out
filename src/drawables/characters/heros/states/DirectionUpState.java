package drawables.characters.heros.states;

import drawables.pickables.Weapon;
import drawables.pickables.weapons.bullets.bulletDirectionStates.ShootDown;
import drawables.pickables.weapons.bullets.bulletDirectionStates.ShootUp;

import java.awt.*;

public class DirectionUpState implements DirectionState{

    @Override
    public void shoot(Weapon weapon, Point position) {
        weapon.shoot(new ShootUp(),position);
    }

}
