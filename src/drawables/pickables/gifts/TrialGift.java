package drawables.pickables.gifts;

import javafx.scene.canvas.Canvas;

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
	public void drawOnCanvas(Canvas canvas) {
		//gui;
	}

	@Override
	public int giveTrial() {
		return TRIAL;
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
