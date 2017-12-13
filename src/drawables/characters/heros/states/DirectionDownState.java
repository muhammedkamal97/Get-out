package drawables.characters.heros.states;

import drawables.characters.Hero;
import drawables.pickables.Weapon;
import drawables.pickables.weapons.bullets.bulletDirectionStates.ShootDown;
import javafx.scene.canvas.GraphicsContext;
import maze.Maze;

import java.awt.*;

public class DirectionDownState implements DirectionState {
    @Override
    public void shoot(Weapon weapon, Point position) {
        weapon.shoot(new ShootDown(),position);
    }
    //will initialize 4 sprites in each hero and they will have get methods
    //draw function will be called in DirectionState
    @Override
    public void Draw(GraphicsContext gc, Point pt, int widthCell, int heightCell, Hero hero) {
            hero.getDownSprite().reset();
            int i = (int)hero.getPosition().getY()-heightCell;
            while (i < hero.getPosition().getY()) {
                gc.clearRect(hero.getPosition().getX(), i, widthCell, heightCell);
                i++;
                hero.getDownSprite().drawNextSprite(gc, widthCell, heightCell, (int)hero.getPosition().getX(), i);
            }

    }
}

