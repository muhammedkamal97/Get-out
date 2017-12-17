package drawables.obstacles.traps;

import View.Graphics.ImagesMaps.MazeMap;
import drawables.characters.Hero;
import drawables.obstacles.Trap;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

import java.awt.*;

public class Hole extends StandardTrap implements Trap {

    private final int HEALTH_POINTS = 1000;
    private final int DAMAGE = 1000;

    public Hole (){
        setHealthPoints();
        setDamage();
    }

    @Override
    protected int getHealthPoints() {
        return HEALTH_POINTS;
    }

    @Override
    protected int getOriginalDamage() {
        return DAMAGE;
    }
}
