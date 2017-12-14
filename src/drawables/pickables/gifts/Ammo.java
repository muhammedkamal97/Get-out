package drawables.pickables.gifts;

import drawables.characters.Hero;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

import java.awt.*;

public class Ammo extends StandardGift{

    @Override
    public void addGift(Hero hero) {
        hero.acceptAmmo();
    }
}
