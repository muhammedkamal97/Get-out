package drawables.pickables.shields;

import drawables.obstacles.walls.Steel;

public class SteelShield extends StandardShield {
    private final int SHIELD_POINTS = 60;

    public SteelShield(){
        setShieldPoints();
    }

    @Override
    protected int getShieldPoints() {
        return SHIELD_POINTS;
    }
    //TODO @ abdelrahman 7aga kan 3ayez ye3melha

}
