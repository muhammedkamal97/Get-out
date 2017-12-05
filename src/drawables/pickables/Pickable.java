package drawables.pickables;

import drawables.Drawable;
import drawables.characters.Handler;

public interface Pickable extends Drawable{
    public void addToHandler(Handler hero);
}
