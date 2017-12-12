package drawables.characters;

import drawables.obstacles.Trap;
import drawables.pickables.Weapon;
import maze.Maze;
import observer.ObservedSubject;

public interface Hero extends Handler,MovingObject, ObservedSubject {

    public void trapHero(Trap trap);
    public void attackedByMonster(Monster monster);
    public void shoot(Maze maze);
    public int getHealthPoints();
    public void holdNextWeapon();
    public void holdPreviousWeapon();
    public void addWeapon(Weapon weapon);
	public void increaseHealthPoints(int Health);
    public void increaseCoins(int coins);
    public void increaseTrials();
    public void acceptAmmo();
	public void protectedFromFlame();
	public void protectedFromBomb();
	public void protectedFromTrap();
	public void protectedFromBullets();
	public void protectedFromMonester();

}
