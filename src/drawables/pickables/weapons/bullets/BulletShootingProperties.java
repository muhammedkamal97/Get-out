package drawables.pickables.weapons.bullets;

import drawables.pickables.Weapon;
import drawables.pickables.weapons.bullets.bulletDirectionStates.ShootDown;
import drawables.pickables.weapons.bullets.bulletDirectionStates.ShootRight;
import drawables.pickables.weapons.bullets.bulletDirectionStates.ShootUp;
import drawables.pickables.weapons.bullets.bulletDirectionStates.ShootingDirectionState;
import maze.Maze;

import java.awt.*;

/**
 * Created by Mahmoud on 12/18/2017.
 */
public class BulletShootingProperties {

    private Point position;
    private Bullet bullet;
    private ShootingDirectionState directionState;
    private Weapon weapon;
    private int displacementX;
    private int displacementY;
    private int factorX;
    private int factorY;

    public BulletShootingProperties(Point position, Bullet bullet, Weapon weapon, ShootingDirectionState directionState)
    {
        this.position = position;
        this.bullet = bullet;
        this.directionState = directionState;
        this.weapon = weapon;
        setFactors();
        setDisplacement();
    }

    public void increment()
    {
        this.displacementX += this.factorX;
        this.displacementY += this.factorY;
    }

    public Point getPastPosition(){return new Point(this.displacementX - this.factorX, this.displacementY - this.factorY);}
    public Point getCurrentPosition()
    {
        return new Point(this.displacementX, this.displacementY);
    }

    public Bullet getBullet() {return this.bullet;}

    public Weapon getWeapon() {return this.weapon;}
    private void setFactors()
    {
        if(this.directionState instanceof ShootUp)
        {
            this.factorX = 0;
            this.factorY = -1;
        }
        else if(this.directionState instanceof ShootDown)
        {
            this.factorX = 0;
            this.factorY = 1;
        }
        else if(this.directionState instanceof ShootRight)
        {
            this.factorX = 1;
            this.factorY = 0;
        }
        else
        {
            this.factorX = -1;
            this.factorY = 0;
        }
    }

    private void setDisplacement() {
        this.displacementX = this.position.x;
        this.displacementY = this.position.y;
    }


}
