package drawables.pickables.gifts;

import drawables.characters.Hero;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

import java.awt.*;

public class Coins extends StandardGift{
	private final String IMAGE_URL = " ";
	private final int COIN_VALUE  = 5 ;
	
	public Coins() {
		super();

	}
	public String getIMAGE_URL() {
		return IMAGE_URL;
	}

	@Override
	public void addGift(Hero hero) {
		hero.increaseCoins(COIN_VALUE);
		destroy();
	}
}
