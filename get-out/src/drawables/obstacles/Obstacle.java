package drawables.obstacles;

import drawables.Drawable;
import maze.Maze;

import java.awt.*;

public interface Obstacle extends Drawable{

    public void destroy();
    public void setMaze(Maze maze);

}
