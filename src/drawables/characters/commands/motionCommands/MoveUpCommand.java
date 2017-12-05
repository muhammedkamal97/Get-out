package drawables.characters.commands.motionCommands;

import drawables.Drawable;
import drawables.characters.Hero;
import drawables.characters.MovingObject;
import drawables.characters.commands.Command;
import maze.Maze;

import constants.GameContract.Directions;
import java.awt.*;

public class MoveUpCommand implements Command{


    @Override
    public void execute(Hero hero, Maze maze) {

        hero.setDirection(Directions.UP);

        MoveUtilities utilities = new MoveUtilities();
        Point position = hero.getPosition();
        position.y = position.y + 1;
        Drawable itemInNextPosition = maze.getItemInPosition(position);
        boolean isValidMove = utilities.isAValidMove(itemInNextPosition);

        if(isValidMove){
            utilities.performMove(itemInNextPosition,hero);
        }

    }
}
