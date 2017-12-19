package View;

import drawables.Drawable;
import drawables.obstacles.Wall;
import javafx.scene.shape.Rectangle;

import java.awt.*;

public class ShapeIntersectionDetector {

    public boolean intersects (Drawable obj, Point point, int cellWidth, int cellHeight , int xDiff, int yDiff) {
        if (!(obj instanceof Wall)) {
            return false;
        }
        Point pt = obj.getPosition();
        javafx.scene.shape.Shape rect1 = new javafx.scene.shape.Rectangle(pt.getX()*cellWidth+xDiff, pt.getY()*cellHeight+ yDiff, cellWidth,cellHeight);
        javafx.scene.shape.Shape rect2 = new Rectangle(point.getX(), point.getY() , cellWidth,cellHeight);
        return rect1.intersects(rect2.getBoundsInLocal());
    }

}
