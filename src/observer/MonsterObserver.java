package observer;

import drawables.characters.Monster;

import java.awt.*;

/**
 * Created by Mahmoud on 12/15/2017.
 */
public interface MonsterObserver {

    public void updateMonsterObserver(Monster monster, Point position);
}
