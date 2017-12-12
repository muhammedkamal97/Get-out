package drawables.characters.heros.states;

import drawables.pickables.Weapon;
import maze.Maze;

import java.awt.*;

public interface DirectionState {

    public void shoot(Weapon weapon, Point position, Maze maze);

}
