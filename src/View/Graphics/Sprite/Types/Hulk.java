package View.Graphics.Sprite.Types;


import View.Graphics.Sprite.ImageSprite;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;

import java.awt.image.BufferedImage;

public class Hulk implements ImageSprite {

    private final Image imgLeft = new Image("hero3.png");
    private final int framesInRow = 1;
    private final int framesInColumn = 3;

    @Override
    public final BufferedImage getImageLeft() {
        BufferedImage buff = SwingFXUtils.fromFXImage(imgLeft, null);
        return buff.getSubimage(0,(int)(1*(imgLeft.getHeight()/4)),(int)imgLeft.getWidth(), (int)(imgLeft.getHeight()/4));
    }

    @Override
    public final BufferedImage getImageRight() {
        BufferedImage buff = SwingFXUtils.fromFXImage(imgLeft, null);
        return buff.getSubimage(0,(int)(2*(imgLeft.getHeight()/4)),(int)imgLeft.getWidth(), (int)(imgLeft.getHeight()/4));
    }

    @Override
    public final BufferedImage getImageDown() {
        BufferedImage buff = SwingFXUtils.fromFXImage(imgLeft, null);
        return buff.getSubimage(0,(int)(0*(imgLeft.getHeight()/4)),(int)imgLeft.getWidth(), (int)(imgLeft.getHeight()/4));
    }

    @Override
    public final BufferedImage getImageUp() {
        BufferedImage buff = SwingFXUtils.fromFXImage(imgLeft, null);
        return buff.getSubimage(0,(int)(3*(imgLeft.getHeight()/4)),(int)imgLeft.getWidth(), (int)(imgLeft.getHeight()/4));
    }

    @Override
    public Image getImageIdentity() {
        BufferedImage buff = SwingFXUtils.fromFXImage(imgLeft, null);
        BufferedImage buffCroped = buff.getSubimage(0,0,(int)imgLeft.getWidth()/framesInColumn, (int)(imgLeft.getHeight()/4));
        return SwingFXUtils.toFXImage(buffCroped, null);
    }

    @Override
    public int getFramesInRow() {
        return framesInRow;
    }

    @Override
    public int getFramesInColumn() {
        return framesInColumn;
    }
}
