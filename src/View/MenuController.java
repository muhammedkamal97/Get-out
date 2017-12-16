package View;

import View.Graphics.ImagesMaps.CharactersMap;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class MenuController {

    @FXML
    public void startCanvas (ActionEvent event) throws Exception {
        new Canvas().startView();
    }

}
