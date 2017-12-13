package View.Graphics.ImagesLoaders;

import java.awt.image.BufferedImage;

public interface LoadImages {


    public void constructLayer();

    public BufferedImage getBufferedImage(String key);

}
