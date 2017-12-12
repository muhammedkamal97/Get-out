package drawables.characters.commands;

import drawables.characters.Hero;
import drawables.characters.MovingObject;
import maze.Maze;

public interface Command {
    public boolean execute(MovingObject object , Maze maze);
}
