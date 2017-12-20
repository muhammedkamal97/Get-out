package drawables.pickables.shields;

import drawables.characters.Hero;

public class NormalShield extends StandardShield{


    private final int SHIELD_POINTS = 40;

    public NormalShield(){
        setShieldPoints();
    }

    @Override
    protected int getShieldPoints() {
        return SHIELD_POINTS;
    }
}
