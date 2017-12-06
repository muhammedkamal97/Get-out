package drawables.pickables.weapons.bullets;

public class RifleBullet extends StandardBullet {

    private final int DAMAGE = 50;
    public RifleBullet() {
        setDamage();
    }

    @Override
    public Cloneable cloneBullet() {
        return new RifleBullet();
    }

    @Override
    protected int getTypeDamage() {
        return DAMAGE;
    }
}
