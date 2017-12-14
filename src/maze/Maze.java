package maze;

import drawables.Drawable;
import drawables.characters.Hero;
import drawables.characters.Monster;
import drawables.obstacles.Bomb;
import drawables.obstacles.Trap;
import drawables.obstacles.Wall;
import drawables.obstacles.walls.Steel;
import drawables.pickables.Gift;
import drawables.pickables.Shield;
import drawables.pickables.Weapon;
import observer.ObservedSubject;
import observer.SubjectObserver;

import java.awt.*;
import java.util.ArrayList;

public class Maze implements ObservedSubject {

    MazeComponents components = new MazeComponents();


    Hero hero;
    protected int[][] maze;
    private Drawable[][] drawables;
    private ArrayList<SubjectObserver> observers;


    public Drawable getItemInPosition(Point position) {
        try {
            return drawables[position.x][position.y];
        } catch (Exception e) {
            return new Steel();
        }
    }

    public void setDrawables(Drawable[][] drawables) {
        this.drawables = drawables;
    }
    public void setMazeHero(Hero hero) {
        this.hero = hero;
    }
    public void setComponents(MazeComponents components){
        this.components = components;
    }
    public void setMaze(int[][] maze){
    	this.maze = maze;
    }

    public void removeWall(Wall obstacle){
        components.walls.remove(obstacle);
    }
    public void removeTrap(Trap trap){
        components.traps.remove(trap);
    }
    public void removeBomb(Bomb bomb){
        components.bombs.remove(bomb);
    }
    public void removeGift(Gift gift){
        components.gifts.remove(gift);
    }
    public void removeWeapon(Weapon weapon){
        components.weapons.remove(weapon);
    }
    public void removeShield(Shield shield){
        components.shields.remove(shield);
    }
    public void removeMonster(Monster monster){
        components.monsters.remove(monster);
    }


    public Point getDimensions(){
        return new Point(
                components.mazeStructure[0].length ,
                components.mazeStructure.length
        );
    }

    @Override
    public void notifyObservers() {
        for (int i = 0 ; i < observers.size() ; i++){
            observers.get(i).update();
        }
    }

    @Override
    public void RegisterObserver(SubjectObserver observer) {
        observers.add(observer);
    }
}