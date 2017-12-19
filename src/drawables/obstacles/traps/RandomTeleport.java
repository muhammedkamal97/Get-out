package drawables.obstacles.traps;

import View.Graphics.ImagesMaps.MazeMap;
import drawables.characters.Hero;
import drawables.obstacles.Trap;
import drawables.roads.Road;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

import java.awt.*;
import java.util.Random;

public class RandomTeleport extends StandardTrap {



    private final int HEALTH = 50;
    private final int DAMAGE = 0;

    public RandomTeleport() {
        setDamage();
        setHealthPoints();
    }

    //TODO animation
    @Override
    protected int getOriginalDamage() {
        return DAMAGE;
    }

    @Override
    protected int getHealthPoints() {
        return HEALTH;
    }

    @Override
    public void trap(Hero hero) {
        Random ran = new Random();
        Point dimensions = maze.getDimensions();
        int x = ran.nextInt(dimensions.x); //handle maze size
        int y = ran.nextInt(dimensions.y);
        while (!(maze.getItemInPosition(new Point(x, y)) instanceof Road)) {
            x = ran.nextInt(dimensions.x);
            y = ran.nextInt(dimensions.y);
        }
        hero.setPosition(new Point(x, y));
    }

    @Override
    public void destroy() {

        super.destroy();
        return;
    }
}
