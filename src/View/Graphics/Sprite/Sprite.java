package View.Graphics.Sprite;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public interface Sprite {

    /**
     * @param gc
     * @param widthCell is the cell dimension supposed being squared
     * @param heightCell is the cell dimension supposed being squared
     */
    public void drawNextSprite(GraphicsContext gc, int widthCell, int heightCell, int posX ,int posY);

    /**
     * @param width   image's width
     * @param height  image's height
     * @param rows    number of frames in the row
     * @param columns number of frames in the column
     * @param ss      the image sent as a BufferedImage
     */
    public void SpriteSheetBuffer(int width, int height, int rows, int columns, BufferedImage ss);
    public void drawInNewPos (GraphicsContext gc, int widthCell, int heightCell, Point oldPos, Point newPos);
}
