package drawables.pickables.weapons;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import drawables.pickables.Weapon;
import loaders.ElementLoader;

public class WeaponsFactory {
	
	private ArrayList<Class<? extends Weapon>> weapons;
	
	public WeaponsFactory() {
		
		if(!loadClasses())
			throw new RuntimeException("Failed to load classes");
	}
	
	
	public ArrayList<Class<? extends Weapon>> getClasses()
	{
		return this.weapons;
	}
	
	public Weapon factoryMethod(String name)
	{
		for(int i = 0 ; i < this.weapons.size() ; i++)
		{
			Class<? extends Weapon> tempClass = this.weapons.get(i);
			
			if(tempClass.getName().contains(name))
			{
				try {
					Constructor<?> ctor = tempClass.getConstructor();
					ctor.setAccessible(true);
					return (Weapon) ctor.newInstance();
				} catch (NoSuchMethodException | SecurityException | InstantiationException 
						| IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
					throw new RuntimeException("Weapon Factory Failure");
				}
			}
		}
		
		throw new RuntimeException("Weapon does not exist");
	}
	
	private boolean loadClasses()
	{
		ElementLoader<Weapon> loader = new ElementLoader<>();
		try{
			loader.load("src/drawables/pickables/weapons");
			this.weapons = (ArrayList<Class<? extends Weapon>>) loader.getList();
		} catch (RuntimeException e) {
			return false;
		}
		
		return true;
	}
}
