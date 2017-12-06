package gameLevels;

import drawables.characters.Monster;
import drawables.characters.monsters.Behaviors.ShootingBehavior;
import drawables.obstacles.Trap;
import drawables.obstacles.Wall;
import drawables.pickables.Weapon;

import java.util.ArrayList;

public interface LevelProperties {

    public int getNumberOfLives();
    public int getNumberOfMonsters();
    public int getNumberOfTraps();
    public int getNumberOfGifts();

    public ArrayList<Weapon> getAllowedWeapons();
    public ArrayList<Wall> getAllowedWalls();
    public ArrayList<Monster> getAllowedMonsters();
    public ArrayList<Trap> getAllowedTraps();
}
