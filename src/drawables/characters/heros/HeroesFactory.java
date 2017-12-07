package drawables.characters.heros;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import drawables.characters.Hero;
import loaders.ElementLoader;

public class HeroesFactory {
	
	private ArrayList<Class<? extends Hero>> heros;
	
	public HeroesFactory() {
		
		if(!loadClasses())
			throw new RuntimeException("Failed to load classes");
	}
	
	
	public ArrayList<Class<? extends Hero>> getClasses()
	{
		return this.heros;
	}
	
	public Hero factoryMethod(String name)
	{
		for(int i = 0 ; i < this.heros.size() ; i++)
		{
			Class<? extends Hero> tempClass = this.heros.get(i);
			
			if(tempClass.getName().contains(name))
			{
				try {
					Constructor<?> ctor = tempClass.getConstructor();
					ctor.setAccessible(true);
					return (Hero) ctor.newInstance();
				} catch (NoSuchMethodException | SecurityException | InstantiationException 
						| IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
					throw new RuntimeException("Hero Factory Failure");
				}
			}
		}
		
		throw new RuntimeException("Hero does not exist");
	}
	
	private boolean loadClasses()
	{
		ElementLoader<Hero> loader = new ElementLoader<>();
		try{
			loader.load("src/drawables/characters/heros");
			this.heros = (ArrayList<Class<? extends Hero>>) loader.getList();
		} catch (RuntimeException e) {
			return false;
		}
		
		return true;
	}
}
