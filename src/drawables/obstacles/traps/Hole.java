package drawables.obstacles.traps;

import drawables.characters.Hero;
import drawables.obstacles.Trap;
import javafx.scene.canvas.Canvas;

import java.awt.*;

public class Hole extends StandardTrap implements Trap {

    public void Hole (){
        this.damage = 1000;
    }

    @Override
    public void trap(Hero hero) {
        return;
        //hero should fall and die
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
