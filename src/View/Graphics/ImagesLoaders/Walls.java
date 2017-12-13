package View.Graphics.ImagesLoaders;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;

import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

public class Walls implements LoadImages {

    Map<String,BufferedImage> images;

    public Walls () {
        images = new HashMap<>();
    }

    @Override
    public void constructLayer() {

        images.put("Bricks",SwingFXUtils.fromFXImage(
                new Image("Bricks.png"), null));
        images.put("TreeWall",SwingFXUtils.fromFXImage(
                new Image("TreeWall.png"), null));
        images.put("WoodWall1",SwingFXUtils.fromFXImage(
                new Image("WoodWall1.png"), null));
        images.put("WoodWall2",SwingFXUtils.fromFXImage(
                new Image("WoodWall2.png"), null));
    }

    @Override
    public BufferedImage getBufferedImage(String key) {
        return images.get(key);
    }

}
