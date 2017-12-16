package drawables.pickables.gifts;

import java.awt.Point;

import View.Graphics.ImagesMaps.MazeMap;
import drawables.characters.Hero;
import drawables.pickables.Gift;
import javafx.scene.canvas.GraphicsContext;
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
        maze.removeGift(this);
    }


    @Override
    public void drawOnCanvas(GraphicsContext gc, Point pt, int widthCell, int heightCell) {
        MazeMap map = MazeMap.getInstance();
        gc.drawImage(map.getBufferedImage("Gift"),
                pt.getX(), pt.getY(),
                widthCell, heightCell);
    }
}
