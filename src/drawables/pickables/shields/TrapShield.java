package drawables.pickables.shields;

import drawables.characters.Hero;

public class TrapShield extends StandardShield {
	public TrapShield() {
		super();
	}
	@Override
	public void protectedFrom(Hero hero) {
		hero.protectedFromTrap();
		destroy();
	}



}
