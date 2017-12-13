package drawables.obstacles.traps;

import drawables.characters.Hero;
import drawables.obstacles.Trap;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import maze.Maze;

import java.awt.*;

public class Cage extends StandardTrap implements Trap {

    private final int HEALTH_POINTS = 50;
    private final int DAMAGE = 0;

    public Cage (){
        setHealthPoints();
        setDamage();
    }

    @Override
    protected int getHealthPoints() {
        return HEALTH_POINTS;
    }

    @Override
    public void trap(Hero hero) {
//        hero.removeAllWeapons(); //TODO
    }

    @Override
    public void drawOnCanvas(GraphicsContext gc, Point pt, int width, int height) {
        //TODO
    }

    /**
     * display weapons on destroy
     */
    @Override
    public void destroy() {

        return;
    }
    @Override
    protected int getOriginalDamage() {
        return DAMAGE;
    }
}
