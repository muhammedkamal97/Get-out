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


    public boolean performMove(Drawable objectAtNewPosition, MovingObject object) {
        if (object instanceof Hero) {
            Hero hero = (Hero) object;
            if (objectAtNewPosition instanceof Obstacle) {
                if (objectAtNewPosition instanceof Bomb) {
                    hero.setPosition(objectAtNewPosition.getPosition());
                    ((Bomb) objectAtNewPosition).explode();
                    return true;
                } else {
                    hero.setPosition(objectAtNewPosition.getPosition());
                    ((Trap) objectAtNewPosition).trap(hero);
                    return true;
                }
            } else if (objectAtNewPosition instanceof Monster) {
                ((Monster) objectAtNewPosition).attack(hero);
                return false;
            } else if (objectAtNewPosition instanceof Pickable) {
                hero.setPosition(objectAtNewPosition.getPosition());
                hero.pick((Pickable) objectAtNewPosition);
                return true;
            }
            else {
                hero.setPosition(objectAtNewPosition.getPosition());
                return true;
            }
        } else if (object instanceof Monster){
            Monster monster = (Monster) object;
            if (objectAtNewPosition instanceof Obstacle) {
                if (objectAtNewPosition instanceof Bomb) {
                    monster.setPosition(objectAtNewPosition.getPosition());
                    ((Bomb) objectAtNewPosition).explode();
                    return true;
                } else {
                    monster.setPosition(objectAtNewPosition.getPosition());
                    return true;
                }
            } else if (objectAtNewPosition instanceof Monster) {
                return false;
            } else if (objectAtNewPosition instanceof Pickable) {
                monster.setPosition(objectAtNewPosition.getPosition());
                return true;
            }
        }
        return false;
    }
}
