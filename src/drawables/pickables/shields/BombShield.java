package drawables.pickables.shields;

import drawables.characters.Hero;

public class BombShield extends StandardShield {
	public BombShield() {
		super();
	}

	@Override
	public void protectedFrom(Hero hero) {
		hero.protectedFromBomb();
		destroy();
	}
	

}
