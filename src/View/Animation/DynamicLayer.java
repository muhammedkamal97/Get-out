package View.Animation;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;

import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

public class DynamicLayer implements LoadImages{

    Map<String,BufferedImage> images;

    public DynamicLayer () {
        images = new HashMap<>();
    }

    @Override
    public void constructLayer() {
        images.put("temp",SwingFXUtils.fromFXImage(new Image("run.png"), null));
    }

    @Override
    public BufferedImage getBufferedImage(String key) {
        return images.get(key);
    }

}
