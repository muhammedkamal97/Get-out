package drawables.obstacles;

import drawables.Drawable;
import maze.Maze;

public interface Obstacle extends Drawable, ReactorsOnTouch {

    public void destroy();
    public void setMaze(Maze maze);

}
