package View;

import drawables.characters.Hero;
import drawables.characters.heros.Hulk;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class HeroMenu {


    public void startView() throws Exception {

        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("HeroMenu.fxml"));
        Parent root = fxmlLoader.load();

        Scene scene = new Scene(root);
        stage.setTitle("Choose your character");
        stage.setScene(scene);
        stage.show();
    }
}
