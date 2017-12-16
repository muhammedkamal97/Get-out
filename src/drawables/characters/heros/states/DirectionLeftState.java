package drawables.characters.heros.states;

import drawables.characters.MovingObject;
import drawables.pickables.Weapon;
import drawables.pickables.weapons.bullets.bulletDirectionStates.ShootLeft;
import javafx.animation.TranslateTransitionBuilder;
import javafx.scene.canvas.GraphicsContext;
import javafx.util.Duration;

import java.awt.*;
import java.awt.event.ActionEvent;

public class DirectionLeftState implements DirectionState {
    @Override
    public void shoot(Weapon weapon, Point position) {
        weapon.shoot(new ShootLeft(),position);
    }

    @Override
    public void Draw(GraphicsContext gc, Point pt, int widthCell, int heightCell, MovingObject heroOrMonster) {
//        heroOrMonster.getLeftSprite().reset();
//        int i = (int)heroOrMonster.getPosition().getY()+ widthCell;
//        while (i > heroOrMonster.getPosition().getY()) {
//            gc.clearRect(i, heroOrMonster.getPosition().getY(), widthCell, heightCell);
//            i--;
//            heroOrMonster.getLeftSprite().drawNextSprite(gc, widthCell, heightCell, i, (int)heroOrMonster.getPosition().getY());
//        }
        gc.clearRect(pt.getX()+1-(widthCell / 2),
                pt.getY()-(heightCell / 2),
                widthCell, heightCell);
        heroOrMonster.getLeftSprite().drawNextSprite(
                gc, widthCell, heightCell,
                (int)pt.getX(),
                (int)pt.getY());

    }
    @Override
    public void DrawReleased(GraphicsContext gc,Point pt,  int widthCell, int heightCell, MovingObject heroOrMonster) {
        gc.clearRect(pt.getX()+1-(widthCell / 2),
                pt.getY()-(heightCell / 2),
                widthCell, heightCell);
        heroOrMonster.getLeftSprite().drawReleased(
                gc, widthCell, heightCell,
                (int)pt.getX(),
                (int)pt.getY());
    }

}
