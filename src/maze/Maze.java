package maze;

import drawables.Drawable;
import drawables.characters.Hero;
import drawables.characters.Monster;
import drawables.obstacles.Obstacle;

import java.awt.*;
import java.util.ArrayList;

public abstract class Maze {

    ArrayList<Monster> monsters;
    ArrayList<Obstacle> obstacles;
    Hero hero;

    public abstract Drawable getItemInPosition(Point position);


    public void setMazeHero(Hero hero) {
        this.hero = hero;
    }

    public void setMonsters(ArrayList<Monster> monsters) {
        this.monsters = monsters;
    }

    public void setObstacles(ArrayList<Obstacle> obstacles) {
        this.obstacles = obstacles;
    }

}