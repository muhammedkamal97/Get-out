package drawables.characters.commands.motionCommands;

import drawables.Drawable;
import drawables.characters.Hero;
import drawables.characters.Monster;
import drawables.obstacles.Bomb;
import drawables.obstacles.Obstacle;
import drawables.obstacles.Trap;
import drawables.obstacles.Wall;
import drawables.pickables.Pickable;

public class MoveUtilities {

    public boolean isAValidMove(Drawable objectAtNewPosition){
        return !(objectAtNewPosition instanceof Obstacle && objectAtNewPosition instanceof Wall);
    }

    public void performMove(Drawable objectAtNewPosition , Hero hero) {

        if(objectAtNewPosition instanceof Obstacle){
            if(objectAtNewPosition instanceof Bomb){
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
