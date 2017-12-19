package drawables.pickables.weapons;

import drawables.pickables.weapons.bullets.Bullet;
import drawables.pickables.weapons.bullets.NormalBullet;

public class NormalGun extends StandardWeapon {

    private final int MAGAZINE_SIZE = 6;
    private final Bullet WEAPON_BULLET = new NormalBullet();

    public NormalGun() {
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
