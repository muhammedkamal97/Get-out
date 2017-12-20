package drawables.characters.commands.motionCommands;

import drawables.Drawable;
import drawables.characters.MovingObject;
import drawables.characters.commands.Command;
import drawables.characters.heros.states.DirectionDownState;
import drawables.characters.heros.states.DirectionUpState;
import maze.Maze;

import java.awt.*;

public class MoveUpCommand implements Command {


    @Override
    public boolean execute(MovingObject object, Maze maze) {

        object.setDirectionState(DirectionUpState.createDirectionUpState());

        MoveUtilities utilities = new MoveUtilities();
        Point position = new Point();

        position.x = object.getPosition().x;
        position.y = object.getPosition().y - 1;
        Drawable itemInNextPosition = maze.getItemInPosition(position);
        boolean isValidMove = utilities.isAValidMove(itemInNextPosition);

        if(isValidMove){
            return utilities.performMove(itemInNextPosition,object);

        }
        return false;

    }
}
