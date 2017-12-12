package View.Animation;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;

import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

public class SteadyLayer implements LoadImages{

    Map<String,BufferedImage> images;

    public SteadyLayer () {
        images = new HashMap<>();
    }

    @Override
    public void constructLayer() {
        images.put("road",SwingFXUtils.fromFXImage(new Image("run.png"), null));
    }

    @Override
    public BufferedImage getBufferedImage(String key) {
        return images.get(key);
    }

}
