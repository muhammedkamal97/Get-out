package drawables.obstacles.bombs;

import drawables.characters.Hero;
import drawables.obstacles.Bomb;
import javafx.scene.canvas.Canvas;
import maze.Maze;

import java.awt.*;

public class NormalBomb extends StandardBomb implements Bomb {

    private int health;
    private int range;

    public void NormalBomb() {
        this.health = 50;
        this.range = 5;
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

        for (int i = (-range); i <= range; i++) {
            for (int j = (-range); j <= range; j++) {
                if (!(maze.getItemInPosition(new Point(i, j)) instanceof Hero)) {
//                    maze.set = road;
                }
                //display black marks for explosion;
            }
        }

    }

    @Override
    public void explode() {
        this.damageDrawableInExplosionRange();
        //display animation for explosion;
    }

    @Override
    public void drawOnCanvas(Canvas canvas) {
        //TODO
    }

    @Override
    public void destroy() {
        return;
    }
}
