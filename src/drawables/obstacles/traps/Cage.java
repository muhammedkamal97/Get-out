package drawables.obstacles.traps;

import drawables.characters.Hero;
import drawables.obstacles.Trap;
import javafx.scene.canvas.Canvas;
import maze.Maze;

import java.awt.*;

public class Cage extends StandardTrap implements Trap {

    public Cage (){
        this.damage = 0;
    }


    @Override
    public void trap(Hero hero) {
//        hero.removeAllWeapons(); //TODO
    }

    @Override
    public void drawOnCanvas(Canvas canvas) {
        //TODO
    }

    /**
     * display weapons on destroy
     */
    @Override
    public void destroy() {

        return;
    }
}
