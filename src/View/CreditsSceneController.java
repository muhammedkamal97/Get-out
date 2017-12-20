package View;

import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
import org.apache.log4j.Logger;

/**
 * Created by Mahmoud on 12/20/2017.
 */
public class CreditsSceneController {

    public TextFlow rollCredit;
    public Button returntoMenu;

    private Logger logger = Logger.getLogger(CreditsSceneController.class);


    private String credit = "This program exists because thanks to the amazing effort of 5 programmers." +
            "Thanks to their dedication, this masterpiece is available for free. So while you are enjoying " +
            "your time playing, we would like you to do us a favor. Pray for us :) " +
            "Programmers: \n" +
            "- Aya Ashraf\n" +
            "- Sarah el dafrawy\n" +
            "- Abdelrahman Ibrahim\n" +
            "- Mohammad Kamal\n" +
            "- Mahmoud Gamal\n";
    @FXML
    public void initialize()
    {
        rollCredit.setLineSpacing(1.5);
        rollCredit.setTextAlignment(TextAlignment.JUSTIFY);
        rollCredit.setPadding(new Insets(0,80,0,80));
        rollCredit.getChildren().add(new Text(credit));
    }

    @FXML
    public void returnToMenu()
    {
        Stage stage = (Stage) this.returntoMenu.getScene().getWindow();
        stage.close();

        try {
            new MenuScene().startView();
        } catch (Exception e) {
            logger.fatal("Could not return to menu");
            throw new RuntimeException("Failed to load menu");
        }
    }
}
