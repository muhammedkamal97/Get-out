package drawables.characters.commands.motionCommands;

import drawables.Drawable;
import drawables.characters.Hero;
import drawables.characters.Monster;
import drawables.characters.MovingObject;
import drawables.obstacles.Bomb;
import drawables.obstacles.Obstacle;
import drawables.obstacles.Trap;
import drawables.obstacles.Wall;
import drawables.pickables.Pickable;

public class MoveUtilities {

    public boolean isAValidMove(Drawable objectAtNewPosition) {
        return !(objectAtNewPosition instanceof Obstacle && objectAtNewPosition instanceof Wall);
    }

    public void performMove(Drawable objectAtNewPosition, MovingObject object) {
        if (object instanceof Hero) {
            Hero hero = (Hero) object;
            if (objectAtNewPosition instanceof Obstacle) {
                if (objectAtNewPosition instanceof Bomb) {
                    ((Bomb) objectAtNewPosition).explode();
                } else {
                    hero.setPosition(objectAtNewPosition.getPosition());
                    ((Trap) objectAtNewPosition).trap(hero);
                }
            } else if (objectAtNewPosition instanceof Monster) {
                ((Monster) objectAtNewPosition).attack(hero);
            } else if (objectAtNewPosition instanceof Pickable) {
                hero.pick((Pickable) objectAtNewPosition);
            }
        }
    }
}
