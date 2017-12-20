package View;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.TextFlow;

/**
 * Created by Mahmoud on 12/20/2017.
 */
public class CreditsSceneController {

    public TextFlow rollCredit;

    private String credit = "We would like to thank the lord for his gifts";
    @FXML
    public void initialize()
    {
        rollCredit.setLineSpacing(1.5);
        rollCredit.setTextAlignment(TextAlignment.CENTER);
        rollCredit.getChildren().add(new Text(credit));
    }

}
