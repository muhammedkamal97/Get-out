package gameLevels;

import drawables.characters.Monster;
import drawables.obstacles.Bomb;
import drawables.obstacles.Trap;
import drawables.obstacles.Wall;
import drawables.pickables.Gift;
import drawables.pickables.Shield;
import drawables.pickables.Weapon;


public interface LevelProperties {


    public int getNumberOfLives();
    public int getNumberOfMonsters();
    public int getNumberOfTraps();
    public int getNumberOfGifts();
    public int getNumberOfBombs();
    public int getNumberOfWeapons();
    public int getNumberOfShields();

    public int getMazeWidth();
    public int getMazeLength();


    public Class[] getAllowedWeapons();
    public Class[] getAllowedWalls();
    public Class[] getAllowedMonsters();
    public Class[] getAllowedGifts();
    public Class[] getAllowedBombs();
    public Class[] getAllowedTraps();
    public Class[] getAllowedShields();


}
