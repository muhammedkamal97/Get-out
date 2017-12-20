package View;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;

/**
 * Created by Mahmoud on 12/20/2017.
 */
public class CreditsSceneController {

    public TextFlow rollCredit;
    public Button returntoMenu;

    private String credit = "This program exists because thanks to the amazing effort of 5 programmers.\n " +
            "Thanks to their dedication, this masterpiece is available for free.\n So while you are enjoying " +
            "your time playing, we would like you to do us a favor. Pray for us :)\n " +
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
            throw new RuntimeException("Failed to load menu");
        }
    }
}
