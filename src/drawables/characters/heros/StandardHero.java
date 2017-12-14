package drawables.characters.heros;

import View.Graphics.ImagesMaps.CharactersMap;
import View.Graphics.Sprite.ImageSprite;
import View.Graphics.Sprite.MySprite;
import drawables.characters.Hero;

import drawables.characters.Monster;
import drawables.characters.commands.Command;
import drawables.characters.heros.states.DirectionState;
import drawables.obstacles.Trap;
import drawables.pickables.Pickable;
import drawables.pickables.Weapon;
import drawables.pickables.weapons.bullets.Bullet;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;


import javafx.scene.canvas.GraphicsContext;
import maze.Maze;
import observer.MotionObserver;
import observer.SubjectObserver;

public abstract class StandardHero implements Hero {

	private int healthPoints = 0;
	private int coins = 0;
	private int trials = 0;
	private int armorPoints = 0;
	private Point position;
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

	private Maze maze;

	private MySprite downSprite = new MySprite();
	private MySprite upSprite = new MySprite();
	private MySprite rightSprite = new MySprite();
	private MySprite leftSprite = new MySprite();

	@Override
	public int getHealthPoints() {
		return healthPoints;
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
		if (! this.holdMoneterShield){
		int damage = monster.getDamage();
		takeDamage(damage);
		} else {
			this.holdbombShield = false;
		}
	}

	@Override
	public void setMaze(Maze maze) {
		this.maze = maze;
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

				notifyObservers();
			} else{
				healthPoints -= damage;
			}
		}
	}

	@Override
	public void shoot() {
		if (currentWeapon != null) {
			//currentWeapon.shoot(direction, position);
			this.directionState.shoot(currentWeapon, position);
		}
	}

	@Override
	public void holdNextWeapon() {
		if (currentWeapon != null) {
			int index = allWeapons.indexOf(currentWeapon);
			if (index != allWeapons.size() - 1)
				currentWeapon = allWeapons.get(index + 1);
		}
	}

	@Override
	public void holdPreviousWeapon() {

		if (currentWeapon != null) {
			int index = allWeapons.indexOf(currentWeapon);
			if (index != 0)
				currentWeapon = allWeapons.get(index - 1);
		}
	}

	@Override
	public void addWeapon(Weapon weapon) {
		allWeapons.add(weapon);
		currentWeapon = weapon;
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
		if (! this.holdBulletShield){
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

		this.position = position;
		notifyMotionObservers();
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
		if (currentWeapon != null)
			currentWeapon.reload();
	}

	@Override
	public void increaseHealthPoints(int Health) {
		healthPoints += Health;
		if (healthPoints > getHeroStartingHealth()) {
			healthPoints = getHeroStartingHealth();
		}
	}

	@Override
	public void increaseCoins(int coins) {
		this.coins += coins;
	}

	@Override
	public void increaseTrials() {
		this.trials++;
	}
	@Override
	public void protectedFromFlame() {
		this.holdflameShield = true;
	}
	@Override
	public void protectedFromBomb() {
		this.holdbombShield = true;
	}
	@Override
	public void protectedFromTrap() {
		this.holdTrapShield = true;
	}
	@Override
	public void protectedFromBullets() {
		this.holdBulletShield = true;
		
	}

	public void protectedFromMonester() {
		this.holdMoneterShield =true;
	}

	@Override
	public void notifyObservers() {
		for(int i = 0 ; i < observers.size() ; i++){
			observers.get(i).update();
		}
	}

	@Override
	public void RegisterObserver(SubjectObserver observer) {
		observers.add(observer);
	}

	@Override
	public void drawOnCanvas(GraphicsContext gc, Point pt, int widthCell, int heightCell) {
		getDirectionState().Draw(gc, pt, widthCell, heightCell, this);
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
	public MySprite getLeftSprite() { return leftSprite; }

	private void setDownSprite(MySprite sprite) { this.downSprite = sprite; }
	private void setUpSprite(MySprite sprite) { this.upSprite = sprite; }
	private void setRightSprite(MySprite sprite) { this.rightSprite = sprite; }
	private void setLeftSprite(MySprite sprite) { this.leftSprite = sprite; }

	protected void spriteSetters() {
		setHealthPoints();
		CharactersMap map = CharactersMap.getInstance();
		ImageSprite sprite = map.getImageSprite("Flash");
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
		for(int i = 0 ; i < motionObservers.size();i++)
			motionObservers.get(i).updateMovingObjects();
	}
}
