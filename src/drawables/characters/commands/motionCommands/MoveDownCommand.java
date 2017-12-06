package drawables.characters.commands.motionCommands;

import drawables.Drawable;
import drawables.characters.MovingObject;
import drawables.characters.commands.Command;
import maze.Maze;

import java.awt.*;
import constants.GameContract.Directions;

public class MoveDownCommand implements Command {


    @Override
    public void execute(MovingObject object, Maze maze) {

        object.setDirection(Directions.DOWN);

        MoveUtilities utilities = new MoveUtilities();
        Point position = object.getPosition();
        position.y = position.y - 1;

        object.setPosition(position);
        Drawable itemInNextPosition = maze.getItemInPosition(position);
        boolean isValidMove = utilities.isAValidMove(itemInNextPosition);

        if(isValidMove){
            utilities.performMove(itemInNextPosition,object);
        }

    }
}
