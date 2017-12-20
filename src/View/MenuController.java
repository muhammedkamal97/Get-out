package View;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class MenuController {

    @FXML
    private Button submitButton;
    @FXML
    public void startHeroMenu () throws Exception {
        new HeroMenu().startView();

        Stage stage = (Stage) this.submitButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void rollCredits()
    {

        try {
            new CreditsScene().startView();
        } catch (Exception e) {
            e.printStackTrace();
        }

        Stage stage = (Stage) this.submitButton.getScene().getWindow();
        stage.close();
    }

}
