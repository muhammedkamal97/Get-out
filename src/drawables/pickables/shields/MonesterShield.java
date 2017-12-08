package drawables.pickables.shields;

import drawables.characters.Hero;

public class MonesterShield extends StandardShield {

	@Override
	public void protectedFrom(Hero hero) {
		hero.protectedFromMonester();
	}

}
