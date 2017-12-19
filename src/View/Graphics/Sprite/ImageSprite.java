package View.Graphics.Sprite;

import javafx.scene.image.Image;

import java.awt.image.BufferedImage;

public interface ImageSprite {

    public BufferedImage getImageLeft();
    public BufferedImage getImageRight();
    public BufferedImage getImageUp();
    public BufferedImage getImageDown();
    public int getFramesInRow();
    public int getFramesInColumn();
    public Image getImageIdentity();

    }
