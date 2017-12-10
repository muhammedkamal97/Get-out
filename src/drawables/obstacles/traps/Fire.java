package drawables.obstacles.traps;

import drawables.characters.Hero;
import drawables.obstacles.Trap;
import javafx.scene.canvas.Canvas;
import maze.Maze;

import java.awt.*;

public class Fire extends StandardTrap implements Trap {

    public void Fire (){
        this.damage = 75;
    }


    @Override
    public void trap(Hero hero) {
        return;
    }


    @Override
    public void drawOnCanvas(Canvas canvas) {
        //TODO
    }

    @Override
    public void destroy() {
        return;
    }
}
