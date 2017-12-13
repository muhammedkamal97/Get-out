package View.Graphics.ImagesLoaders;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;

import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

public class Heroes implements LoadImages{

    Map<String,BufferedImage> images;

    public Heroes () {
        images = new HashMap<>();
    }

    @Override
    public void constructLayer() {

        images.put("CuteGirl",SwingFXUtils.fromFXImage(
                new Image("run.png"), null));

    }

    @Override
    public BufferedImage getBufferedImage(String key) {
        return images.get(key);
    }

}
