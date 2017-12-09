package drawables.obstacles.traps;

import drawables.characters.Hero;
import drawables.obstacles.Trap;
import javafx.scene.canvas.Canvas;
import maze.Maze;

import java.awt.*;
import java.util.Random;

public class RandomTeleport implements Trap {

    private Point position;
    private int damage;

    public void RandomTeleport () {
        this.damage = 0;
    }

    @Override
    public void trap(Hero hero) {
        int x = 2, y = 2;
//        Random ran = new Random();
//
//        while (!allowable(new Point(x , y))) {
//            x = ran.nextInt(maze.height);
//            y = ran.nextInt(maze.width);
//        }
        hero.setPosition(new Point(x, y));
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
        return this.position;
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
