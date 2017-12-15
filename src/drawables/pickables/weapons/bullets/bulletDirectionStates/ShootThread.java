package drawables.pickables.weapons.bullets.bulletDirectionStates;

import drawables.Drawable;
import drawables.characters.MovingObject;
import drawables.pickables.weapons.bullets.Bullet;
import drawables.roads.Road;
import maze.Maze;

import java.awt.*;

/**
 * Created by Mahmoud on 12/13/2017.
 */
public class ShootThread extends Thread{


    private Point position;
    private Bullet bullet;
    private Maze maze;
    private int displacementFactorX;
    private int displacementFactorY;

    public void setPosition(Point position) {
        this.position = position;
    }

    public void setBullet(Bullet bullet) {
        this.bullet = bullet;
    }

    public  void setMaze(Maze maze) {
        this.maze = maze;
    }

    public void setDisplacement(int displacementFactorX, int displacementFactorY)
    {
        this.displacementFactorX = displacementFactorX;
        this.displacementFactorY = displacementFactorY;
    }

    @Override
    public void run()
    {
        int displacement = 0;
        while(true)
        {
            displacement++;
            int displacementX = this.displacementFactorX * displacement;
            int displacementY = this.displacementFactorY * displacement;

            Point newPosition = new Point((int)this.position.getX() + displacementX,
                                          (int)this.position.getY() + displacementY);

            Drawable entityInWay = this.maze.getItemInPosition(newPosition);

            if(!(entityInWay instanceof Road))
            {
                entityInWay.takeDamage(this.bullet.getDamage());
                break;
            }
        }
    }
}


