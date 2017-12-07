package drawables.pickables.shields;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import drawables.pickables.Shield;
import loaders.ElementLoader;

public class ShieldsFactory {
	
	private ArrayList<Class<? extends Shield>> shields;
	
	public ShieldsFactory() {
		
		if(!loadClasses())
			throw new RuntimeException("Failed to load classes");
	}
	
	
	public ArrayList<Class<? extends Shield>> getClasses()
	{
		return this.shields;
	}
	
	public Shield factoryMethod(String name)
	{
		for(int i = 0 ; i < this.shields.size() ; i++)
		{
			Class<? extends Shield> tempClass = this.shields.get(i);
			
			if(tempClass.getName().contains(name))
			{
				try {
					Constructor<?> ctor = tempClass.getConstructor();
					ctor.setAccessible(true);
					return (Shield) ctor.newInstance();
				} catch (NoSuchMethodException | SecurityException | InstantiationException 
						| IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
					throw new RuntimeException("Shield Factory Failure");
				}
			}
		}
		
		throw new RuntimeException("Shield does not exist");
	}
	
	private boolean loadClasses()
	{
		ElementLoader<Shield> loader = new ElementLoader<>();
		try{
			loader.load("src/drawables/pickables/shields");
			this.shields = (ArrayList<Class<? extends Shield>>) loader.getList();
		} catch (RuntimeException e) {
			return false;
		}
		
		return true;
	}
}
