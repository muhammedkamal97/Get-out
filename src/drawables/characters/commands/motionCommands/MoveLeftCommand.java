package drawables.characters.commands.motionCommands;

import drawables.Drawable;
import drawables.characters.MovingObject;
import drawables.characters.commands.Command;
import maze.Maze;

import constants.GameContract.Directions;
import java.awt.*;

public class MoveLeftCommand implements Command {

    @Override
    public void execute(MovingObject object, Maze maze) {

        object.setDirection(Directions.LEFT);

        MoveUtilities utilities = new MoveUtilities();
        Point position = object.getPosition();
        position.x = position.x - 1;
        object.setPosition(position);
        Drawable itemInNextPosition = maze.getItemInPosition(position);
        boolean isValidMove = utilities.isAValidMove(itemInNextPosition);

        if(isValidMove){
            utilities.performMove(itemInNextPosition,object);
        }

    }

}
