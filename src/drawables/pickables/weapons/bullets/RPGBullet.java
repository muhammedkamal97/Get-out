package drawables.pickables.weapons.bullets;

public class RPGBullet extends StandardBullet {

    private final int DAMAGE = 100;
    public RPGBullet() {
        setDamage();
    }

    @Override
    public Cloneable clone() {
        return new RPGBullet();
    }

    @Override
    protected int getTypeDamage() {
        return DAMAGE;
    }
}
