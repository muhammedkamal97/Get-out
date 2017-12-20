package drawables.characters.heros;

import View.Graphics.ImagesMaps.CharactersMap;
import View.Graphics.Sprite.ImageSprite;
import View.Graphics.Sprite.MySprite;
import drawables.Drawable;
import drawables.characters.Hero;
import drawables.characters.IWeaponIterator;
import drawables.characters.Monster;
import drawables.characters.commands.Command;
import drawables.characters.heros.StandardHero.WeaponIterator;
import drawables.characters.heros.states.DirectionState;
import drawables.obstacles.Trap;
import drawables.obstacles.Wall;
import drawables.pickables.Pickable;
import drawables.pickables.Weapon;
import drawables.pickables.weapons.bullets.Bullet;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;


import javafx.scene.canvas.GraphicsContext;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import maze.Maze;
import observer.*;

public abstract class StandardHero implements Hero {

    private int healthPoints = 0;
    private int coins = 0;
    private int trials = 0;
    private int armorPoints = 0;
    private Point position;
    private Point oldPosition;
    private DirectionState directionState;
    private Weapon currentWeapon;
    private ArrayList<Weapon> allWeapons = new ArrayList<>();
    private boolean holdMoneterShield = false;
    private boolean holdBulletShield = false;
    private boolean holdflameShield = false;
    private boolean holdTrapShield = false;
    private boolean holdbombShield = false;
    private ArrayList<SubjectObserver> observers = new ArrayList<>();
    private ArrayList<MotionObserver> motionObservers = new ArrayList<>();
    private ArrayList<DeathObserver> deathObservers = new ArrayList<>();
    private ArrayList<HeroWinObserver> winObservers = new ArrayList<>();
    private ArrayList<HeroStateObserver> stateObservers = new ArrayList<>();
    private boolean hasKey = false;
    private Maze maze;

    private MySprite downSprite = new MySprite();
    private MySprite upSprite = new MySprite();
    private MySprite rightSprite = new MySprite();
    private MySprite leftSprite = new MySprite();
    
    private IWeaponIterator iterator = getIterator();
    public class WeaponIterator implements IWeaponIterator{
    	private ArrayList<Weapon> weapons;
    	private int index = 0;
    	public WeaponIterator(StandardHero hero) {
			weapons = hero.allWeapons;
		}
		@Override
		public boolean hasNext() {
			return index < weapons.size();
		}

		@Override
		public Weapon nextweapon() {
			index++;
			index = index % weapons.size();
			return weapons.get(index);
		}

		@Override
		public Weapon previousweapon() {
			index--;
			index = index%weapons.size();
			return weapons.get(index);
		}

		@Override
		public Weapon getWeapon() {
			return weapons.get(index);
		}
    	
    }
    @Override
    public int getHealthPoints() {
        return healthPoints;
    }

    @Override
    public Weapon getCurrentWeapon() {
        return currentWeapon;
    }

    @Override
    public int getTrials() {
        return trials;
    }

    @Override
    public void trapHero(Trap trap) {
        if (!this.holdTrapShield) {
            int damage = trap.getDamage();
            takeDamage(damage);
        } else {
            this.holdTrapShield = false;
        }
    }

    @Override
    public void attackedByMonster(Monster monster) {
        if (!this.holdMoneterShield) {
            int damage = monster.getDamage();
            takeDamage(damage);
        } else {
            this.holdbombShield = false;
        }
    }

    @Override
    public void setMaze(Maze maze) {

        this.maze = maze;
        registerMotionObservers(maze);
    }

    @Override
    public void takeDamage(int damage) {
        if (damage < armorPoints) {
            armorPoints -= damage;
        } else {
            damage = damage - armorPoints;
            armorPoints = 0;
            if (damage >= healthPoints) {
                healthPoints = 0;
                // check or notify
                notifyChangeInHealth();
                notifyChangeInArmorPoints();
                notifyDeathObservers();
            } else {
                healthPoints -= damage;
                notifyChangeInHealth();
                notifyChangeInArmorPoints();
            }
        }
    }

