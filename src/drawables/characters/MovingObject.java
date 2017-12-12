package drawables.characters;


import drawables.Drawable;
import drawables.characters.commands.Command;
import drawables.characters.heros.states.DirectionState;
import drawables.pickables.weapons.bullets.Bullet;
import maze.Maze;

public interface MovingObject extends Drawable{

    public boolean move(Command moveCommand , Maze maze);
    public int getHealthPoints();
    public void setDirectionState(DirectionState state);
    public void takeBullet(Bullet bullet);
    public void receiveExplosionDamage(int damage);
}
