package drawables.characters;

public interface Monster extends MovingObject {

    public void attack(Hero hero);
    public int getDamage();
    public void shoot();

}
