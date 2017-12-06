package drawables.pickables.gifts;

import java.awt.Point;

import drawables.characters.Hero;
import drawables.pickables.Gift;
import javafx.scene.canvas.Canvas;

/**
 * @author AyaOsman
 *
 */
public abstract class StandardGift implements Gift{
	protected Point position = new Point();
	@Override
	public void addToHandler(Hero hero) {
		hero.addGift(this);
	}

	@Override
	public abstract void drawOnCanvas(Canvas canvas);

	@Override
	public Point getPosition() {
		return position;
	}

	@Override
	public void setPosition(Point position) {
		this.position = position;
	}
	@Override
	public int giveTrial() {
		return 0;
	}
	@Override
	public int giveHealthPoints() {
		return 0;
	}
	@Override
	public int giveCoins() {
		return 0;
	}

}
