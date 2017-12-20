package View;

import javafx.application.Application;
import javafx.stage.Stage;
import org.apache.log4j.Logger;


/**
 * Created by Mahmoud on 12/20/2017.
 */
public class Main extends Application
{

    private static Logger logger = Logger.getLogger(Main.class);

    public static void main(String[] args) {launch(args);}

    @Override
    public void start(Stage primaryStage) throws Exception {

        logger.info("Starting the game");
        new MenuScene().startView();
    }
}
