package drawables.characters.heros.states;

import drawables.characters.Hero;
import drawables.pickables.Weapon;
import drawables.pickables.weapons.bullets.bulletDirectionStates.ShootDown;
import drawables.pickables.weapons.bullets.bulletDirectionStates.ShootUp;
import javafx.scene.canvas.GraphicsContext;
import maze.Maze;

import java.awt.*;

public class DirectionUpState implements DirectionState{

    @Override
    public void shoot(Weapon weapon, Point position, Maze maze) {
        weapon.shoot(new ShootUp(),position, maze);
    }

    @Override
    public void Draw(GraphicsContext gc, Point pt, int widthCell, int heightCell, Hero hero) {
        hero.getUpSprite().reset();
        int i = (int)hero.getPosition().getY()+heightCell;
        while (i > hero.getPosition().getY()) {
            gc.clearRect(hero.getPosition().getX(), i, widthCell, heightCell);
            i--;
            hero.getUpSprite().drawNextSprite(gc, widthCell, heightCell, hero.getPosition().getX(), i);
        }

    }

}
