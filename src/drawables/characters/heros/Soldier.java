package drawables.characters.heros;

import drawables.characters.commands.Command;
import drawables.characters.heros.states.DirectionDownState;
import drawables.pickables.Weapon;
import maze.Maze;

public class Soldier extends StandardHero {

    private static final int HEALTH_POINTS = 150;
    private static final int MOTION_DELAY = 50;
    private static final int WEAPONS_LIMIT = 5;


    public Soldier() {
        setHealthPoints();
        spriteSetters();
        setDirectionState(DirectionDownState.createDirectionDownState());
    }

    @Override
    public boolean move(Command moveCommand, Maze maze) {
    	boolean moveResult = super.move(moveCommand, maze);
        try {
            Thread.sleep(MOTION_DELAY);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return moveResult;
    }

    @Override
    protected int getHeroStartingHealth() {
        return HEALTH_POINTS;
    }

    @Override
    public void addWeapon(Weapon weapon) {
        super.addWeapon(weapon);
        if(getNumberOfWeapons() > WEAPONS_LIMIT){
            removeFirstWeapon();
        }
    }

}
