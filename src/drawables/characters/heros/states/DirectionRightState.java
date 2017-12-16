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
//        heroOrMonster.getRightSprite().reset();
//        int i = (int)heroOrMonster.getPosition().getY() - widthCell;
//        while (i < heroOrMonster.getPosition().getY()) {
//            gc.clearRect(i, heroOrMonster.getPosition().getY(), widthCell, heightCell);
//            i++;
//            heroOrMonster.getRightSprite().drawNextSprite(gc, widthCell, heightCell, i, (int)heroOrMonster.getPosition().getY());
//        }
        gc.clearRect(pt.getX() - 1 - (widthCell / 2),
                pt.getY() - (heightCell / 2),
                widthCell, heightCell);
        heroOrMonster.getRightSprite().drawNextSprite(
                gc, widthCell, heightCell,
                (int) pt.getX(),
                (int) pt.getY());

    }

    @Override
    public void DrawReleased(GraphicsContext gc, Point pt, int widthCell, int heightCell, MovingObject heroOrMonster) {
        gc.clearRect(pt.getX() - 1 - (widthCell / 2),
                pt.getY() - (heightCell / 2),
                widthCell, heightCell);
        heroOrMonster.getRightSprite().drawReleased(
                gc, widthCell, heightCell,
                (int) pt.getX(),
                (int) pt.getY());
    }
}
