package View;

import drawables.characters.Hero;
import drawables.characters.heros.HeroesFactory;
import drawables.characters.heros.Hulk;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
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

/**
 * Created by Mahmoud on 12/17/2017.
 */
public class HeroMenuController {

    @FXML
    private BorderPane pane;
    @FXML
    private VBox vbox;
    @FXML
    private Button Done;


    private GridPane heroGrid;
    private HeroesFactory heroesFactory;
    private Class<? extends Hero> chosenHero;

    public HeroMenuController() {
        this.heroesFactory = new HeroesFactory();
        this.heroGrid = new GridPane();
    }

    @FXML
    public void initialize() {

        ArrayList<String> heroesNames = new ArrayList<>();
        loadHeroes(heroesNames);


        ImageView imageView = new ImageView(new Image("Trap.png"));

        imageView.setFitHeight(100.0);
        imageView.setFitWidth(100.0);
        ImageView imageView1 = new ImageView(new Image("AK47.png"));

        imageView1.setFitWidth(100.0);
        imageView1.setFitHeight(100.0);


        this.heroGrid.add(imageView, 0, 0);
        this.heroGrid.add(imageView1, 1, 0);

        this.heroGrid.gridLinesVisibleProperty().setValue(true);
        this.pane.setCenter(this.heroGrid);

        ImageView imageView2 = new ImageView(imageView.getImage());
        imageView2.setFitWidth(150.0);
        imageView2.setFitHeight(150.0);
        vbox.getChildren().add(imageView2);

        this.heroGrid.getChildren().forEach(img -> img.setOnMouseClicked(e -> {

            ImageView imageView3 = new ImageView(((ImageView) img).getImage());
            imageView3.setFitWidth(150.0);
            imageView3.setFitHeight(150.0);
            vbox.getChildren().remove(0);
            vbox.getChildren().add(imageView3);
        }));

    }

    @FXML
    public void doneChoosing(ActionEvent event) {
//        chosenHero = blah; //TODO set chosenHero
        Stage stage = (Stage) Done.getScene().getWindow();
        chosenHero = Hulk.class;
        stage.close();
        //7ab3at class men el Hero
    }


    @FXML
    public void chooseCharacter(KeyEvent event) {

        //set Class Hero chosen
    }

    private void loadHeroes(ArrayList<String> heroesNames) {
        ArrayList<Class<? extends Hero>> heroesClasses = this.heroesFactory.getClasses();

        for (int i = 0; i < heroesClasses.size(); i++)
//            heroes.add(this.heroesFactory.factoryMethod(heroesClasses.get(i).getName()));
            heroesNames.add(heroesClasses.get(i).getSimpleName());
    }

    public Class<? extends Hero> getChosenHero() {
        return chosenHero;
    }
}
