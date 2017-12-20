package gameLevels.levelBuilder;

import gameLevels.LevelProperties;
import gameLevels.Levels.Level3;
import gameLevels.LevelsFactory;
import maze.Maze;

public class LevelEngineer {

    private LevelsFactory levelFactory;
    private LevelBuilder levelBuilder;

    public LevelEngineer() {
        this.levelFactory = new LevelsFactory();
        this.levelBuilder = new LevelBuilder();
    }

    public void setLevelBuilder(String level) {
        LevelProperties currentLevel = this.levelFactory.factoryMethod(level);
        this.levelBuilder.setLevelProperties(currentLevel);
    }

    public Maze buildTheMaze() {
        return this.levelBuilder.buildLevelMaze();
    }

}
