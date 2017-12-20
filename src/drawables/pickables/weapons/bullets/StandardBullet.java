package drawables.pickables.weapons.bullets;

import View.Graphics.ImagesMaps.MazeMap;
import drawables.characters.heros.states.*;
import drawables.pickables.Weapon;
import drawables.pickables.weapons.bullets.bulletDirectionStates.ShootingDirectionState;
import javafx.scene.canvas.GraphicsContext;
import maze.Maze;

import java.awt.*;

public abstract class StandardBullet implements Bullet {

    private int damage;
    @Override
    public int getDamage() {
        return damage;
    }

    @Override
    public void startMotionAfterShooting(Point initialPosition, ShootingDirectionState state, Weapon weapon) {
        state.startMotion(initialPosition,this, weapon);
    }

    @Override
    public void drawOnCanvas(GraphicsContext gc, int x, int y, int width, int height, DirectionState state) {
        MazeMap map = MazeMap.getInstance();
        if (state instanceof DirectionDownState) {
            gc.drawImage(map.getBufferedImage("ShootingBulletDown"),
                    x, y, width, height);
        } else if (state instanceof DirectionLeftState) {
            gc.drawImage(map.getBufferedImage("ShootingBulletLeft"),
                    x, y, width, height);
        } else if (state instanceof DirectionRightState) {
            gc.drawImage(map.getBufferedImage("ShootingBulletRight"),
                    x, y, width, height);
        } else if (state instanceof DirectionUpState) {
            gc.drawImage(map.getBufferedImage("ShootingBulletUp"),
                    x, y, width, height);
        }
    }

    protected void setDamage(){
        damage = getTypeDamage();
    }
    protected abstract int getTypeDamage();
}
