package View;

import View.Graphics.ImagesMaps.CharactersMap;
import drawables.characters.Hero;
import drawables.characters.heros.HeroesFactory;
import drawables.characters.heros.Hulk;
import gameLevels.LevelProperties;
import gameLevels.LevelsFactory;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.apache.log4j.Logger;

import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collection;
import java.util.logging.Level;

/**
 * Created by Mahmoud on 12/17/2017.
 */
public class HeroMenuController {

    @FXML
    private BorderPane heroPane;
    @FXML
    private ImageView heroPreview;
    @FXML
    private ComboBox<String> levelBox;

    private Logger logger = Logger.getLogger(HeroMenuController.class);


    private GridPane heroGrid;
    private HeroesFactory heroesFactory;
    private LevelsFactory levelsFactory;
    private ArrayList<ImageView> heroesImages;
    private ArrayList<Class<? extends Hero>> heroesClasses;
    private ArrayList<Class<? extends LevelProperties>> levels;

    public HeroMenuController() {
        this.heroesFactory = new HeroesFactory();
        this.levelsFactory = new LevelsFactory();
        this.heroGrid = new GridPane();
    }

    @FXML
    public void initialize() {

        loadClasses();
        loadImages();
        loadLevels();

        setGridPane();
    }


    private void loadClasses() {
        this.heroesClasses = this.heroesFactory.getClasses();
        this.levels = this.levelsFactory.getClasses();

    }

    private void loadImages() {

        int columns = (int) Math.ceil(Math.sqrt(heroesClasses.size()));
        this.heroesImages = new ArrayList<>();

        for (Class<? extends Hero> hero : heroesClasses) {
            ImageView imageView = new ImageView(getImage(hero.getSimpleName()));
            imageView.setFitHeight(100.0);
            imageView.setFitWidth(100.0);
            heroesImages.add(imageView);
        }

        for (int i = 0; i < heroesImages.size(); i++) {
            int row = i / columns;
            int column = i % columns;
            this.heroGrid.add(heroesImages.get(i), column, row);
        }

        this.heroPreview.setImage(heroesImages.get(0).getImage());

    }

    private void loadLevels() {

        for (Class<? extends LevelProperties> level : this.levels) {
            String name = level.getSimpleName();
            this.levelBox.getItems().add(name.substring(name.length() - 1));
        }

    }

    private Image getImage(String key) {
        CharactersMap map = CharactersMap.getInstance();
        return map.getImageSprite(key).getImageIdentity();
    }

    private void setGridPane() {
        this.heroGrid.gridLinesVisibleProperty().setValue(true);
        this.heroPane.setCenter(this.heroGrid);

        this.heroGrid.getChildren().forEach(img -> img.setOnMouseClicked(e -> {
            this.heroPreview.setImage(((ImageView) img).getImage());
        }));
    }

    private int findHero() {
        Image heroToFind = this.heroPreview.getImage();
        for (int i = 0; i < this.heroesImages.size(); i++) {
            ImageView imageView = this.heroesImages.get(i);

            if (imageView.getImage().equals(heroToFind))
                return i;
        }

        throw null;
    }

    @FXML
    public void submitButtonAction() {
        int index;

        try {
            index = findHero();
        } catch (Exception e) {
            logger.fatal("Could not find hero");
            throw new RuntimeException("Fatal error, cannot find hero");
        }

        Class<? extends Hero> hero = this.heroesClasses.get(index);

        int level;
        try {
            level = Integer.parseInt(this.levelBox.getValue());

        } catch (Exception e) {
            logger.warn("User did not choose level");
            level = 1;
        }

        Stage stage = (Stage) this.heroGrid.getScene().getWindow();
        stage.close();

        try {
            new Canvas().startView(hero, level, this.levelBox.getItems().size());
        } catch (Exception e) {
            logger.fatal("Could not launch Canvas");
            throw new RuntimeException("Fatal error, cannot launch canvas");
        }

    }

}
