package View.Graphics.Sprite;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class MySprite {

    private ArrayList<BufferedImage> imageSprite;
    private int count;

    public MySprite() {
        this.imageSprite = new ArrayList<>();
        count = 0;
    }

    /**
     * @param gc
     * @param widthCell  is the cell dimension supposed being squared
     * @param heightCell is the cell dimension supposed being squared
     */
    public void drawNextSprite(GraphicsContext gc, int widthCell, int heightCell, int posX, int posY) {
        count++;
        count %= imageSprite.size();
        Image img = SwingFXUtils.toFXImage(imageSprite.get(count), null);
        gc.drawImage(img, posX, posY, widthCell, heightCell);
    }

    /**
     * @param width   image's width
     * @param height  image's height
     * @param rows    number of frames in the row
     * @param columns number of frames in the column
     * @param ss      the image sent as a BufferedImage
     */
    public void SpriteSheetBuffer(int width, int height, int rows, int columns, BufferedImage ss) {
        int TileW = width / columns;
        int TileH = height / rows;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                imageSprite.add(ss.getSubimage(j * TileW, i * TileH, TileW, TileH));
                imageSprite.add(ss.getSubimage(j * TileW, i * TileH, TileW, TileH));
                imageSprite.add(ss.getSubimage(j * TileW, i * TileH, TileW, TileH));
            }
        }
    }

    public void drawReleased(GraphicsContext gc, int widthCell, int heightCell, int posX, int posY) {
        count = 1;
        Image img = SwingFXUtils.toFXImage(imageSprite.get(count), null);
        gc.drawImage(img, posX, posY, widthCell, heightCell);
    }
}

