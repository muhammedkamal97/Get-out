package drawables.pickables;

import drawables.Drawable;
import drawables.characters.Handler;
import drawables.characters.Hero;
import maze.Maze;

public interface Pickable extends Drawable{
    public void addToHandler(Hero hero);
    public void destroy();
}
