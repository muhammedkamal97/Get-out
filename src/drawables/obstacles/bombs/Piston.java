package drawables.obstacles.bombs;

import drawables.characters.Hero;
import drawables.obstacles.Bomb;
import drawables.roads.Road;
import javafx.scene.canvas.Canvas;

import java.awt.*;
import java.util.Random;

public class Piston extends StandardBomb implements Bomb {

    private int health;
    private int range;

    public void Piston() {
        this.health = 250;
        this.range = 10;
        this.damage = 100;
    }

    @Override
    public void takeBullet(int bulletDamage) {
        this.health -= bulletDamage;
        if (this.health <= 0) {
            this.destroy();
        }
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
    public void explode() {
        myThread();
        // start thread then excute explode implementation in it
        // display animation for explosion;
    }

    @Override
    public void drawOnCanvas(Canvas canvas) {
        //TODO
    }

    @Override
    public void destroy() {
        return;
    }

    public void myThread() {
        Thread t = new Thread() {
            public void run() {
                try {
                    sleep(500);
                    //add explode code
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
