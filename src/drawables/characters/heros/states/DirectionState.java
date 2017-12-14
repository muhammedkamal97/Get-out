package drawables.characters.heros.states;

import drawables.characters.MovingObject;
import drawables.pickables.Weapon;
import javafx.scene.canvas.GraphicsContext;

import java.awt.*;

public interface DirectionState {

    public void shoot(Weapon weapon, Point position);

    public void Draw(GraphicsContext gc, Point pt, int width, int height, MovingObject heroOrMonster);

}
