package drawables.characters;

import drawables.pickables.Weapon;

public interface IWeaponIterator {
	public boolean hasNext();
	public Weapon nextweapon();
	public Weapon previousweapon();
	public Weapon getWeapon();
}
