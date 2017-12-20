package View;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.apache.log4j.Logger;

/**
 * Created by Mahmoud on 12/19/2017.
 */
public class CreditsScene {

    private Logger logger = Logger.getLogger(CreditsScene.class);

    public void startView() throws Exception
    {

        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("CreditsScene.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);
        stage.setTitle("Credits");
        stage.setScene(scene);
        logger.info("Opening credits");
        stage.show();
    }


}
