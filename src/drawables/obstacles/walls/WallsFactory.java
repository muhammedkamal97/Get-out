package drawables.obstacles.walls;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import drawables.obstacles.Wall;
import loaders.ElementLoader;

public class WallsFactory {
	
	private ArrayList<Class<? extends Wall>> walls;
	
	public WallsFactory() {
		
		if(!loadClasses())
			throw new RuntimeException("Failed to load classes");
	}
	
	
	public ArrayList<Class<? extends Wall>> getClasses()
	{
		return this.walls;
	}
	
	public Wall factoryMethod(String name)
	{
		for(int i = 0 ; i < this.walls.size() ; i++)
		{
			Class<? extends Wall> tempClass = this.walls.get(i);
			
			if(tempClass.getName().contains(name))
			{
				try {
					Constructor<?> ctor = tempClass.getConstructor();
					ctor.setAccessible(true);
					return (Wall) ctor.newInstance();
				} catch (NoSuchMethodException | SecurityException | InstantiationException 
						| IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
					throw new RuntimeException("Wall Factory Failure");
				}
			}
		}
		
		throw new RuntimeException("Wall does not exist");
	}
	
	private boolean loadClasses()
	{
		ElementLoader<Wall> loader = new ElementLoader<>();
		try{
			loader.load("src/drawables/obstacles/walls");
			this.walls = (ArrayList<Class<? extends Wall>>) loader.getList();
		} catch (RuntimeException e) {
			return false;
		}
		
		return true;
	}
}
