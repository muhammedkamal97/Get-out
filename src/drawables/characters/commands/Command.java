package drawables.characters.commands;

import drawables.characters.Hero;
import drawables.characters.MovingObject;
import maze.Maze;

public interface Command {
    public void execute(Hero hero , Maze maze);
}
