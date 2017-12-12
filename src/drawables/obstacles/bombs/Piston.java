package drawables.obstacles.bombs;

import drawables.characters.Hero;
import drawables.obstacles.Bomb;
import drawables.roads.Road;
import javafx.scene.canvas.Canvas;
import maze.Maze;

import java.awt.*;
import java.util.Random;

public class Piston extends StandardBomb {

    private final int HEALTH = 250;
    private final int RANGE = 10;
    private final int DAMAGE = 100;
    private Maze maze;

    public Piston() {
        setBombRange();
        setDamage();
        setHealthPoints();
        maze = getMaze();
    }


    @Override
    public void damageDrawableInExplosionRange() {
        Random ran = new Random();

        int x = ran.nextInt(30); //handle maze size
        int y = ran.nextInt(30);
        while (!(maze.getItemInPosition(new Point(x, y)) instanceof Hero)) {
            x = ran.nextInt(30);
            y = ran.nextInt(30);
        }
//        maze.set(new Point((int)this.getPosition().getX(),j) == road;
        //display explosion graphics (start animation for 10 secs then stop it)
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
    public void animateOnExplosion() {
    //TODO
    }

    @Override
    public void explode() {
        myThread();
        this.destroy();
    }

    @Override
    public void drawOnCanvas(Canvas canvas) {
        //TODO
    }

    private void myThread() {
        Thread t = new Thread() {
            public void run() {
                try {
                    sleep(500);
                    //add explode code
                    animateOnExplosion();
                    damageDrawableInExplosionRange();
                    //display explosion graphics
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        };
        t.start();
    }
}
