package gameLevels.levelBuilder.mazeInitializer;

import maze.ImpMaze;
import maze.Maze;
import maze.MazeComponents;

public class MazeAssembler {

    public Maze assembleMaze(MazeComponents components){
    	Maze maze = new ImpMaze();
    	maze.setMaze(components.mazeStructure);
    	maze.mapDrawableIntoMaze(components);
        return maze;
    }
}
