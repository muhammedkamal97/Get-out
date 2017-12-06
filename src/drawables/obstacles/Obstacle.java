package drawables.obstacles;

import drawables.Drawable;
import drawables.characters.MovingObject;
import drawables.characters.monsters.Behaviors.MotionBehavior;
import maze.Maze;

import java.awt.*;

public interface Obstacle extends Drawable{

    public void destroy();
    public void setMaze(Maze maze);

}
