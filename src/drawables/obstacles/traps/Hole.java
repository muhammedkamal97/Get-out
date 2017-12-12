package drawables.obstacles.traps;

import drawables.characters.Hero;
import drawables.obstacles.Trap;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

import java.awt.*;

public class Hole extends StandardTrap implements Trap {

    public Hole (){
        this.damage = 1000;
    }

    @Override
    public void trap(Hero hero) {
        return;
        //hero should fall and die
    }

    @Override
    public void drawOnCanvas(GraphicsContext gc, Point pt, int width, int height) {
    //TODO
    }

    @Override
    public void destroy() {
        return;
    }
}
