package drawables.obstacles.traps;

import drawables.obstacles.Trap;

public class Fire extends StandardTrap implements Trap {


    private final int HEALTH_POINTS = 50;
    private final int DAMAGE = 75;

    public Fire (){
        setHealthPoints();
        setDamage();
    }
//TODO fire animation
    @Override
    protected int getHealthPoints() {
        return HEALTH_POINTS;
    }

    @Override
    protected int getOriginalDamage() {
        return DAMAGE;
    }

}
