package gameCore;

import View.Graphics.ImagesMaps.MazeMap;
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

    private static volatile RunnerGameAdapter instance;
    private static Object mutex = new Object();

    private RunnerGameAdapter(){}

    public static RunnerGameAdapter getInstance() {
        RunnerGameAdapter result = instance;
        if (result == null) {
            synchronized (mutex) {
                result = instance;
                if (result == null)
                    instance = result = new RunnerGameAdapter();
            }
        }
        return result;
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
