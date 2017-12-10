package drawables.characters.monsters;

import drawables.characters.Hero;
import drawables.characters.Monster;
import drawables.characters.commands.Command;
import drawables.characters.heros.states.DirectionState;
import drawables.characters.monsters.Behaviors.ShootingBehavior;
import drawables.pickables.weapons.bullets.Bullet;
import maze.Maze;

import java.awt.*;

public abstract class StandardMonster implements Monster {

    private int damage;
    private int healthPoints;
    private ShootingBehavior shootingBehavior;
    private Point position;

    private DirectionState directionState;
    
    
    @Override
    public void attack(Hero hero) {
        hero.attackedByMonster(this);
    }

    @Override
    public int getDamage() {
        return damage;
    }


    @Override
    public void move(Command moveCommand, Maze maze) {
        moveCommand.execute(this,maze);
    }

    @Override
    public int getHealthPoints() {
        return healthPoints;
    }

   @Override
   public void setDirectionState(DirectionState state) {
	   directionState = state;
   }

   
    @Override
    public void takeBullet(Bullet bullet) {
        takeDamage(bullet.getDamage());
    }

    @Override
    public void receiveExplosionDamage(int damage) {
        takeDamage(damage);
    }

    private void takeDamage(int damage) {
        if (damage >= healthPoints) {
            healthPoints = 0;
            //dead notify canvas
        } else {
            healthPoints -= damage;
        }
    }

    @Override
    public void shoot() {
        shootingBehavior.shoot(position,directionState);
    }

    @Override
    public Point getPosition() {
        return this.position;
    }

    @Override
    public void setPosition(Point position) {
        this.position = position;
    }

    protected void setHealthPoints(){
        healthPoints = getMonsterHealthPoints();
    }
    protected void setShootingBehavior() {
        shootingBehavior = getShootingBehavior();
    }

    protected void setDamage(){
        damage = getMonsterDamage();
    }

    protected abstract int getMonsterDamage();
    protected abstract ShootingBehavior getShootingBehavior();
    protected abstract int getMonsterHealthPoints();
}
