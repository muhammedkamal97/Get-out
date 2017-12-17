package View;

import drawables.characters.Hero;
import drawables.characters.heros.HeroesFactory;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Created by Mahmoud on 12/17/2017.
 */
public class HeroMenu {

    public BorderPane pane;
    public VBox vbox;

    private GridPane heroGrid;
    private HeroesFactory heroesFactory;

    public HeroMenu ()
    {
        this.heroesFactory = new HeroesFactory();
        this.heroGrid = new GridPane();
    }

    @FXML
    public void initialize()
    {

        ArrayList<Hero> heroes = new ArrayList<>();
        loadHeroes(heroes);


        ImageView imageView = new ImageView(new Image("run.png"));

        imageView.setFitHeight(100.0);
        imageView.setFitWidth(100.0);
        ImageView imageView1 = new ImageView(new Image("AK47.png"));

        imageView1.setFitWidth(100.0);
        imageView1.setFitHeight(100.0);


        this.heroGrid.add(imageView,0,0);
        this.heroGrid.add(imageView1,1,0);

        this.heroGrid.gridLinesVisibleProperty().setValue(true);
        this.pane.setCenter(this.heroGrid);

        ImageView imageView2 = new ImageView(imageView.getImage());
        imageView2.setFitWidth(150.0);
        imageView2.setFitHeight(150.0);
        vbox.getChildren().add(imageView2);

        this.heroGrid.getChildren().forEach(img -> img.setOnMouseClicked(e->{

            ImageView imageView3 = new ImageView(((ImageView)img).getImage());
            imageView3.setFitWidth(150.0);
            imageView3.setFitHeight(150.0);
            vbox.getChildren().remove(0);
            vbox.getChildren().add(imageView3);
        }));

    }

    @FXML
    public void chooseCharacter(KeyEvent event)
    {

    }

    private void loadHeroes(ArrayList<Hero> heroes)
    {
        ArrayList<Class<? extends Hero>> heroesClasses = this.heroesFactory.getClasses();

        for(int i = 0 ; i < heroesClasses.size() ; i++)
            heroes.add(this.heroesFactory.factoryMethod(heroesClasses.get(i).getName()));
    }
}
