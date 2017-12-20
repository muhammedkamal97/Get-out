package drawables.characters.heros.states;

import drawables.characters.MovingObject;
import drawables.pickables.Weapon;
import drawables.pickables.weapons.bullets.bulletDirectionStates.ShootUp;
import javafx.scene.canvas.GraphicsContext;

import java.awt.*;

public class DirectionUpState implements DirectionState {


    private static DirectionUpState state = null;
    public static DirectionUpState createDirectionUpState(){
        if(state == null) {

            state = new DirectionUpState();
            return state;
        }
        return state;
    }
    private DirectionUpState(){
        //nothing
    }

    @Override
    public void shoot(Weapon weapon, Point position) {
        weapon.shoot(new ShootUp(), position);
    }

    @Override
    public void Draw(GraphicsContext gc, Point pt, int widthCell, int heightCell, MovingObject heroOrMonster) {
        gc.clearRect(pt.getX(),
                pt.getY() + 20,
                widthCell, heightCell);
        heroOrMonster.getUpSprite().drawNextSprite(
                gc, widthCell, heightCell,
                (int) pt.getX(),
                (int) pt.getY());

    }

    @Override
    public void DrawReleased(GraphicsContext gc, Point pt, int widthCell, int heightCell, MovingObject heroOrMonster) {
        gc.clearRect(pt.getX(),
                pt.getY() + 20,
                widthCell, heightCell);
        heroOrMonster.getUpSprite().drawNextSprite(gc, widthCell, heightCell,
                (int) pt.getX(),
                (int) pt.getY());

    }

}
