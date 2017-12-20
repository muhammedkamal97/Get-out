package View;

import javafx.fxml.FXML;

public class MenuController {

    @FXML
    public void startHeroMenu () throws Exception {
        new HeroMenu().startView();
    }

}
