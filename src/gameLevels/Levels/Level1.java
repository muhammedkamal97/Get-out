package gameLevels.Levels;

import gameLevels.LevelProperties;

public class Level1 implements LevelProperties{


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
        return 0;
    }

    @Override
    public int getNumberOfGifts() {
        return 0;
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
        return 0;
    }

    @Override
    public int getMazeWidth() {
        return 0;
    }

    @Override
    public int getMazeLength() {
        return 0;
    }

    @Override
    public Class[] getAllowedWeapons() {
        return new Class[0];
    }

    @Override
    public Class[] getAllowedWalls() {
        return new Class[0];
    }

    @Override
    public Class[] getAllowedMonsters() {
        return new Class[0];
    }

    @Override
    public Class[] getAllowedGifts() {
        return new Class[0];
    }

    @Override
    public Class[] getAllowedBombs() {
        return new Class[0];
    }

    @Override
    public Class[] getAllowedTraps() {
        return new Class[0];
    }

    @Override
    public Class[] getAllowedShields() {
        return new Class[0];
    }
}
