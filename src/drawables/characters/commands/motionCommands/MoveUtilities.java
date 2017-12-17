package drawables.characters.commands.motionCommands;

import drawables.Drawable;
import drawables.characters.Hero;
import drawables.characters.Monster;
import drawables.characters.MovingObject;
import drawables.obstacles.*;
import drawables.pickables.Pickable;

import java.awt.*;

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
                }  else if (objectAtNewPosition instanceof Gate) {
                    if (hero.hasKey()) {
                        throw new RuntimeException("you win");
                    } else {
                        System.out.println("u need a key");
                    }
                    return true;
                }else {
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
            Point temp = new Point(monster.getPosition().x,monster.getPosition().y);
            monster.setPastPosition(temp);
            if (objectAtNewPosition instanceof Obstacle) {
                if (objectAtNewPosition instanceof Bomb) {
                    monster.setPosition(objectAtNewPosition.getPosition());
                    return true;
                } else if (objectAtNewPosition instanceof Gate){
                    return false;
                } else {
                    monster.setPosition(objectAtNewPosition.getPosition());
                    return true;
                }
            } else if (objectAtNewPosition instanceof Monster) {
                return false;
            } else if (objectAtNewPosition instanceof Pickable) {
                monster.setPosition(objectAtNewPosition.getPosition());
                return true;
            } else if (objectAtNewPosition instanceof Hero) {
                monster.attack((Hero)objectAtNewPosition);
                return true;
            }  else {
                monster.setPosition(objectAtNewPosition.getPosition());
                return true;
            }
        }
        return false;
    }
}
