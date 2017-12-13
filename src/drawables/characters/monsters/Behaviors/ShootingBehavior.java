package drawables.characters.monsters.Behaviors;

import java.awt.*;

import drawables.characters.heros.states.DirectionState;
import drawables.pickables.weapons.bullets.bulletDirectionStates.ShootingDirectionState;
import maze.Maze;

public interface ShootingBehavior {
    public void shoot(Point initialPosition , DirectionState state);
}
