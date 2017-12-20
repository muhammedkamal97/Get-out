package View;

import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Created by Mahmoud on 12/20/2017.
 */
public class Main extends Application
{

    public static void main(String[] args)
    {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        new MenuScene().startView();
    }
}
