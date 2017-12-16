package View;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Canvas {

    public void startView() throws Exception {
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("Canvas.fxml"));

        Scene scene = new Scene(root);
        stage.setTitle("FXML Welcome");
        stage.setScene(scene);

        stage.show();
    }

}
