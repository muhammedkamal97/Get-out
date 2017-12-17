package observer;

import drawables.characters.Hero;
import drawables.characters.Monster;

import java.awt.*;

public interface MazeLayersObserver {

    public void updateMonsterPosition(Point oldPosition ,Monster monster);
    public void updateRoadsAndWalls(Point position);
    public void updatePickables(Point position);

}
