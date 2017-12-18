package drawables.characters.heros.states;

import drawables.characters.MovingObject;
import drawables.pickables.Weapon;
import drawables.pickables.weapons.bullets.bulletDirectionStates.ShootRight;
import javafx.scene.canvas.GraphicsContext;

import java.awt.*;

public class DirectionRightState implements DirectionState {
    @Override
    public void shoot(Weapon weapon, Point position) {

        weapon.shoot(new ShootRight(), position);
    }

    @Override
    public void Draw(GraphicsContext gc, Point pt, int widthCell, int heightCell, MovingObject heroOrMonster) {
        gc.clearRect(pt.getX() - 20,
                pt.getY(),
                widthCell, heightCell);
        heroOrMonster.getRightSprite().drawNextSprite(
                gc, widthCell, heightCell,
                (int) pt.getX(),
                (int) pt.getY());

    }

    @Override
    public void DrawReleased(GraphicsContext gc, Point pt, int widthCell, int heightCell, MovingObject heroOrMonster) {
        gc.clearRect(pt.getX() - 20,
                pt.getY(),
                widthCell, heightCell);
        heroOrMonster.getRightSprite().drawReleased(
                gc, widthCell, heightCell,
                (int) pt.getX(),
                (int) pt.getY());
    }
}
