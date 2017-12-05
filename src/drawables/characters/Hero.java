package drawables.characters;

import drawables.obstacles.Trap;

public interface Hero extends Handler,MovingObject{

    public void trapHero(Trap trap);
    public void attackedByMonster(Monster monster);
    public void shoot();

}
