package drawables.pickables.weapons.bullets;

import drawables.pickables.Weapon;
import drawables.pickables.weapons.bullets.bulletDirectionStates.ShootingDirectionState;
import javafx.scene.canvas.GraphicsContext;
import maze.Maze;

import java.awt.*;

public interface Bullet extends Cloneable{

    public int getDamage();
    public void startMotionAfterShooting(Point initialPosition , ShootingDirectionState stat, Weapon weapon);
    public void drawOnCanvas(GraphicsContext gc, int x, int y, int width, int height);
}
