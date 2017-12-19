package View;

import View.Graphics.ImagesMaps.CharactersMap;
import drawables.characters.Hero;
import drawables.characters.heros.Flash;
import drawables.characters.heros.Hulk;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class MenuController {

    @FXML
    public void startHeroMenu () throws Exception {
        new HeroMenu().startView();
    }

}
