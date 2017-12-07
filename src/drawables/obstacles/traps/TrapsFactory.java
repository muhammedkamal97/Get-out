package drawables.obstacles.traps;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import drawables.obstacles.Trap;
import loaders.ElementLoader;

public class TrapsFactory {
	
	private ArrayList<Class<? extends Trap>> traps;
	
	public TrapsFactory() {
		
		if(!loadClasses())
			throw new RuntimeException("Failed to load classes");
	}
	
	
	public ArrayList<Class<? extends Trap>> getClasses()
	{
		return this.traps;
	}
	
	public Trap factoryMethod(String name)
	{
		for(int i = 0 ; i < this.traps.size() ; i++)
		{
			Class<? extends Trap> tempClass = this.traps.get(i);
			
			if(tempClass.getName().contains(name))
			{
				try {
					Constructor<?> ctor = tempClass.getConstructor();
					return (Trap) ctor.newInstance();
				} catch (NoSuchMethodException | SecurityException | InstantiationException 
						| IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
					throw new RuntimeException("Trap Factory Failure");
				}
			}
		}
		
		throw new RuntimeException("Trap does not exist");
	}
	
	private boolean loadClasses()
	{
		ElementLoader<Trap> loader = new ElementLoader<>();
		try{
			loader.load("src/drawables/obstacles/traps");
			this.traps = (ArrayList<Class<? extends Trap>>) loader.getList();
		} catch (RuntimeException e) {
			return false;
		}
		
		return true;
	}
}
