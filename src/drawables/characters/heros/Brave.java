package drawables.characters.heros;

import drawables.characters.commands.Command;
import drawables.characters.heros.states.DirectionDownState;
import drawables.pickables.Weapon;
import maze.Maze;

public class Brave extends StandardHero {
    private static final int HEALTH_POINTS = 80;
    private static final int MOTION_DELAY = 3;
    private static final int WEAPONS_LIMIT = 3;

    public Brave() {
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
        if (getNumberOfWeapons() > WEAPONS_LIMIT) {
            removeFirstWeapon();
        }
    }

}
