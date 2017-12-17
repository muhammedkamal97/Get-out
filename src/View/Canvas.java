package View;

import drawables.characters.Hero;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Canvas {

    public void startView(Hero hero) throws Exception {
        Stage stage = new Stage();
        //        Parent root = FXMLLoader.load(getClass().getResource("Canvas.fxml"));

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Canvas.fxml"));
//        fxmlLoader.setController(new CanvasController());
        Parent root = (Parent)fxmlLoader.load();

        Scene scene = new Scene(root);
        stage.setTitle("FXML Welcome");
        stage.setScene(scene);
        CanvasController controller = fxmlLoader.<CanvasController>getController();
        controller.initLogin(hero);
        stage.show();
    }

}
