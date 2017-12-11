package gameCore;

import gameLoop.GameLoop;
import maze.Maze;

public interface IGameCore {

    public void InitializeMaze(int level);
    public GameLoop getGameLoop();

}
