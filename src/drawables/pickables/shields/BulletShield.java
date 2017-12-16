package drawables.pickables.shields;

import drawables.characters.Hero;

public class BulletShield extends StandardShield{
	public BulletShield() {
		super();
	}

	@Override
	public void protectedFrom(Hero hero) {
		hero.protectedFromBullets();
		destroy();
	}



}
