package drawables.characters.heros;

import drawables.characters.commands.Command;
import drawables.pickables.Weapon;
import javafx.scene.canvas.Canvas;
import maze.Maze;

public class Flash extends StandardHero {
    private static final int HEALTH_POINTS = 100;
    private static final int MOTION_DELAY = 10;
    private static final int WEAPONS_LIMIT = 1;

    public Flash(){
        setHealthPoints();
    }

    @Override
    public void move(Command moveCommand, Maze maze) {
        super.move(moveCommand, maze);
        try {
            Thread.sleep(MOTION_DELAY);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
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

    @Override
    public void drawOnCanvas(Canvas canvas) {

    }

	
}
