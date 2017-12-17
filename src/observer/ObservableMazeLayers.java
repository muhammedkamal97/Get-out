package observer;

import drawables.characters.Hero;
import drawables.characters.Monster;

import java.awt.*;

public interface ObservableMazeLayers {

    public void registerMazeLayersObserver(MazeLayersObserver observer);
    public void notifyHeroChange(Point oldPosition , Hero hero);
    public void notifyMonsterChange(Point oldPosition , Monster monster);
    public void notifyPickablesChange(Point position);
    public void notifyWallsChange(Point position);
}
