package drawables.obstacles.traps;

import drawables.characters.Hero;
import drawables.obstacles.Trap;
import drawables.roads.Road;
import javafx.scene.canvas.Canvas;

import java.awt.*;
import java.util.Random;

public class RandomTeleport extends StandardTrap implements Trap {


    public void RandomTeleport() {
        this.damage = 0;
    }

    @Override
    public void trap(Hero hero) {
        Random ran = new Random();
        int x = ran.nextInt(30); //handle maze size
        int y = ran.nextInt(30);
        while (!(maze.getItemInPosition(new Point(x, y)) instanceof Road)) {
            x = ran.nextInt(30);
            y = ran.nextInt(30);
        }
        hero.setPosition(new Point(x, y));
        //change maze since hero's position is changed
    }

    @Override
    public void drawOnCanvas(Canvas canvas) {
        //TODO
    }

    @Override
    public void destroy() {
        return;
    }
}
