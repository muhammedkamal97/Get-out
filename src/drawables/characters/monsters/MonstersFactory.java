package drawables.characters.monsters;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import drawables.characters.Monster;
import loaders.ElementLoader;

public class MonstersFactory {
	
	private ArrayList<Class<? extends Monster>> monsters;
	
	public MonstersFactory() {
		
		if(!loadClasses())
			throw new RuntimeException("Failed to load classes");
	}
	
	
	public ArrayList<Class<? extends Monster>> getClasses()
	{
		return this.monsters;
	}
	
	public Monster factoryMethod(String name)
	{
		for(int i = 0 ; i < this.monsters.size() ; i++)
		{
			Class<? extends Monster> tempClass = this.monsters.get(i);
			
			if(tempClass.getName().contains(name))
			{
				try {
					Constructor<?> ctor = tempClass.getConstructor();
					ctor.setAccessible(true);
					return (Monster) ctor.newInstance();
				} catch (NoSuchMethodException | SecurityException | InstantiationException 
						| IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
					throw new RuntimeException("Monster Factory Failure");
				}
			}
		}
		
		throw new RuntimeException("Monster does not exist");
	}
	
	private boolean loadClasses()
	{
		ElementLoader<Monster> loader = new ElementLoader<>();
		try{
			loader.load("src/drawables/characters/monsters");
			this.monsters = (ArrayList<Class<? extends Monster>>) loader.getList();
		} catch (RuntimeException e) {
			return false;
		}
		
		return true;
	}
}
