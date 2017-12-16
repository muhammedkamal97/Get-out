package drawables.pickables.shields;

import drawables.characters.Hero;

public class FlameShield extends StandardShield{
	public FlameShield() {
		super();
	}

	@Override
	public void protectedFrom(Hero hero) {
		hero.protectedFromFlame();
		destroy();
	}

	

	
	

}
