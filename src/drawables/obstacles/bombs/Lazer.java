package drawables.obstacles.bombs;

import drawables.characters.Hero;
import drawables.obstacles.Bomb;
import drawables.roads.Road;
import javafx.scene.canvas.Canvas;
import maze.Maze;

import java.awt.*;

public class Lazer extends StandardBomb implements Bomb {

    private int health;

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
    public void damageDrawableInExplosionRange() {

            for (int j = -3; j <= 3; j++) {
                //display green lazer as one line
                if (!(maze.getItemInPosition(new Point((int)this.getPosition().getX(),j)) instanceof Hero)) {
//                    maze.set(new Point((int)this.getPosition().getX(),j) == road;
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
