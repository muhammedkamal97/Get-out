package drawables.obstacles.bombs;

import drawables.characters.Hero;
import drawables.obstacles.Bomb;
import drawables.roads.Road;
import javafx.scene.canvas.Canvas;
import maze.Maze;

import java.awt.*;

public class Lazer extends StandardBomb implements Bomb {


    private final int HEALTH = 25;
    private final int RANGE = 3;
    private final int DAMAGE = 1000;

    public Lazer () {
        setBombRange();
        setDamage();
        setHealthPoints();
    }

    @Override
    protected int getBombOriginalDamage() {
        return DAMAGE;
    }

    @Override
    protected int getBombRange() {
        return RANGE;
    }

    @Override
    protected int getHealthPoints() {
        return HEALTH;
    }

    @Override
    public void animateOnExplosion() {

    }

    @Override
    public void drawOnCanvas(Canvas canvas) {
        //TODO
    }
}
