package drawables.pickables.gifts;

import drawables.characters.Hero;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

import java.awt.*;

public class TrialGift extends StandardGift{
	private final String Image_URL = " ";
	private final int TRIAL = 1;

	public TrialGift() {
		super();
	}

	public String getImage_URL() {
		return Image_URL;
	}

	@Override
	public void drawOnCanvas(GraphicsContext gc, Point pt, int width, int height) {
		//gui;
	}

	@Override
	public void addGift(Hero hero) {
		hero.increaseTrials();
	}
}
