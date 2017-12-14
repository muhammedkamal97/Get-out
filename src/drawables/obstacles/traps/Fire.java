package drawables.obstacles.traps;

import View.Graphics.ImagesMaps.MazeMap;
import drawables.characters.Hero;
import drawables.obstacles.Trap;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import maze.Maze;

import java.awt.*;

public class Fire extends StandardTrap implements Trap {


    private final int HEALTH_POINTS = 50;
    private final int DAMAGE = 75;

    public Fire (){
        setHealthPoints();
        setDamage();
    }

    @Override
    protected int getHealthPoints() {
        return HEALTH_POINTS;
    }

    @Override
    public void destroy() {
        return;
    }

    @Override
    protected int getOriginalDamage() {
        return DAMAGE;
    }

}
