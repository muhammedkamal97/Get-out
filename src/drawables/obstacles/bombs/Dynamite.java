package drawables.obstacles.bombs;

import drawables.obstacles.Bomb;

public class Dynamite extends StandardBomb implements Bomb {

    private final int HEALTH = 50;
    private final int RANGE = 3;
    private final int DAMAGE = 25;

    public Dynamite() {

        setBombRange();
        setDamage();
        setHealthPoints();

    }

    @Override
    protected int getBombOriginalDamage() {
        return DAMAGE;
    }

    @Override
    protected int getBombRange() {
        return RANGE;
    }

    @Override
    protected int getHealthPoints() {
        return HEALTH;
    }

}
