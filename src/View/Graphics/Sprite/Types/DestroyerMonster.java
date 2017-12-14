package View.Graphics.Sprite.Types;


import View.Graphics.Sprite.ImageSprite;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;

import java.awt.image.BufferedImage;

public class DestroyerMonster implements ImageSprite {

    private final Image imgLeft = new Image("run.png");
    private final Image imgRight = new Image("run.png");
    private final Image imgDown = new Image("run.png");
    private final Image imgUp = new Image("run.png");
    private final int framesInRow = 4;
    private final int framesInColumn = 4;

    @Override
    public final BufferedImage getImageLeft() {
        return SwingFXUtils.fromFXImage(imgLeft, null);
    }

    @Override
    public final BufferedImage getImageRight() {
        return SwingFXUtils.fromFXImage(imgRight, null);
    }

    @Override
    public final BufferedImage getImageDown() {
        return SwingFXUtils.fromFXImage(imgDown, null);
    }

    @Override
    public final BufferedImage getImageUp() {
        return SwingFXUtils.fromFXImage(imgUp, null);
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
