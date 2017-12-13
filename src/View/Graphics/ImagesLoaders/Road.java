package View.Graphics.ImagesLoaders;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;

import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

/**
 * Layer for gifts, traps, bombs
 */
public class Road implements LoadImages {

    Map<String,BufferedImage> images;

    public Road () {
        images = new HashMap<>();
    }

    @Override
    public void constructLayer() {

        images.put("GrassRoad",SwingFXUtils.fromFXImage(
                new Image("GrassRoad.png"), null));
//        images.put("GreyRoad",SwingFXUtils.fromFXImage(
//                new Image("GreyRoad.png"), null));
    }

    @Override
    public BufferedImage getBufferedImage(String key) {
        return images.get(key);
    }

}
