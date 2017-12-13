package drawables.pickables.gifts;

import java.awt.Point;

import drawables.characters.Hero;
import drawables.pickables.Gift;
import maze.Maze;

/**
 * @author AyaOsman
 */
public abstract class StandardGift implements Gift {
    protected Point position = new Point();
    protected Maze maze;

    @Override
    public void addToHandler(Hero hero) {
        addGift(hero);
    }

    @Override
    public Point getPosition() {
        return position;
    }

    @Override
    public void setMaze(Maze maze) { this.maze = maze;}

    @Override
    public void setPosition(Point position) {
        this.position = position;
    }

    @Override
    public void takeDamage(int damage) {
        destroy();
    }

    @Override
    public void destroy() {

    }
}
