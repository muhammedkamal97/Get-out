package drawables.characters.heros;

import drawables.characters.Hero;
import drawables.characters.Monster;
import drawables.characters.commands.Command;
import drawables.obstacles.Trap;
import drawables.pickables.Pickable;
import javafx.scene.canvas.Canvas;

import java.awt.*;

public class test implements Hero {
    @Override
    public void trapHero(Trap trap) {

    }

    @Override
    public void attackedByMonster(Monster monster) {

    }

    @Override
    public void shoot() {

    }

    @Override
    public void drawOnCanvas(Canvas canvas) {

    }

    @Override
    public Point getPosition() {
        return null;
    }

    @Override
    public void setPosition(Point position) {

    }

    @Override
    public void pick(Pickable item) {

    }

    @Override
    public void move(Command moveCommand) {

    }

    @Override
    public int getHealthPoints() {
        return 0;
    }

    @Override
    public void setDirection(int direction) {

    }

    @Override
    public int getDirection() {
        return 0;
    }
}
