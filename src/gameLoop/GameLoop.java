package gameLoop;

import drawables.characters.Hero;
import maze.Maze;
import observer.SubjectObserver;

public interface GameLoop extends SubjectObserver {

    public void setLoopMaze(Maze maze);
    public void initiateLoop();
    public void setHero(Hero hero);
    public void moveHeroUp();
    public void moveHeroLeft();
    public void moveHeroDown();
    public void moveHeroRight();
    public void shoot();
    public void holdNextWeapon();
    public void holdPreviousWeapon();
}
