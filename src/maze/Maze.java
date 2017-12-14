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
import observer.ObservedSubject;
import observer.SubjectObserver;

import java.awt.*;
import java.util.ArrayList;

public class Maze implements ObservedSubject {

    MazeComponents components = new MazeComponents();


    Hero hero;
    protected int[][] maze;
    private ArrayList<SubjectObserver> observers;
    private Drawable[][] movingObjectsLayer;
    private Drawable[][] roadAndWallsLayer;
    private Drawable[][] pickablesLayer;


    public Drawable getItemInPosition(Point position) {
        try {
            if(movingObjectsLayer[position.x][position.y] != null) {
                return movingObjectsLayer[position.x][position.y];
            } else if (pickablesLayer[position.x][position.y]!= null){
                return pickablesLayer[position.x][position.y];
            } else {
                return roadAndWallsLayer[position.x][position.y];
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
        roadAndWallsLayer[position.x][position.y] = new Road();
        components.walls.remove(obstacle);
    }
    public void removeTrap(Trap trap){
        Point position = trap.getPosition();
        pickablesLayer[position.x][position.y] = null;
        components.traps.remove(trap);
    }
    public void removeBomb(Bomb bomb){
        Point position = bomb.getPosition();
        pickablesLayer[position.x][position.y] = null;
        components.bombs.remove(bomb);
    }
    public void removeGift(Gift gift){
        Point position = gift.getPosition();
        pickablesLayer[position.x][position.y] = null;
        components.gifts.remove(gift);
    }
    public void removeWeapon(Weapon weapon){
        Point position = weapon.getPosition();
        pickablesLayer[position.x][position.y] = null;
        components.weapons.remove(weapon);
    }
    public void removeShield(Shield shield){
        Point position = shield.getPosition();
        pickablesLayer[position.x][position.y] = null;
        components.shields.remove(shield);
    }
    public void removeMonster(Monster monster){
        Point position = monster.getPosition();
        movingObjectsLayer[position.x][position.y] = null;
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