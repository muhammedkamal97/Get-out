package gameLevels;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import drawables.obstacles.Wall;
import loaders.ElementLoader;

public class LevelsFactory {

    private ArrayList<Class<? extends LevelProperties>> levels;

    public LevelsFactory() {

        if(!loadClasses())
            throw new RuntimeException("Failed to load classes");
    }


    public ArrayList<Class<? extends LevelProperties>> getClasses()
    {
        return this.levels;
    }

    public LevelProperties factoryMethod(String name)
    {
        for(int i = 0 ; i < this.levels.size() ; i++)
        {
            Class<? extends LevelProperties> tempClass = this.levels.get(i);

            if(tempClass.getName().contains(name))
            {
                try {
                    Constructor<?> ctor = tempClass.getConstructor();
                    ctor.setAccessible(true);
                    return (LevelProperties) ctor.newInstance();
                } catch (NoSuchMethodException | SecurityException | InstantiationException
                        | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
                    throw new RuntimeException("Level Factory Failure");
                }
            }
        }

        throw new RuntimeException("Level does not exist");
    }

    private boolean loadClasses()
    {
        ElementLoader<LevelProperties> loader = new ElementLoader<>();
        try{
            loader.load("src/gameLevels/Levels"); // need to have a separate  path
            this.levels = (ArrayList<Class<? extends LevelProperties>>) loader.getList();
        } catch (RuntimeException e) {
            return false;
        }

        return true;
    }
}
