package drawables.obstacles.bombs;

import drawables.obstacles.Bomb;
import javafx.scene.canvas.Canvas;
import maze.Maze;

import java.awt.*;

public class NormalBomb implements Bomb {

    private Point position;
    private int health;
    private int range;
    private int damage;

    public void NormalBomb () {
        this.health = 50;
        this.range = 5;
        this.damage = 100;
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

        for (int i = (-range); i <= range; i++) {
            for (int j = (-range); j <= range; j++) {
//                if (maze[i][j] != hero) {
//                    maze[i][j] = road;
//                }
                //display black marks for explosion;
            }
        }

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
