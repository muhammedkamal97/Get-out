package drawables.pickables.shields;

import java.awt.Point;

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
	public void drawOnCanvas(GraphicsContext gc, Point pt, int width, int height) {
		//Gui
		
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
