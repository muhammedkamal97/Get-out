package drawables.pickables.weapons.bullets;

public class NormalBullet extends StandardBullet {


    private final int DAMAGE = 30;
    public NormalBullet() {
        setDamage();
    }

    @Override
    public Cloneable cloneBullet() {
        return new NormalBullet();
    }

    @Override
    protected int getTypeDamage() {
        return DAMAGE;
    }
}
