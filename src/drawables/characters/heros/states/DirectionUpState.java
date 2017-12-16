package drawables.characters.heros.states;

import drawables.characters.MovingObject;
import drawables.pickables.Weapon;
import drawables.pickables.weapons.bullets.bulletDirectionStates.ShootUp;
import javafx.scene.canvas.GraphicsContext;

import java.awt.*;

public class DirectionUpState implements DirectionState {

    @Override
    public void shoot(Weapon weapon, Point position) {
        weapon.shoot(new ShootUp(), position);
    }

    @Override
    public void Draw(GraphicsContext gc, Point pt, int widthCell, int heightCell, MovingObject heroOrMonster) {
//        heroOrMonster.getUpSprite().reset();
//        int i = (int)heroOrMonster.getPosition().getY()+heightCell;
//        while (i > heroOrMonster.getPosition().getY()) {
//            gc.clearRect(heroOrMonster.getPosition().getX(), i, widthCell, heightCell);
//            i--;
//            heroOrMonster.getUpSprite().drawNextSprite(gc, widthCell, heightCell, (int)heroOrMonster.getPosition().getX(), i);
//        }

        gc.clearRect(pt.getX() - (widthCell / 2),
                pt.getY() + 1 - (heightCell / 2),
                widthCell, heightCell);
        heroOrMonster.getUpSprite().drawNextSprite(
                gc, widthCell, heightCell,
                (int) pt.getX(),
                (int) pt.getY());

    }

    @Override
    public void DrawReleased(GraphicsContext gc, Point pt, int widthCell, int heightCell, MovingObject heroOrMonster) {
        gc.clearRect(pt.getX() - 1 - (widthCell / 2),
                pt.getY() - (heightCell / 2),
                widthCell, heightCell);
        heroOrMonster.getUpSprite().drawNextSprite(gc, widthCell, heightCell,
                (int) pt.getX(),
                (int) pt.getY());

    }

}
