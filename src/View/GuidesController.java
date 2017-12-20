package View;

import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;

/**
 * Created by Mahmoud on 12/20/2017.
 */
public class GuidesController {

    public TextFlow rollCredit;
    public Button returntoMenu;

    private String credit = "Allowed Movements are using keyboard keys:\n\n"+
            "key UP: moves hero step up\n" +
            "key DOWN:moves hero step down\n" +
            "key LEFT: moves hero step left\n" +
            "key SPACE: shoots bullets\n" +
            "key E: holds next weapon\n" +
            "key Q: holdPreviousWeapon\n" +
            "key V: zoom in and out \n";
    @FXML
    public void initialize()
    {
        rollCredit.setLineSpacing(1.5);
        rollCredit.setTextAlignment(TextAlignment.JUSTIFY);
        rollCredit.setPadding(new Insets(0,0,0,100));
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
