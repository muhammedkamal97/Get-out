package View;

import drawables.characters.Hero;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;

public class Canvas {

    public void startView(Class<? extends Hero> hero, int lvl, int maxLvl) throws Exception {
        Stage stage = new Stage();

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Canvas.fxml"));
        Parent root = fxmlLoader.load();

        Scene scene = new Scene(root);
        PerspectiveCamera camera = new PerspectiveCamera(true);
        camera.setNearClip(0.1); // clipping image zoom in
        camera.setFarClip(10000.0);  //clipping image zoom out

        scene.setCamera(camera);


        Rotate xRotate = new Rotate(0,0,0,0, Rotate.X_AXIS);
        Rotate yRotate = new Rotate(0,0,0,0, Rotate.Y_AXIS);
        Rotate zRotate = new Rotate(0,0,0,0, Rotate.Z_AXIS);

        camera.getTransforms().addAll(xRotate, yRotate, zRotate);

        stage.setTitle("FXML Welcome");
        stage.setScene(scene);
        CanvasController controller = fxmlLoader.<CanvasController>getController();
        controller.initLogin(hero, lvl, maxLvl, camera);
        stage.show();

    }

}
