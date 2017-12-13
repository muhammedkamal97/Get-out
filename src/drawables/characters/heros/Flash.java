package drawables.characters.heros;

import View.Graphics.Sprite.MySprite;
import drawables.characters.commands.Command;
import drawables.characters.heros.states.DirectionState;
import drawables.pickables.Weapon;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import maze.Maze;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Flash extends StandardHero {
    private static final int HEALTH_POINTS = 100;
    private static final int MOTION_DELAY = 10;
    private static final int WEAPONS_LIMIT = 1;

    private MySprite downSprite;
    private MySprite upSprite;
    private MySprite rightSprite;
    private MySprite leftSprite;


    public Flash() {
        setHealthPoints();
        constructSprites();
    }

    private void constructSprites() {
        downSprite = new MySprite();
        upSprite = new MySprite();
        rightSprite = new MySprite();
        leftSprite = new MySprite();

        //get them from Map
//        BufferedImage imgLeft = getHeroesimage
//        BufferedImage imgRight = getHeroesimage
//        BufferedImage imgDown = getHeroesimage
//        BufferedImage imgUp = getHeroesimage

        //need cell width & height
//        downSprite.SpriteSheetBuffer();
//        upSprite.SpriteSheetBuffer();
//        rightSprite.SpriteSheetBuffer();
//        leftSprite.SpriteSheetBuffer();

    }

    @Override
    public boolean move(Command moveCommand, Maze maze) {
        boolean moveResult = super.move(moveCommand, maze);
        try {
            Thread.sleep(MOTION_DELAY);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return moveResult;
    }

    @Override
    protected int getHeroStartingHealth() {
        return HEALTH_POINTS;
    }

    @Override
    public void addWeapon(Weapon weapon) {
        super.addWeapon(weapon);
        if (getNumberOfWeapons() > WEAPONS_LIMIT) {
            removeFirstWeapon();
        }
    }

    @Override
    public void drawOnCanvas(GraphicsContext gc, Point pt, int widthCell, int heightCell) {
        getDirectionState().Draw(gc, pt, widthCell, heightCell, this);
    }


    public MySprite getDownSprite() {
        return downSprite;
    }

    public MySprite getUpSprite() {
        return upSprite;
    }

    public MySprite getRightSprite() {
        return rightSprite;
    }

    public MySprite getLeftSprite() {
        return leftSprite;
    }
}
