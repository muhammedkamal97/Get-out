package drawables.pickables.weapons;


import drawables.pickables.weapons.bullets.Bullet;
import drawables.pickables.weapons.bullets.RifleBullet;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

import java.awt.*;

public class AK47 extends StandardWeapon {

    private final int MAGAZINE_SIZE = 25;
    private final Bullet WEAPON_BULLET = new RifleBullet();

    public AK47(){
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
