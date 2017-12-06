package drawables.pickables.gifts;

import javafx.scene.canvas.Canvas;

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
	public void drawOnCanvas(Canvas canvas) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public int giveHealthPoints() {
		return HEALTH_POINTS;
	}

}
