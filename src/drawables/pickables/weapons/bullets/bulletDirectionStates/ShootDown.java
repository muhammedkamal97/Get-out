package drawables.pickables.weapons.bullets.bulletDirectionStates;

import drawables.pickables.weapons.bullets.Bullet;
import maze.Maze;

import java.awt.*;

public class ShootDown extends Thread implements ShootingDirectionState{
    @Override
    public void startMotion(Point position, Bullet bullet, Maze maze) {
        ShootThread thread = new ShootThread();
        thread.setPosition(position);
        thread.setBullet(bullet);
        thread.setMaze(maze);
        thread.setDisplacement(0,1);

        thread.start();
    }
}
