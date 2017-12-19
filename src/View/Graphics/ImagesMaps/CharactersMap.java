package View.Graphics.ImagesMaps;

import View.Graphics.Sprite.ImageSprite;
import View.Graphics.Sprite.SpriteFactory;

import java.util.HashMap;
import java.util.Map;

public class CharactersMap {

    private static volatile CharactersMap instance;
    private static Object mutex = new Object();

    private Map<String, ImageSprite> images;

    private CharactersMap() {
        images = new HashMap<>();
    }

    public static CharactersMap getInstance() {
        CharactersMap result = instance;
        if (result == null) {
            synchronized (mutex) {
                result = instance;
                if (result == null)
                    instance = result = new CharactersMap();
            }
        }
        return result;
    }

    public ImageSprite getImageSprite(String key) {
        ImageSprite img = images.get(key);
        if (img != null) {
            return img;
        } else {
            SpriteFactory factory = new SpriteFactory();
            img = factory.factoryMethod(key);
            images.put(key, img);
            return img;
        }
    }
}
