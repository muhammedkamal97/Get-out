package gameLevels.Levels;

import drawables.characters.monsters.NormalMonster;
import drawables.obstacles.bombs.Lazer;
import drawables.obstacles.bombs.NormalBomb;
import drawables.obstacles.traps.Cage;
import drawables.obstacles.traps.Hole;
import drawables.obstacles.walls.Bricks;
import drawables.obstacles.walls.Steel;
import drawables.obstacles.walls.Tree;
import drawables.obstacles.walls.Wood;
import drawables.pickables.gifts.Coins;
import drawables.pickables.shields.BombShield;
import drawables.pickables.weapons.MP5;
import gameLevels.LevelProperties;

public class level_test implements LevelProperties{
    @Override
    public int getNumberOfLives() {
        return 0;
    }

    @Override
    public int getNumberOfMonsters() {
        return 0;
    }

    @Override
    public int getNumberOfTraps() {
        return 2;
    }

    @Override
    public int getNumberOfGifts() {
        return 2;
    }

    @Override
    public int getNumberOfBombs() {
        return 0;
    }

    @Override
    public int getNumberOfWeapons() {
        return 0;
    }

    @Override
    public int getNumberOfShields() {
        return 2;
    }

    @Override
    public int getMazeWidth() {
        return 20;
    }

    @Override
    public int getMazeLength() {
        return 20;
    }

    @Override
    public Class[] getAllowedWeapons() {
        return new Class[]{
                MP5.class};
    }

    @Override
    public Class[] getAllowedWalls() {
        return new Class[]{Tree.class, Bricks.class, Wood.class};
    }

    @Override
    public Class[] getAllowedMonsters() {
        return new Class[]{NormalMonster.class};
    }

    @Override
    public Class[] getAllowedGifts() {
        return new Class[]{Coins.class};
    }

    @Override
    public Class[] getAllowedBombs() {
        return new Class[]{NormalBomb.class};
    }

    @Override
    public Class[] getAllowedTraps() {
        return new Class[]{Cage.class};
    }

    @Override
    public Class[] getAllowedShields() {
        return new Class[]{BombShield.class};
    }
}
