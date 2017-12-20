package gameCore;

import gameLevels.levelBuilder.LevelEngineer;
import gameLoop.GameLoop;
import gameLoop.RunnerLoop;
import maze.Maze;

public class RunnerGameAdapter implements IGameCore{

    Maze maze;
    private static RunnerGameAdapter gameCore;

    public static RunnerGameAdapter createGameCore(){
        if(gameCore == null){
            gameCore = new RunnerGameAdapter();
            return gameCore;
        }
        return gameCore;
    }

    private RunnerGameAdapter(){
        //nothing
    }

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
