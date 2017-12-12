package drawables.characters.heros.states;

import drawables.pickables.Weapon;
import drawables.pickables.weapons.bullets.bulletDirectionStates.ShootDown;
import maze.Maze;

import java.awt.*;

public class DirectionDownState implements DirectionState {
    @Override
    public void shoot(Weapon weapon, Point position, Maze maze) {
        weapon.shoot(new ShootDown(),position,maze);
    }
}

