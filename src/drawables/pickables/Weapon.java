package drawables.pickables;

import drawables.pickables.weapons.bullets.bulletDirectionStates.ShootingDirectionState;
import maze.Maze;

import java.awt.*;

public interface Weapon extends Pickable {
    public void shoot(ShootingDirectionState state, Point initialPosition);
    public void reload();
    public int getNumberOfBullets();
}
