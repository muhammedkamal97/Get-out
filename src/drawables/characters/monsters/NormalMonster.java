package drawables.characters.monsters;

import drawables.characters.monsters.Behaviors.NoShootingBehavior;
import drawables.characters.monsters.Behaviors.ShootingBehavior;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

import java.awt.*;

public class NormalMonster extends StandardMonster{

    private final int HEALTH_POINTS = 100;
    private final int DAMAGE = 50;
    private final ShootingBehavior shootingBehavior = new NoShootingBehavior();
    public NormalMonster(){
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
