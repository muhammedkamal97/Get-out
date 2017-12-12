package drawables.obstacles.bombs;

import drawables.characters.Hero;
import drawables.obstacles.Bomb;
import javafx.scene.canvas.Canvas;

import java.awt.*;

public class dynamite extends StandardBomb implements Bomb {

    private final int HEALTH = 50;
    private final int RANGE = 3;
    private final int DAMAGE = 50;

    public dynamite() {

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
        //TODO
    }

    @Override
    public void drawOnCanvas(Canvas canvas) {
        //TODO
    }
}
