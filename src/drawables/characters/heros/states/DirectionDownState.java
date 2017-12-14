package drawables.characters.heros.states;

import drawables.characters.MovingObject;
import drawables.pickables.Weapon;
import drawables.pickables.weapons.bullets.bulletDirectionStates.ShootDown;
import javafx.scene.canvas.GraphicsContext;

import java.awt.*;

public class DirectionDownState implements DirectionState {
    @Override
    public void shoot(Weapon weapon, Point position) {
        weapon.shoot(new ShootDown(),position);
    }
    //will initialize 4 sprites in each hero and they will have get methods
    //draw function will be called in DirectionState
    @Override
    public void Draw(GraphicsContext gc, Point pt, int widthCell, int heightCell, MovingObject heroOrMonster) {
            heroOrMonster.getDownSprite().reset();
            int i = (int)heroOrMonster.getPosition().getY()-heightCell;
            while (i < heroOrMonster.getPosition().getY()) {
                gc.clearRect(heroOrMonster.getPosition().getX(), i, widthCell, heightCell);
                i++;
                heroOrMonster.getDownSprite().drawNextSprite(gc, widthCell, heightCell, (int)heroOrMonster.getPosition().getX(), i);
            }

    }
}

