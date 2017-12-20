package View;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import org.apache.log4j.Logger;

public class MenuController {

    @FXML
    private Button submitButton;
    private Logger logger = Logger.getLogger(MenuController.class);


    @FXML
    public void startHeroMenu () throws Exception {
        new HeroMenu().startView();
        logger.info("Opening hero selection menu");
        Stage stage = (Stage) this.submitButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void rollCredits()
    {

        try {
            new CreditsScene().startView();
        } catch (Exception e) {
            logger.fatal("Could not open Credits");
            throw new RuntimeException("Could not open credit");
        }

        Stage stage = (Stage) this.submitButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void guides()
    {

        try {
            new GuidesScene().startView();
        } catch (Exception e) {
            logger.fatal("Could not open user guide");
            throw new RuntimeException("Could not open user guide");
        }

        Stage stage = (Stage) this.submitButton.getScene().getWindow();
        stage.close();
    }

}
