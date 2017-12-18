package gameLoop;

import drawables.Drawable;
import drawables.characters.Hero;
import maze.Maze;
import observer.*;

import java.awt.*;

public interface GameLoop extends SubjectObserver, DeathObserver,HeroWinObserver , ObservableGame{

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
    public void lookLeft();
    public void lookRight();
    public void lookUp();
    public void lookDown();
    public void registerAsBombObserver(BombExplosionObserver observer);
    public void registerAsMazeLayerObserver(MazeLayersObserver observer);
        //Sarah's
    public Point getMazeDimensions();
    public Drawable[][] getMovingObjectsLayer();
    public Drawable[][] getRoadAndWallsLayer();
    public Drawable[][] getPickablesLayer();

}
