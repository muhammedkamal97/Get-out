package View;

import drawables.characters.Hero;
import drawables.characters.heros.Hulk;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class HeroMenu {

    private Class<? extends Hero> chosenHero;

    public Class<? extends Hero> startView( ) throws Exception {

        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("HeroMenu.fxml"));
        Parent root = (Parent)fxmlLoader.load();

        Scene scene = new Scene(root);
        stage.setTitle("FXML Welcome");
        stage.setScene(scene);
        HeroMenuController controller = fxmlLoader.<HeroMenuController>getController();
        this.chosenHero = controller.getChosenHero();
        stage.show();

        return Hulk.class;
//        return chosenHero; //TODO
    }
}