    @Override
    public void shoot() {
        if (currentWeapon != null) {
            //currentWeapon.shoot(direction, position); //TODO
            this.directionState.shoot(currentWeapon, position);
            notifyChangeInNumberOfBullets();
        }
    }

    @Override
    public void holdNextWeapon() {
    	currentWeapon = iterator.nextweapon();
        notifyChangeInCurrentWeapon();
        notifyChangeInNumberOfBullets();
    }

    @Override
    public void holdPreviousWeapon() {
    	currentWeapon = iterator.previousweapon();
        notifyChangeInCurrentWeapon();
        notifyChangeInNumberOfBullets();
    }


    @Override
    public void addWeapon(Weapon weapon) {
        allWeapons.add(weapon);
        currentWeapon = weapon;
        notifyChangeInCurrentWeapon();
        notifyChangeInNumberOfBullets();
    }

    @Override
    public void receiveExplosionDamage(int damage) {
        takeDamage(damage);
    }

    @Override
    public boolean move(Command moveCommand, Maze maze) {
        return moveCommand.execute(this, maze);
    }

    @Override
    public void takeBullet(Bullet bullet) {
        if (!this.holdBulletShield) {
            int damage = bullet.getDamage();
            takeDamage(damage);
        } else {
            this.holdBulletShield = false;
        }
    }

    @Override
    public void pick(Pickable item) {
        item.addToHandler(this);
    }

    @Override
    public Point getPosition() {
        return position;
    }

    @Override
    public void setDirectionState(DirectionState state) {
        this.directionState = state;
    }

    @Override
    public DirectionState getDirectionState() {
        return this.directionState;
    }

    @Override
    public void setPosition(Point position) {
        this.oldPosition = this.position;
        this.position = position;
        notifyMotionObservers();
    }

    @Override
    public Point getOldPosition() {
        return this.oldPosition;
    }

    protected int getNumberOfWeapons() {
        return allWeapons.size();
    }

    protected void removeFirstWeapon() {
        allWeapons.remove(0);
    }

    protected void setHealthPoints() {
        healthPoints = getHeroStartingHealth();
    }

    protected abstract int getHeroStartingHealth();

    @Override
    public void acceptAmmo() {
        if (currentWeapon != null) {
            currentWeapon.reload();
            notifyChangeInNumberOfBullets();
        }
    }


    @Override
    public void increaseHealthPoints(int Health) {
        healthPoints += Health;
        if (healthPoints > getHeroStartingHealth()) {
            healthPoints = getHeroStartingHealth();
        }

        notifyChangeInHealth();
    }

    @Override
    public void increaseCoins(int coins) {
        this.coins += coins;
        notifyChangeInCoins();
    }

    @Override
    public void increaseTrials() {
        this.trials++;
    }

    public void protectedFromMonester() {
        this.holdMoneterShield = true;
    }

    @Override
    public void notifyObservers() {
        for (int i = 0; i < observers.size(); i++) {
            observers.get(i).update();
        }
    }

    @Override
    public void RegisterObserver(SubjectObserver observer) {
        observers.add(observer);
    }

    @Override
    public void acceptShield(int shieldPoints) {
        if (armorPoints + shieldPoints > 100) {
            armorPoints = 100;
        } else {
            armorPoints += shieldPoints;
        }
        notifyChangeInArmorPoints();
    }

    @Override
    public void drawOnCanvas(GraphicsContext gc, Point pt, int widthCell, int heightCell) {
        getDirectionState().Draw(gc, pt, widthCell, heightCell, this);
    }

    @Override
    public void drawOnReleased(GraphicsContext gc, Point pt, int widthCell, int heightCell) {
        getDirectionState().DrawReleased(gc, pt, widthCell, heightCell, this);
    }

    @Override
    public MySprite getDownSprite() {
        return downSprite;
    }

    @Override
    public MySprite getUpSprite() {
        return upSprite;
    }

    @Override
    public MySprite getRightSprite() {
        return rightSprite;
    }

