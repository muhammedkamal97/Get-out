package View;

import View.Sounds.SoundMap;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.apache.log4j.Logger;

public class MenuScene {


    private Logger logger = Logger.getLogger(MenuScene.class);
    public void startView() throws Exception {

        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("MenuScene.fxml"));
        Parent root = fxmlLoader.load();

        Scene scene = new Scene(root);
        stage.setTitle("The Maze Runner");
        stage.setScene(scene);
        stage.show();

        logger.info("Launching Menu");
//        SoundMap map = SoundMap.getInstance();
//        map.getMediaPlayer("StartMenu").play();
    }
}
