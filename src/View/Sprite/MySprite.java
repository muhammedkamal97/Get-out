package View.Sprite;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.util.ArrayList;

public class MySprite {

    private ArrayList<Image> imageSprite;
    private int count;

    MySprite(ArrayList<Image> imageSprite) {
        this.imageSprite = imageSprite;
    }

    public void drawNextSprite(GraphicsContext gc) {
        count++;
        count%= imageSprite.size();
//        gc.clearRect(); //clear area aound sprite
//        gc.drawImage(MySprite.get(count));
    }

}
