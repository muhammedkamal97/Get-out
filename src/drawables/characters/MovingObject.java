package drawables.characters;


import drawables.Drawable;
import drawables.characters.commands.Command;

import java.awt.*;

public interface MovingObject extends Drawable{

    public void move(Command moveCommand);
    public int getHealthPoints();
    public void setDirection(int direction);
    public int getDirection();
}
