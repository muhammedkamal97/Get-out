package drawables.pickables.weapons.bullets;

import drawables.pickables.Weapon;
import drawables.pickables.weapons.bullets.bulletDirectionStates.ShootingDirectionState;
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

    protected void setDamage(){
        damage = getTypeDamage();
    }
    protected abstract int getTypeDamage();
}
