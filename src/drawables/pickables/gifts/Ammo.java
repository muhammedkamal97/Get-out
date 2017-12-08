package drawables.pickables.gifts;

import drawables.characters.Hero;
import javafx.scene.canvas.Canvas;

public class Ammo extends StandardGift{


    @Override
    public void drawOnCanvas(Canvas canvas) {

    }

    @Override
    public void addGift(Hero hero) {
        hero.acceptAmmo();
    }
}
