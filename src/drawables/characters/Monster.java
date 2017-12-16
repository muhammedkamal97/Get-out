package drawables.characters;

import maze.Maze;
import observer.MonsterObservable;

import java.awt.*;

public interface Monster extends MovingObject, MonsterObservable{

    public void attack(Hero hero);
    public int getDamage();
    public void shoot();
    public void setPastPosition(Point position);

}
