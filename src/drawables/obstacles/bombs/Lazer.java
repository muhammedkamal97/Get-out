package drawables.obstacles.bombs;

import drawables.obstacles.Bomb;
import javafx.scene.canvas.Canvas;
import maze.Maze;

import java.awt.*;

public class Lazer implements Bomb {

    private Point position;
    private int health;
    private int damage;

    public void Lazer () {
        this.health = 25;
        this.damage = 1000;
    }

    @Override
    public void takeBullet(int bulletDamage) {
        this.health-= bulletDamage;
        if (this.health <= 0) {
            this.destroy();
        }
    }

    @Override
    public void damageDrawableInExplosionRange(Maze maze) {

//            for (int j = 0; j <= maze.getHeight; j++) {
//                if (maze[this.position.getX()][j] != hero) {
//                    maze[this.position.getX()][j] != hero
//                }
//                //display black marks for explosion;
//            }
    }

    @Override
    public void explode() {
//        this.damageDrawableInExplosionRange();
        //display animation for explosion;
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
