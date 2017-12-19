package View;

import View.Graphics.ImagesMaps.CharactersMap;
import drawables.characters.Hero;
import drawables.characters.heros.HeroesFactory;
import drawables.characters.heros.Hulk;
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

import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by Mahmoud on 12/17/2017.
 */
public class HeroMenuController {

    @FXML
    private BorderPane pane;
    @FXML
    private ImageView heroPreview;
    @FXML
    private ComboBox<Integer> levelBox;


    private GridPane heroGrid;
    private HeroesFactory heroesFactory;
    private Class<? extends Hero> chosenHero;
    private ArrayList<ImageView> heroesImages;
    private ArrayList<Class<? extends Hero>> heroesClasses;

    public HeroMenuController() {
        this.heroesFactory = new HeroesFactory();
        this.heroGrid = new GridPane();
    }

    @FXML
    public void initialize() {

        this.heroesClasses = this.heroesFactory.getClasses();
        loadImages();

        this.heroGrid.gridLinesVisibleProperty().setValue(true);
        this.pane.setCenter(this.heroGrid);

        this.heroGrid.getChildren().forEach(img -> img.setOnMouseClicked(e -> {
            this.heroPreview.setImage(((ImageView) img).getImage());
        }));

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

    private Image getImage(String key) {
        CharactersMap map = CharactersMap.getInstance();
        return map.getImageSprite(key).getImageIdentity();
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
            throw new RuntimeException("Fatal error, cannot find hero");
        }

        Class<? extends Hero> hero = this.heroesClasses.get(index);

        Stage stage = (Stage)this.heroGrid.getScene().getWindow();
        stage.close();
        try{
            new Canvas().startView(hero,1);
        } catch (Exception e) {
            throw new RuntimeException("Fatal error, cannot launch canvas");
        }

    }
}
