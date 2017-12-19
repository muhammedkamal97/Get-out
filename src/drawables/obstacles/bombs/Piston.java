package drawables.obstacles.bombs;

import drawables.characters.Hero;
import maze.Maze;

import java.awt.*;
import java.util.Random;

public class Piston extends StandardBomb {

    private final int HEALTH = 250;
    private final int RANGE = 4;
    private final int DAMAGE = 75;

    public Piston() {
        setBombRange();
        setDamage();
        setHealthPoints();
    }

    @Override
    protected int getHealthPoints() {
        return HEALTH;
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
    public void explode() {
        this.destroy();
    }
}
