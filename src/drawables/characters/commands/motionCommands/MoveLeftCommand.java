package drawables.characters.commands.motionCommands;

import drawables.Drawable;
import drawables.characters.MovingObject;
import drawables.characters.commands.Command;
import drawables.characters.heros.states.DirectionDownState;
import drawables.characters.heros.states.DirectionLeftState;
import maze.Maze;

import java.awt.*;

public class MoveLeftCommand implements Command {

    @Override
    public boolean execute(MovingObject object, Maze maze) {

        object.setDirectionState(DirectionLeftState.createDirectionLeftState());

        MoveUtilities utilities = new MoveUtilities();

        Point position = new Point();
        position.x = object.getPosition().x - 1;
        position.y = object.getPosition().y;
        Drawable itemInNextPosition = maze.getItemInPosition(position);
        boolean isValidMove = utilities.isAValidMove(itemInNextPosition);
        if(isValidMove){
          return   utilities.performMove(itemInNextPosition,object);

        }
        return false;
    }

}
