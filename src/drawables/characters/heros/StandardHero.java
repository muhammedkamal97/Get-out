package drawables.characters.heros;

import drawables.characters.Hero;
import drawables.characters.Monster;
import drawables.characters.commands.Command;
import drawables.obstacles.Trap;
import drawables.pickables.Pickable;
import drawables.pickables.Weapon;
import drawables.pickables.weapons.bullets.Bullet;
import javafx.scene.canvas.Canvas;

import java.awt.*;
import java.util.ArrayList;

import constants.GameContract.Directions;
import maze.Maze;

public abstract class StandardHero implements Hero {


    private int healthPoints;
    private int armorPoints;
    private int direction = Directions.DOWN;
    private Point position;
    private Weapon currentWeapon;
    private ArrayList<Weapon> allWeapons = new ArrayList<>();

    @Override
    public void trapHero(Trap trap) {
        int damage = trap.getDamage();
        takeDamage(damage);
    }

    private void takeDamage(int damage){
        if(damage < armorPoints){
            armorPoints -= damage;
        } else {
            damage = damage - armorPoints;
            armorPoints = 0;
            if(damage >= healthPoints) {
                healthPoints = 0;
            }
        }
    }
    @Override
    public void attackedByMonster(Monster monster) {
        int damage = monster.getDamage();
        takeDamage(damage);
    }
    @Override
    public void shoot() {
        if(currentWeapon != null) {
            currentWeapon.shoot(direction, position);
        }
    }

    @Override
    public void holdNextWeapon() {
        if(currentWeapon != null){
            int index = allWeapons.indexOf(currentWeapon);
            if(index != allWeapons.size() - 1)
                currentWeapon = allWeapons.get(index + 1);
        }
    }

    @Override
    public void holdPreviousWeapon() {

        if(currentWeapon != null){
            int index = allWeapons.indexOf(currentWeapon);
            if(index != 0)
                currentWeapon = allWeapons.get(index - 1);
        }
    }

    @Override
    public void addWeapon(Weapon weapon) {
        allWeapons.add(weapon);
        currentWeapon = weapon;
    }

    @Override
    public void move(Command moveCommand, Maze maze) {
        moveCommand.execute(this,maze);
    }
    @Override
    public void setDirection(int direction) {
        this.direction = direction;
    }

    @Override
    public void takeBullet(Bullet bullet) {
        int damage = bullet.getDamage();
        takeDamage(damage);
    }

    @Override
    public int getDirection() {
        return direction;
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
    public void setPosition(Point position) {
        this.position = position;
    }

    protected int getNumberOfWeapons(){
        return allWeapons.size();
    }
    protected void removeFirstWeapon(){
        allWeapons.remove(0);
    }
}
