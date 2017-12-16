package View.Graphics.Sprite;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import View.Graphics.Sprite.ImageSprite;
import loaders.ElementLoader;

public class SpriteFactory {

    private ArrayList<Class<? extends ImageSprite>> sprites;

    public SpriteFactory() {

        if (!loadClasses())
            throw new RuntimeException("Failed to load classes");
    }


    public ArrayList<Class<? extends ImageSprite>> getClasses() {
        return this.sprites;
    }

    public ImageSprite factoryMethod(String name) {
        for (int i = 0; i < this.sprites.size(); i++) {
            Class<? extends ImageSprite> tempClass = this.sprites.get(i);

            if (tempClass.getName().contains(name)) {
                try {
                    Constructor<?> ctor = tempClass.getConstructor();
                    ctor.setAccessible(true);
                    return (ImageSprite) ctor.newInstance();
                } catch (NoSuchMethodException | SecurityException | InstantiationException
                        | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
                    throw new RuntimeException("Sprite Factory Failure");
                }
            }
        }

        throw new RuntimeException("Sprite does not exist");
    }

    private boolean loadClasses() {
        ElementLoader<ImageSprite> loader = new ElementLoader<>();
        try {
            loader.load("src/View/Graphics/Sprite/Types");
            this.sprites = (ArrayList<Class<? extends ImageSprite>>) loader.getList();
        } catch (RuntimeException e) {
            return false;
        }

        return true;
    }
}
