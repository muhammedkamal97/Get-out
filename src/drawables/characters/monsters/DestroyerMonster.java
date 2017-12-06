package drawables.characters.monsters;

import drawables.characters.monsters.Behaviors.RPGShootingBehavior;
import drawables.characters.monsters.Behaviors.ShootingBehavior;
import javafx.scene.canvas.Canvas;

public class DestroyerMonster extends StandardMonster{
    private final int HEALTH_POINTS = 100;
    private final int DAMAGE = 50;
    private final ShootingBehavior shootingBehavior = new RPGShootingBehavior();


    public DestroyerMonster(){
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
    public void drawOnCanvas(Canvas canvas) {

    }
}
