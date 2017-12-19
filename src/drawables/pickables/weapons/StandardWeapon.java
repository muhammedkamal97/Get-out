package drawables.pickables.weapons;

import View.Graphics.ImagesMaps.MazeMap;
import drawables.characters.Hero;
import drawables.pickables.Weapon;
import drawables.pickables.weapons.bullets.Bullet;
import drawables.pickables.weapons.bullets.BulletShootingProperties;
import drawables.pickables.weapons.bullets.bulletDirectionStates.ShootThread;
import drawables.pickables.weapons.bullets.bulletDirectionStates.ShootingDirectionState;
import javafx.scene.canvas.GraphicsContext;
import maze.Maze;
import observer.BulletMotionObserver;

import java.awt.*;
import java.util.ArrayList;

public abstract class StandardWeapon implements Weapon {


    private Point position;
    private int ammo;
    private int maxMagazineCapacity;
    private Bullet weaponBullet;
    private Bullet shotBullet;
    private ShootThread bulletThread;
    protected Maze maze;
    private ArrayList<BulletMotionObserver> observers = new ArrayList<>();

    @Override
    public void shoot(ShootingDirectionState state, Point initialPosition) {

        if (ammo != 0) {
            shotBullet = (Bullet) weaponBullet.cloneBullet();
            shotBullet.startMotionAfterShooting(initialPosition, state, this);
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
    public void setMaze(Maze maze) {
        this.maze = maze;
    }

    @Override
    public void initiateBulletThread(BulletShootingProperties shootingProperties) {

        if (this.bulletThread != null) {
            if (this.bulletThread.isAlive()) {
                this.bulletThread.addBullet(shootingProperties);
                return;
            }
        }

        this.bulletThread = new ShootThread();
        this.bulletThread.setMaze(this.maze);
        this.bulletThread.setDaemon(true);
        this.bulletThread.addBullet(shootingProperties);
        this.bulletThread.start();


    }

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

    @Override
    public void registerBulletMotionObserver(BulletMotionObserver observer) {
        observers.add(observer);
    }

    @Override
    public void notifyBulletMotionObserver(Point pastPosition, Point currenPosition,boolean destroyed) {
        for (int i = 0; i < this.observers.size(); i++)
            this.observers.get(i).updateBulletMotionObserver(pastPosition, currenPosition,destroyed);
    }

    protected void setBulletType() {
        weaponBullet = getBulletType();
    }

    protected void setMaxMagazineCapacity() {
        maxMagazineCapacity = getMaxMagazineCapacity();
    }

    protected abstract int getMaxMagazineCapacity();

    protected abstract Bullet getBulletType();

}
