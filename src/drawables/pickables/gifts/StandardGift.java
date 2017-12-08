package drawables.pickables.gifts;

import java.awt.Point;

import drawables.characters.Hero;
import drawables.pickables.Gift;

/**
 * @author AyaOsman
 *
 */
public abstract class StandardGift implements Gift{
	protected Point position = new Point();


	@Override
	public void addToHandler(Hero hero) {
		addGift(hero);
	}

	@Override
	public Point getPosition() {
		return position;
	}

	@Override
	public void setPosition(Point position) {
		this.position = position;
	}


}
