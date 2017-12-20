package View;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.apache.log4j.Logger;

/**
 * Created by Mahmoud on 12/19/2017.
 */
public class GuidesScene {

    private Logger logger = Logger.getLogger(GuidesScene.class);

    public void startView() throws Exception
    {

        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("GuidesScene.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);
        stage.setTitle("Guides");
        stage.setScene(scene);
        logger.info("Opening user guide");
        stage.show();
    }


}
