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


    private static DirectionLeftState state = null;
    public static DirectionLeftState createDirectionLeftState(){
        if(state == null) {

            state = new DirectionLeftState();
            return state;
        }
        return state;
    }
    private DirectionLeftState(){
        //nothing
    }

    @Override
    public void shoot(Weapon weapon, Point position) {
        weapon.shoot(new ShootLeft(),position);
    }

    @Override
    public void Draw(GraphicsContext gc, Point pt, int widthCell, int heightCell, MovingObject heroOrMonster) {
        gc.clearRect(pt.getX()+20,
                pt.getY(),
                widthCell, heightCell);
        heroOrMonster.getLeftSprite().drawNextSprite(
                gc, widthCell, heightCell,
                (int)pt.getX(),
                (int)pt.getY());

    }
    @Override
    public void DrawReleased(GraphicsContext gc,Point pt,  int widthCell, int heightCell, MovingObject heroOrMonster) {
        gc.clearRect(pt.getX()+20,
                pt.getY(),
                widthCell, heightCell);
        heroOrMonster.getLeftSprite().drawReleased(
                gc, widthCell, heightCell,
                (int)pt.getX(),
                (int)pt.getY());
    }

}
