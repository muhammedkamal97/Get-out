package View.Graphics.ImagesMaps;

import javafx.scene.image.Image;
import java.util.HashMap;
import java.util.Map;

public class TrapsMap {


    private static volatile TrapsMap instance;
    private static Object mutex = new Object();

    private Map<String, Image> images;

    private TrapsMap() {
        images = new HashMap<>();
        constructLayer();
    }

    public static TrapsMap getInstance() {
        TrapsMap result = instance;
        if (result == null) {
            synchronized (mutex) {
                result = instance;
                if (result == null)
                    instance = result = new TrapsMap();
            }
        }
        return result;
    }

    public void constructLayer() {

//        images.put("WoodWall2", new Image("WoodWall2.png"));
        
    }

    public Image getBufferedImage(String key) {
        return images.get(key);
    }

}