    @Override
    public MySprite getLeftSprite() {
        return leftSprite;
    }

    private void setDownSprite(MySprite sprite) {
        this.downSprite = sprite;
    }

    private void setUpSprite(MySprite sprite) {
        this.upSprite = sprite;
    }

    private void setRightSprite(MySprite sprite) {
        this.rightSprite = sprite;
    }

    private void setLeftSprite(MySprite sprite) {
        this.leftSprite = sprite;
    }

    protected void spriteSetters() {
        setHealthPoints();
        CharactersMap map = CharactersMap.getInstance();
        ImageSprite sprite = map.getImageSprite(this.getClass().getSimpleName());
        setDownSprite(constructSprite(sprite.getImageDown(), sprite));
        setUpSprite(constructSprite(sprite.getImageUp(), sprite));
        setRightSprite(constructSprite(sprite.getImageRight(), sprite));
        setLeftSprite(constructSprite(sprite.getImageLeft(), sprite));
    }

    private MySprite constructSprite(BufferedImage img, ImageSprite imgSprite) {
        MySprite sprite = new MySprite();
        sprite.SpriteSheetBuffer(img.getWidth(),
                img.getHeight(),
                imgSprite.getFramesInRow(),
                imgSprite.getFramesInColumn(),
                img);
        return sprite;
    }

    @Override
    public void registerMotionObservers(MotionObserver observer) {
        motionObservers.add(observer);
    }

    @Override
    public void notifyMotionObservers() {
        for (int i = 0; i < motionObservers.size(); i++)
            motionObservers.get(i).updateMovingObjects();
    }

    @Override
    public void notifyDeathObservers() {
        for (int i = 0; i < deathObservers.size(); i++)
            deathObservers.get(i).updateDeadObservable();
    }

    @Override
    public void registerDeathObserver(DeathObserver observer) {
        deathObservers.add(observer);
    }

    @Override
    public void pickedKey() {
        hasKey = true;
    }

    @Override
    public boolean hasKey() {
        return hasKey;
    }

    @Override
    public void dropKey() {
        hasKey = false;
    }

    @Override
    public void registerStateObserver(HeroStateObserver observer) {
        stateObservers.add(observer);
    }

    @Override
    public void notifyChangeInHealth() {
        for (int i = 0; i < stateObservers.size(); i++)
            stateObservers.get(i).updateChangeInHealth(((double) healthPoints) / getHeroStartingHealth());
    }

    @Override
    public void notifyChangeInArmorPoints() {
        for (int i = 0; i < stateObservers.size(); i++)
            stateObservers.get(i).updateChangeInArmorPoints(((double) armorPoints) / 100);
    }

    @Override
    public void notifyChangeInCoins() {
        for (int i = 0; i < stateObservers.size(); i++)
            stateObservers.get(i).updateCoins(coins);
    }

    @Override
    public void notifyChangeInNumberOfBullets() {
        int bulletsLeft;
        if (currentWeapon == null) {
            bulletsLeft = 0;
        } else {
            bulletsLeft = currentWeapon.getNumberOfBullets();
        }
        for (int i = 0; i < stateObservers.size(); i++)
            stateObservers.get(i).updateNumberOfBullets(bulletsLeft);
    }

    @Override
    public void notifyChangeInCurrentWeapon() {
        for (int i = 0; i < stateObservers.size(); i++)
            stateObservers.get(i).updateCurrentWeapon(currentWeapon);
    }

    @Override
    public void notifyWinObservers() {
        for (int i = 0; i < winObservers.size(); i++)
            winObservers.get(i).updateHeroAsWinner();
    }

    @Override
    public void registerWinObserver(HeroWinObserver observer) {
        winObservers.add(observer);
    }

    @Override
    public void removeAllWeapons() {
        allWeapons = new ArrayList<>();
        currentWeapon = null;
        notifyChangeInCurrentWeapon();
    }
    @Override
    public WeaponIterator getIterator(){
		return new WeaponIterator(this);
    }
}