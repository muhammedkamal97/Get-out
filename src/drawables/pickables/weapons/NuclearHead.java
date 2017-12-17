package drawables.pickables.weapons;

import drawables.pickables.weapons.bullets.Bullet;
import drawables.pickables.weapons.bullets.NuclearHeadBullet;
import drawables.pickables.weapons.bullets.RifleBullet;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

import java.awt.*;

public class NuclearHead extends StandardWeapon {

    private final int MAGAZINE_SIZE = 3;
    private final Bullet WEAPON_BULLET = new NuclearHeadBullet();

    public NuclearHead() {
        setBulletType();
        setMaxMagazineCapacity();
        reload();
    }

    @Override
    protected int getMaxMagazineCapacity() {
        return MAGAZINE_SIZE;
    }

    @Override
    protected Bullet getBulletType() {
        return WEAPON_BULLET;
    }

}
