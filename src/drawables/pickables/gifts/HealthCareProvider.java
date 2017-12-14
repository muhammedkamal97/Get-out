package drawables.pickables.gifts;

import drawables.characters.Hero;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

import java.awt.*;

/**
 * @author AyaOsman
 *
 */
public class HealthCareProvider extends StandardGift{
	private final String IMAGE_URL = " ";
	private final int HEALTH_POINTS = 5;
	public HealthCareProvider() {
		super();
	}
	public String getIMAGE_URL() {
		return IMAGE_URL;
	}

	@Override
	public void addGift(Hero hero) {
		hero.increaseHealthPoints(HEALTH_POINTS);
	}
}
