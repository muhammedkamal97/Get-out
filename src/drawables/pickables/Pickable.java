package drawables.pickables;

import drawables.Drawable;
import drawables.characters.Handler;
import drawables.characters.Hero;

public interface Pickable extends Drawable{
    public void addToHandler(Hero hero);
}
