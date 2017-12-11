package gameCore;

import gameLevels.levelBuilder.LevelEngineer;
import gameLoop.GameLoop;
import gameLoop.RunnerLoop;
import maze.Maze;

public class RunnerGameAdapter implements IGameCore{

    Maze maze;

    @Override
    public void InitializeMaze(int level) {
        LevelEngineer engineer = new LevelEngineer();
        engineer.setLevelBuilder("Level" + level);
        maze = engineer.buildTheMaze();
    }

    @Override
    public GameLoop getGameLoop() {
        GameLoop loop = new RunnerLoop();
        loop.setLoopMaze(maze);
        return loop;
    }
}
