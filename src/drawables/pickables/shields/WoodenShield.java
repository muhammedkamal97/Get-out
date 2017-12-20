package drawables.pickables.shields;

public class WoodenShield extends StandardShield {
    private final int SHIELD_POINTS = 40;

    public WoodenShield(){
        setShieldPoints();
    }

    @Override
    protected int getShieldPoints() {
        return SHIELD_POINTS;
    }
}
