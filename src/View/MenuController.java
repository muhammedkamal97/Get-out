package View;

import View.Graphics.ImagesMaps.CharactersMap;
import drawables.characters.Hero;
import drawables.characters.heros.Flash;
import drawables.characters.heros.Hulk;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class MenuController {

    @FXML
    public void startCanvas (ActionEvent event) throws Exception {
//        Hero hero = new Flash(); // sent from previous scene
        Hero hero = new Hulk();
        new Canvas().startView(hero);
    }

}
