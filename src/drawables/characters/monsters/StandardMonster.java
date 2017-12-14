package drawables.characters.monsters;

import View.Graphics.ImagesMaps.CharactersMap;
import View.Graphics.Sprite.ImageSprite;
import View.Graphics.Sprite.MySprite;
import drawables.characters.Hero;
import drawables.characters.Monster;
import drawables.characters.commands.Command;
import drawables.characters.heros.states.DirectionState;
import drawables.characters.monsters.Behaviors.ShootingBehavior;
import drawables.pickables.weapons.bullets.Bullet;
import javafx.scene.canvas.GraphicsContext;
import maze.Maze;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class StandardMonster implements Monster {

    private int damage;
    private int healthPoints;
    private ShootingBehavior shootingBehavior;
    private Point position;

    private DirectionState directionState;

    private MySprite downSprite = new MySprite();
    private MySprite upSprite = new MySprite();
    private MySprite rightSprite = new MySprite();
    private MySprite leftSprite = new MySprite();

    @Override
    public void attack(Hero hero) {
        hero.attackedByMonster(this);
    }

    @Override
    public int getDamage() {
        return damage;
    }


    @Override
    public boolean move(Command moveCommand, Maze maze) {
        return moveCommand.execute(this, maze);
    }

    @Override
    public int getHealthPoints() {
        return healthPoints;
    }

    @Override
    public void setDirectionState(DirectionState state) {
        directionState = state;
    }

    @Override
    public DirectionState getDirectionState() {
        return this.directionState;
    }

    @Override
    public void takeBullet(Bullet bullet) {
        takeDamage(bullet.getDamage());
    }

    @Override
    public void receiveExplosionDamage(int damage) {
        takeDamage(damage);
    }

    @Override
    public void takeDamage(int damage) {
        if (damage >= healthPoints) {
            healthPoints = 0;
            //dead notify canvas
        } else {
            healthPoints -= damage;
        }
    }

    @Override
    public void shoot() {
        shootingBehavior.shoot(position, directionState);
    }

    @Override
    public Point getPosition() {
        return this.position;
    }

    @Override
    public void setPosition(Point position) {
        this.position = position;
    }

    @Override
    public void drawOnCanvas(GraphicsContext gc, Point pt, int widthCell, int heightCell) {
        getDirectionState().Draw(gc, pt, widthCell, heightCell, this);
    }

    @Override
    public MySprite getDownSprite() {
        return downSprite;
    }

    @Override
    public MySprite getUpSprite() {
        return upSprite;
    }

    @Override
    public MySprite getRightSprite() {
        return rightSprite;
    }

    @Override
    public MySprite getLeftSprite() {
        return leftSprite;
    }

    private void setDownSprite(MySprite sprite) {
        this.downSprite = sprite;
    }

    private void setUpSprite(MySprite sprite) {
        this.upSprite = sprite;
    }

    private void setRightSprite(MySprite sprite) {
        this.rightSprite = sprite;
    }

    private void setLeftSprite(MySprite sprite) {
        this.leftSprite = sprite;
    }

    protected void spriteSetters() {
        setHealthPoints();
        CharactersMap map = CharactersMap.getInstance();
        ImageSprite sprite = map.getImageSprite("Flash");
        setDownSprite(constructSprite(sprite.getImageDown(), sprite));
        setUpSprite(constructSprite(sprite.getImageUp(), sprite));
        setRightSprite(constructSprite(sprite.getImageRight(), sprite));
        setLeftSprite(constructSprite(sprite.getImageLeft(), sprite));
    }

    private MySprite constructSprite(BufferedImage img, ImageSprite imgSprite) {
        MySprite sprite = new MySprite();
        sprite.SpriteSheetBuffer(img.getWidth(),
                img.getHeight(),
                imgSprite.getFramesInRow(),
                imgSprite.getFramesInColumn(),
                img);
        return sprite;
    }

    protected void setHealthPoints() {
        healthPoints = getMonsterHealthPoints();
    }

    protected void setShootingBehavior() {
        shootingBehavior = getShootingBehavior();
    }

    protected void setDamage() {
        damage = getMonsterDamage();
    }

    protected abstract int getMonsterDamage();

    protected abstract ShootingBehavior getShootingBehavior();

    protected abstract int getMonsterHealthPoints();
}
