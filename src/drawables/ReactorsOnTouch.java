package drawables;

import drawables.characters.Hero;
import drawables.characters.MovingObject;

public interface ReactorsOnTouch extends MovingObject{

    public void pumpInto(Hero hero);
}
