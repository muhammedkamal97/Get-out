package drawables.characters.heros;

import drawables.characters.commands.Command;
import drawables.pickables.Weapon;
import javafx.scene.canvas.Canvas;
import maze.Maze;

public class Hulk extends StandardHero {
    private static final int HEALTH_POINTS = 200;
    private static final int MOTION_DELAY = 150;
    private static final int WEAPONS_LIMIT = 3;


    @Override
    public int getHealthPoints() {
        return HEALTH_POINTS;
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
