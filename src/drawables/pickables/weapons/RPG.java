package drawables.pickables.weapons;

import drawables.pickables.weapons.bullets.Bullet;
import drawables.pickables.weapons.bullets.RPGBullet;
import javafx.scene.canvas.Canvas;

public class RPG extends StandardWeapon {

    private final int MAGAZINE_SIZE = 4;
    private final Bullet WEAPON_BULLET = new RPGBullet();

    public RPG(){
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
