package View;

import View.Graphics.ImagesMaps.CharactersMap;
import drawables.characters.Hero;
import drawables.characters.heros.Flash;
import drawables.characters.heros.Hulk;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class MenuController {

    private Class<? extends Hero> hero;
    private int lvl;

    @FXML
    public void startCanvas (ActionEvent event) throws Exception {
        new Canvas().startView(hero, lvl);
    }

    @FXML
    public void startHeroMenu (ActionEvent event) throws Exception {

        this.hero = new HeroMenu().startView();
//        this.lvl = new lvlMenu().startView(); //TODO
        this.lvl = 1;
    }

}
