package loaders;

import org.apache.log4j.Logger;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;


public class ElementLoader<T> {

	private ArrayList<Class<? extends T>> elements;
	public ElementLoader() {
		this.elements = new ArrayList<>();
	}
	private Logger logger = Logger.getLogger(ElementLoader.class);


	public void load(String packageName) {
		
		File currentPackage = new File(packageName);
		
		if(!currentPackage.exists())
		{
			logger.error("Specified package does not exist");
			throw new RuntimeException();
		}
		
		File[] packageFiles = currentPackage.listFiles();
		
		
		for(int i = 0 ; i < packageFiles.length ; i++)
		{	
			if(!packageFiles[i].getName().contains(".java"))
				continue;
			
			String className = packageFiles[i].getPath();
			className = className.replaceAll("/","\\\\");
			className = className.replaceAll("\\\\", ".").substring(4, className.length() - 5);
			
			try {
				Class<?> currentClass = Class.forName(className);
				if(checkCondition(currentClass))
					this.elements.add((Class<? extends T>) currentClass);
				
			} catch (ClassNotFoundException e) {
				logger.error("Class not found in package");
				throw new RuntimeException("please check the package");
			}
		}	
	}

	public ArrayList<?> getList() {
		return this.elements;
	}

	private boolean checkCondition(Class<?> tempClass)
	{
		if(tempClass.getName().contains("Factory") || tempClass.isInterface())
			return false;
		
		try {
			tempClass.getConstructor().newInstance();
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
				| NoSuchMethodException | SecurityException e) {
			return false;
		}
		return true;
	}
}
