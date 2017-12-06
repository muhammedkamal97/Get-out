package drawables.characters;

import drawables.ReactorsOnTouch;

public interface Monster extends MovingObject, ReactorsOnTouch {

    public void attack(Hero hero);
    public int getDamage();

}
