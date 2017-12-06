package drawables.pickables.weapons.bullets;

import drawables.pickables.weapons.NuclearHead;

public class NuclearHeadBullet extends StandardBullet {


    private final int DAMAGE = 400;
    public NuclearHeadBullet() {
        setDamage();
    }


    @Override
    public Cloneable clone() {
        return new NuclearHeadBullet();
    }

    @Override
    protected int getTypeDamage() {
        return DAMAGE;
    }
}
