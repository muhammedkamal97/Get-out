package drawables.characters;

import maze.Maze;

public interface Monster extends MovingObject {

    public void attack(Hero hero);
    public int getDamage();
    public void shoot(Maze maze);

}
