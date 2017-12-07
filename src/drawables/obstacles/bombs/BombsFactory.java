package drawables.obstacles.bombs;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import drawables.obstacles.Bomb;
import loaders.ElementLoader;

public class BombsFactory {
	
	private ArrayList<Class<? extends Bomb>> bombs;
	
	public BombsFactory() {
		
		if(!loadClasses())
			throw new RuntimeException("Failed to load classes");
	}
	
	
	public ArrayList<Class<? extends Bomb>> getClasses()
	{
		return this.bombs;
	}
	
	public Bomb factoryMethod(String name)
	{
		for(int i = 0 ; i < this.bombs.size() ; i++)
		{
			Class<? extends Bomb> tempClass = this.bombs.get(i);
			
			if(tempClass.getName().contains(name))
			{
				try {
					Constructor<?> ctor = tempClass.getConstructor();
					ctor.setAccessible(true);
					return (Bomb) ctor.newInstance();
				} catch (NoSuchMethodException | SecurityException | InstantiationException 
						| IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
					throw new RuntimeException("Bomb Factory Failure");
				}
			}
		}
		
		throw new RuntimeException("Bomb does not exist");
	}
	
	private boolean loadClasses()
	{
		ElementLoader<Bomb> loader = new ElementLoader<>();
		try{
			loader.load("src/drawables/pickables/gifts");
			this.bombs = (ArrayList<Class<? extends Bomb>>) loader.getList();
		} catch (RuntimeException e) {
			return false;
		}
		
		return true;
	}
}
