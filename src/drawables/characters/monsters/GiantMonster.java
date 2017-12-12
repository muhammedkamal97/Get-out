package drawables.characters.monsters;

import drawables.characters.monsters.Behaviors.NoShootingBehavior;
import drawables.characters.monsters.Behaviors.ShootingBehavior;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

import java.awt.*;

public class GiantMonster extends StandardMonster{
    private final int HEALTH_POINTS = 200;
    private final int DAMAGE = 100;
    private final ShootingBehavior shootingBehavior = new NoShootingBehavior();
    public GiantMonster(){
        setDamage();
        setShootingBehavior();
        setHealthPoints();
    }

    @Override
    protected int getMonsterDamage() {
        return DAMAGE;
    }

    @Override
    protected ShootingBehavior getShootingBehavior() {
        return shootingBehavior;
    }

    @Override
    protected int getMonsterHealthPoints() {
        return HEALTH_POINTS;
    }

    @Override
    public void drawOnCanvas(GraphicsContext gc, Point pt, int width, int height) {

    }
}
