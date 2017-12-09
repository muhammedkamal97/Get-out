package drawables.obstacles.traps;

import drawables.characters.Hero;
import drawables.obstacles.Trap;
import javafx.scene.canvas.Canvas;

import java.awt.*;

public class Hole implements Trap {
    private Point position;
    private int damage;

    public void Hole (){
        this.damage = 1000;
    }

    @Override
    public void trap(Hero hero) {
        hero.receiveExplosionDamage(hero.getHealthPoints()+10); //check // suppose to die
    }

    @Override
    public int getDamage() {
        return this.damage;
    }

    @Override
    public void drawOnCanvas(Canvas canvas) {
    //TODO
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
    public void destroy() {
    //TODO
    }
}
