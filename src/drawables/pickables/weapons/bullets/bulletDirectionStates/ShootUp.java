package drawables.pickables.weapons.bullets.bulletDirectionStates;

import drawables.Drawable;
import drawables.characters.MovingObject;
import drawables.pickables.weapons.bullets.Bullet;
import drawables.roads.Road;
import maze.Maze;

import java.awt.*;

public class ShootUp extends Thread implements ShootingDirectionState {

    private Point position;
    private Bullet bullet;
    private Maze maze;
    @Override
    public void startMotion(Point position, Bullet bullet, Maze maze) {

        this.position = position;
        this.bullet = bullet;
        this.maze = maze;

        Thread.currentThread().start();
    }

    @Override
    public void run()
    {
        int displacement = 0;
        while(true)
        {
            displacement++;
            Point newPosition = new Point((int)this.position.getX(), (int)this.position.getY() - displacement);
            Drawable entityInWay = this.maze.getItemInPosition(newPosition);

            if(!(entityInWay instanceof Road))
            {
                if(entityInWay instanceof MovingObject)
                {
                    ((MovingObject)entityInWay).takeBullet(this.bullet);
                    break;
                }
            }
        }
    }
}
