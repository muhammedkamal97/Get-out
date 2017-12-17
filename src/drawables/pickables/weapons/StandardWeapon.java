package drawables.pickables.weapons;

import View.Graphics.ImagesMaps.MazeMap;
import drawables.characters.Hero;
import drawables.pickables.Weapon;
import drawables.pickables.weapons.bullets.Bullet;
import drawables.pickables.weapons.bullets.bulletDirectionStates.ShootingDirectionState;
import javafx.scene.canvas.GraphicsContext;
import maze.Maze;

import java.awt.*;

public abstract class StandardWeapon implements Weapon{


    private Point position;
    private int ammo;
    private int maxMagazineCapacity;
    private Bullet weaponBullet;
    private Bullet shotBullet;
    protected Maze maze;


    @Override
    public void shoot(ShootingDirectionState state, Point initialPosition) {

        if(ammo != 0){
            shotBullet = (Bullet) weaponBullet.cloneBullet();
            shotBullet.startMotionAfterShooting(initialPosition,state,maze);
            ammo--;
        }
    }

    @Override
    public int getNumberOfBullets() {
        return ammo;
    }

    @Override
    public void drawOnCanvas(GraphicsContext gc, Point pt, int widthCell, int heightCell) {
        MazeMap map = MazeMap.getInstance();
        gc.drawImage(map.getBufferedImage(this.getClass().getSimpleName()),
                pt.getX(), pt.getY(),
                widthCell, heightCell);
    }

    @Override
    public void takeDamage(int damage) {
        destroy();
    }

    @Override
    public void destroy() {
        maze.removeWeapon(this);
    }

    @Override
    public void addToHandler(Hero hero) {
        destroy();
        hero.addWeapon(this);
    }

    @Override
    public void setMaze(Maze maze) {this.maze = maze;}

    @Override
    public Point getPosition() {
        return position;
    }

    @Override
    public void setPosition(Point position) {
        this.position = position;
    }

    @Override
    public void reload() {
        ammo = maxMagazineCapacity;
    }

    protected void setBulletType() {
        weaponBullet = getBulletType();
    }

    protected void setMaxMagazineCapacity(){
        maxMagazineCapacity = getMaxMagazineCapacity();
    }

    protected abstract int getMaxMagazineCapacity();
    protected abstract Bullet getBulletType();

}
