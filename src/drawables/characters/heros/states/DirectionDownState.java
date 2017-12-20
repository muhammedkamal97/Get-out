package drawables.characters.heros.states;

import drawables.characters.MovingObject;
import drawables.pickables.Weapon;
import drawables.pickables.weapons.bullets.bulletDirectionStates.ShootDown;
import javafx.scene.canvas.GraphicsContext;

import java.awt.*;

public class DirectionDownState implements DirectionState {

    private static DirectionDownState state = null;
    public static DirectionDownState createDirectionDownState(){
        if(state == null) {

            state = new DirectionDownState();
            return state;
        }
        return state;
    }
    private DirectionDownState(){
        //nothing
    }

    @Override
    public void shoot(Weapon weapon, Point position) {
        weapon.shoot(new ShootDown(), position);
    }

    @Override
    public void Draw(GraphicsContext gc, Point pt, int widthCell, int heightCell, MovingObject heroOrMonster) {
        gc.clearRect(pt.getX(),
                pt.getY() - 20,
                widthCell, heightCell);
        heroOrMonster.getDownSprite().drawNextSprite(
                gc, widthCell, heightCell,
                (int) pt.getX(),
                (int) pt.getY());

    }

    @Override
    public void DrawReleased(GraphicsContext gc, Point pt, int widthCell, int heightCell, MovingObject heroOrMonster) {
        gc.clearRect(pt.getX(),
                pt.getY() - 20,
                widthCell, heightCell);
        heroOrMonster.getDownSprite().drawReleased(
                gc, widthCell, heightCell,
                (int) pt.getX(),
                (int) pt.getY());
    }
}

