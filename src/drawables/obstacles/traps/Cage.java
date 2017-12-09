package drawables.obstacles.traps;

import drawables.characters.Hero;
import drawables.obstacles.Trap;
import javafx.scene.canvas.Canvas;
import maze.Maze;

import java.awt.*;

public class Cage implements Trap {

    private Point position;
    private int damage;

    @Override
    public void trap(Hero hero) {
//        hero.removeAllWeapons();
        this.damage = 0;
    }

    @Override
    public int getDamage() {
        return this.damage;
    }

    @Override
    public void drawOnCanvas(Canvas canvas) {
        //TODO
    }

    @Override
    public Point getPosition() {
        return position;
    }

    @Override
    public void setPosition(Point position) {
        this.position = position;
    }

    @Override
    public void destroy() {
        //TODO
    }
}
