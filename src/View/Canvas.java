package View;

import drawables.characters.Hero;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Canvas {

    public void startView(Class<? extends Hero> hero, int lvl) throws Exception {
        Stage stage = new Stage();

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Canvas.fxml"));
        Parent root = (Parent)fxmlLoader.load();

        Scene scene = new Scene(root);
        stage.setTitle("FXML Welcome");
        stage.setScene(scene);
        CanvasController controller = fxmlLoader.<CanvasController>getController();
        controller.initLogin(hero, lvl);
        stage.show();

    }

}
