package drawables.pickables.shields;

public class PlatinumShield extends StandardShield {

    private final int SHIELD_POINTS = 100;

    public PlatinumShield(){
        setShieldPoints();
    }

    @Override
    protected int getShieldPoints() {
        return SHIELD_POINTS;
    }
}
