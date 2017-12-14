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
import drawables.roads.Road;
import observer.MotionObserver;
import observer.ObservedSubject;
import observer.SubjectObserver;

import java.awt.*;
import java.util.ArrayList;

public class Maze implements ObservedSubject, MotionObserver{

    MazeComponents components = new MazeComponents();

    private Point heroPosition;
    private Hero hero;
    protected int[][] maze;
    private ArrayList<SubjectObserver> observers;
    private Drawable[][] movingObjectsLayer;
    private Drawable[][] roadAndWallsLayer;
    private Drawable[][] pickablesLayer;


    public Drawable getItemInPosition(Point position) {
        try {
            if(movingObjectsLayer[position.y][position.x] != null) {
                return movingObjectsLayer[position.y][position.x];
            } else if (pickablesLayer[position.y][position.x]!= null){
                return pickablesLayer[position.y][position.x];
            } else {
                return roadAndWallsLayer[position.y][position.x];
            }
        } catch (Exception e) {
            return new Steel();
        }
    }

    public void setMovingObjectsLayer(Drawable[][] layer){
        movingObjectsLayer = layer;
    }

    public void setPickablesLayer(Drawable[][] pickablesLayer) {
        this.pickablesLayer = pickablesLayer;
    }

    public void setRoadAndWallsLayer(Drawable[][] roadAndWallsLayer) {
        this.roadAndWallsLayer = roadAndWallsLayer;
    }

    public void setMazeHero(Hero hero) {
        movingObjectsLayer[1][1] = hero;
        hero.setPosition(new Point(1,1));
        heroPosition = hero.getPosition();
        this.hero = hero;
    }
    public void setComponents(MazeComponents components){
        this.components = components;
    }
    public void setMaze(int[][] maze){
    	this.maze = maze;
    }

    public void removeWall(Wall obstacle){
        Point position = obstacle.getPosition();
        roadAndWallsLayer[position.y][position.x] = new Road();
        roadAndWallsLayer[position.y][position.x].setPosition(new Point(position.x,position.y));
        components.walls.remove(obstacle);
    }
    public void removeTrap(Trap trap){
        Point position = trap.getPosition();
        pickablesLayer[position.y][position.x] = null;
        components.traps.remove(trap);
    }
    public void removeBomb(Bomb bomb){
        Point position = bomb.getPosition();
        pickablesLayer[position.y][position.x] = null;
        components.bombs.remove(bomb);
    }
    public void removeGift(Gift gift){
        Point position = gift.getPosition();
        pickablesLayer[position.y][position.x] = null;
        components.gifts.remove(gift);
    }
    public void removeWeapon(Weapon weapon){
        Point position = weapon.getPosition();
        pickablesLayer[position.y][position.x] = null;
        components.weapons.remove(weapon);
    }
    public void removeShield(Shield shield){
        Point position = shield.getPosition();
        pickablesLayer[position.y][position.x] = null;
        components.shields.remove(shield);
    }
    public void removeMonster(Monster monster){
        Point position = monster.getPosition();
        movingObjectsLayer[position.y][position.x] = null;
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

    @Override
    public void updateMovingObjects() {
        movingObjectsLayer[heroPosition.y][heroPosition.x] = null;
        heroPosition = hero.getPosition();
        movingObjectsLayer[hero.getPosition().y][hero.getPosition().x] = hero;
    }
}