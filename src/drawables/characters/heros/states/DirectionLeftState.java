package drawables.characters.heros.states;

import drawables.characters.Hero;
import drawables.pickables.Weapon;
import drawables.pickables.weapons.bullets.bulletDirectionStates.ShootDown;
import drawables.pickables.weapons.bullets.bulletDirectionStates.ShootLeft;
import javafx.scene.canvas.GraphicsContext;
import maze.Maze;

import java.awt.*;

public class DirectionLeftState implements DirectionState {
    @Override
    public void shoot(Weapon weapon, Point position, Maze maze) {
        weapon.shoot(new ShootLeft(),position, maze);
    }

    @Override
    public void Draw(GraphicsContext gc, Point pt, int widthCell, int heightCell, Hero hero) {
        hero.getDownSprite().reset();
        int i = (int)hero.getPosition().getY()+ widthCell;
        while (i > hero.getPosition().getY()) {
            gc.clearRect(i, hero.getPosition().getY(), widthCell, heightCell);
            i--;
            hero.getDownSprite().drawNextSprite(gc, widthCell, heightCell, i, (int)hero.getPosition().getY());
        }
    }
}
