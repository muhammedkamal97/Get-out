package drawables.pickables;

import drawables.characters.Hero;
import javafx.scene.canvas.GraphicsContext;
import maze.Maze;

import java.awt.*;

public class Key implements Pickable {

    private Point position;
    private Maze maze;

    @Override
    public void addToHandler(Hero hero) {
        hero.pickedKey();
        destroy();
    }

    @Override
    public void destroy() {
        maze.removeKey(this);
    }

    @Override
    public void drawOnCanvas(GraphicsContext gc, Point pt, int widthCell, int heightCell) {

    }

    @Override
    public Point getPosition() {
        return this.position;
    }

    @Override
    public void setPosition(Point position) {
        this.position = position;
    }

    @Override
    public void takeDamage(int damage) {
        //no damage
    }

    @Override
    public void setMaze(Maze maze) {
        this.maze = maze;
    }
}
