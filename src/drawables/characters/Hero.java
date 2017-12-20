package drawables.characters;

import drawables.Drawable;
import drawables.characters.heros.StandardHero.WeaponIterator;
import drawables.obstacles.Trap;
import drawables.pickables.Weapon;
import javafx.scene.canvas.GraphicsContext;
import observer.*;

import java.awt.*;

public interface Hero extends Handler,MovingObject, ObservedSubject, MotionObservable,
        HeroObservable, ObservableHeroState {

    public void trapHero(Trap trap);
    public void attackedByMonster(Monster monster);
    public void shoot();
    public int getHealthPoints();
    public void holdNextWeapon();
    public void holdPreviousWeapon();
    public void addWeapon(Weapon weapon);
	public void increaseHealthPoints(int Health);
    public void increaseCoins(int coins);
    public void increaseTrials();
    public void acceptAmmo();
	public void dropKey();
	public boolean hasKey();
	public void pickedKey();
    public Weapon getCurrentWeapon();
    public int getTrials();
    public void drawOnReleased(GraphicsContext gc, Point pt, int widthCell, int heightCell);
    public void removeAllWeapons();
    public void acceptShield(int shieldPoints);
    public WeaponIterator getIterator();
}
