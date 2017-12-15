package observer;

import drawables.characters.Monster;

/**
 * Created by Mahmoud on 12/15/2017.
 */
public interface MonsterObservable {

    public void notifyMonsterObservers();
    public void registerMonsterObserver(MonsterObserver observer);

}
