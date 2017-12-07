package drawables.pickables.gifts;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import drawables.pickables.Gift;
import loaders.ElementLoader;

public class GiftsFactory {
	
	private ArrayList<Class<? extends Gift>> gifts;
	
	public GiftsFactory() {
		
		if(!loadClasses())
			throw new RuntimeException("Failed to load classes");
	}
	
	
	public ArrayList<Class<? extends Gift>> getClasses()
	{
		return this.gifts;
	}
	
	public Gift factoryMethod(String name)
	{
		for(int i = 0 ; i < this.gifts.size() ; i++)
		{
			Class<? extends Gift> tempClass = this.gifts.get(i);
			
			if(tempClass.getName().contains(name))
			{
				try {
					Constructor<?> ctor = tempClass.getConstructor();
					ctor.setAccessible(true);
					return (Gift) ctor.newInstance();
				} catch (NoSuchMethodException | SecurityException | InstantiationException 
						| IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
					throw new RuntimeException("Gift Factory Failure");
				}
			}
		}
		
		throw new RuntimeException("Gift does not exist");
	}
	
	private boolean loadClasses()
	{
		ElementLoader<Gift> loader = new ElementLoader<>();
		try{
			loader.load("src/drawables/pickables/gifts");
			this.gifts = (ArrayList<Class<? extends Gift>>) loader.getList();
		} catch (RuntimeException e) {
			return false;
		}
		
		return true;
	}
}
