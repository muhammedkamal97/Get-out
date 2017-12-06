package drawables.pickables.weapons;

import drawables.pickables.weapons.bullets.Bullet;
import drawables.pickables.weapons.bullets.NormalBullet;
import drawables.pickables.weapons.bullets.RifleBullet;
import javafx.scene.canvas.Canvas;

public class MP5 extends StandardWeapon {

    private final int MAGAZINE_SIZE = 30;
    private final Bullet WEAPON_BULLET = new NormalBullet();


    public MP5(){
        setBulletType();
        setMaxMagazineCapacity();
    }
    @Override
    public void drawOnCanvas(Canvas canvas) {
        //GUI
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
