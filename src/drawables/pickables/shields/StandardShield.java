package drawables.pickables.shields;

import java.awt.Point;

import View.Graphics.ImagesMaps.MazeMap;
import drawables.characters.Hero;
import drawables.pickables.Shield;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import maze.Maze;

public abstract  class StandardShield implements Shield {
	protected Point position;
	protected Maze maze;
	
	@Override
	public void addToHandler(Hero hero) {		
		protectedFrom(hero);
		
	}

	@Override
	public void setMaze(Maze maze) {this.maze = maze;}

	@Override
	public void drawOnCanvas(GraphicsContext gc, Point pt, int widthCell, int heightCell) {
		MazeMap map = MazeMap.getInstance();
		gc.drawImage(map.getBufferedImage("Shield"),
				pt.getX() - (widthCell / 2),
				pt.getY() - (heightCell / 2),
				widthCell, heightCell);
	}

	@Override
	public void takeDamage(int damage) {
		destroy();
	}

	@Override
	public void destroy() {

	}

	@Override
	public Point getPosition() {
		return this.position;
	}

	@Override
	public void setPosition(Point position) {
		this.position = position;
	}

}
