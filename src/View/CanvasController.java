package View;

import View.Graphics.ImagesMaps.MazeMap;
import drawables.Drawable;
import drawables.characters.Hero;
import drawables.characters.Monster;
import drawables.characters.heros.states.DirectionDownState;
import drawables.characters.heros.states.DirectionLeftState;
import drawables.characters.heros.states.DirectionRightState;
import drawables.characters.heros.states.DirectionUpState;
import View.Graphics.ExplosionAnimation;
import drawables.pickables.Weapon;
import drawables.roads.Road;
import gameCore.RunnerGameAdapter;
import gameLoop.GameLoop;
import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import observer.BombExplosionObserver;
import observer.HeroStateObserver;
import observer.MazeLayersObserver;

import java.awt.*;

public class CanvasController implements MazeLayersObserver, BombExplosionObserver, HeroStateObserver {

    @FXML
    private Button Menu;
    @FXML
    private Canvas dynamicCanvas;
    @FXML
    private Canvas steadyCanvas;
    @FXML
    private Canvas mazeCanvas;
    @FXML
    private Canvas animCanvas;
    @FXML
    private ImageView weaponImage;
    @FXML
    private Label trials;
    @FXML
    private Label coins;
    @FXML
    private Label bullets;
    @FXML
    private ProgressBar healthBar;
    @FXML
    private ProgressBar armorBar;
    private GraphicsContext gcD;
    private GraphicsContext gcAnimation;
    private GraphicsContext gcM;
    private GraphicsContext gcS;
    private int shiftDown = 30;
    private int cellWidth;
    private int cellHeight;
    private GameLoop gameLoop;
    private Hero hero;
    private Point currentPosition;

    //dummy values to update hero's postion don't modify them!!!!
    private int x, y;

    //TODO  Weapons Icons //set in map

    //TODO bullet will be an ellipse moving must have draw function
    //TODO modify stylesheet
    //TODO camera
    //TODO collision
    //TODO bullets observer

    //TODO HERE
    //TODO HERE shield bar //removed or added
    //TODO HERE score bar elly heya coins
    //TODO HERE armorpoints

    @FXML
    protected void MenuButtonAction(ActionEvent event) {
        Stage stage = (Stage) Menu.getScene().getWindow();
        stage.close();
//        animation();
    }

    @FXML
    public void keyPressedSwitch(KeyEvent event) {
        switch (event.getCode()) {
            case LEFT:
            case KP_LEFT:
                x -= 10;
                System.out.println(-cellWidth);
                System.out.println(x);
                if (x < (-cellWidth / 6)) {
                    gameLoop.moveHeroLeft();
                    if (!hero.getPosition().equals(currentPosition)) {
                        x += cellWidth;
                    } else {
                        x += 10;
                    }
                }
                gameLoop.lookLeft();
                hero.setDirectionState(new DirectionLeftState()); //testing
                currentPosition = hero.getPosition();
                System.out.println(currentPosition);
                hero.drawOnCanvas(gcD, new Point((int) (currentPosition.getX() * cellWidth + x),
                                (int) (currentPosition.getY() * cellHeight + y + shiftDown)),
                        cellWidth, cellHeight);
                break;
            case RIGHT:
            case KP_RIGHT:
                x += 10;
                System.out.println(x);
                if (x > cellWidth / 6) {
                    gameLoop.moveHeroRight();
                    if (!hero.getPosition().equals(currentPosition)) {
                        x -= cellWidth;
                    } else {
                        x -= 10;
                    }
                }
                gameLoop.lookRight();
                hero.setDirectionState(new DirectionRightState()); //testing
                currentPosition = hero.getPosition();
                System.out.println(currentPosition);
                hero.drawOnCanvas(gcD, new Point((int) (currentPosition.getX() * cellWidth + x),
                                (int) (currentPosition.getY() * cellHeight + y + shiftDown)),
                        cellWidth, cellHeight);
                break;
            case UP:
            case KP_UP:
                y -= 10;
                if (y < -(cellHeight / 6)) {
                    gameLoop.moveHeroUp();
                    if (!hero.getPosition().equals(currentPosition)) {
                        y += cellHeight;
                    } else {
                        y += 10;
                    }
                }
                gameLoop.lookUp();
                hero.setDirectionState(new DirectionUpState()); //testing
                currentPosition = hero.getPosition();
                hero.drawOnCanvas(gcD, new Point((int) (currentPosition.getX() * cellWidth + x),
                                (int) (currentPosition.getY() * cellHeight + y + shiftDown))
                        , cellWidth, cellHeight);
                break;
            case DOWN:
            case KP_DOWN:
                y += 10;
                if (y > cellHeight / 6) {
                    gameLoop.moveHeroDown();
                    if (!hero.getPosition().equals(currentPosition)) {
                        y -= cellHeight;
                    } else {
                        y -= 10;
                    }
                }
                gameLoop.lookDown();
                hero.setDirectionState(new DirectionDownState()); //testing
                currentPosition = hero.getPosition();
                hero.drawOnCanvas(gcD, new Point((int) (currentPosition.getX() * cellWidth + x),
                        (int) (currentPosition.getY() * cellHeight + y + shiftDown)), cellWidth, cellHeight);
                break;
            default:
                break;
        }

    }

    @FXML
    public void keyReleasedSwitch(KeyEvent event) {
        switch (event.getCode()) {
            case LEFT:
            case KP_LEFT:
                hero.drawOnReleased(gcD, new Point((int) (currentPosition.getX() * cellWidth + x),
                        (int) (currentPosition.getY() * cellHeight + y + shiftDown)), cellWidth, cellHeight);
                break;
            case RIGHT:
            case KP_RIGHT:
                hero.drawOnReleased(gcD, new Point((int) (currentPosition.getX() * cellWidth + x),
                        (int) (currentPosition.getY() * cellHeight + y + shiftDown)), cellWidth, cellHeight);
                break;
            case UP:
            case KP_UP:
                hero.drawOnReleased(gcD, new Point((int) (currentPosition.getX() * cellWidth + x),
                        (int) (currentPosition.getY() * cellHeight + y + shiftDown)), cellWidth, cellHeight);
                break;
            case DOWN:
            case KP_DOWN:
                hero.drawOnReleased(gcD, new Point((int) (currentPosition.getX() * cellWidth + x),
                        (int) (currentPosition.getY() * cellHeight + y + shiftDown)), cellWidth, cellHeight);
                break;
            case CONTROL:
                gameLoop.shoot();
                break;
            case N:
                gameLoop.holdNextWeapon();
                break;
            case P:
                gameLoop.holdPreviousWeapon();
                break;
            default:
                break;
        }
    }

    public void initLogin(Hero hero) {
        this.hero = hero;
        RunnerGameAdapter game = new RunnerGameAdapter();
        game.InitializeMaze(10);
        gameLoop = game.getGameLoop();
        gameLoop.setHero(hero);
        gameLoop.registerAsMazeLayerObserver(this);
        gameLoop.registerAsBombObserver(this);
        gameLoop.initiateLoop();

        setGlobalVariables();
        setMazeLayers();
        x = y = 0;
    }


    private void setGlobalVariables() {

        gcD = dynamicCanvas.getGraphicsContext2D();
        gcAnimation = animCanvas.getGraphicsContext2D();
        gcM = mazeCanvas.getGraphicsContext2D();
        gcS = steadyCanvas.getGraphicsContext2D();

        //to be mofified to no weapon (probably remove it)
        weaponImage.setImage(MazeMap.getInstance().getBufferedImage("Trap"));

        healthBar.setProgress(1.0);
        healthBar.setStyle("-fx-accent: red;");
        armorBar.setProgress(0.0);
        armorBar.setStyle("-fx-accent: green;");
        setTrials();
        coins.setText("0");
        bullets.setText("0");
        Point pt = gameLoop.getMazeDimensions();
        shiftDown += ((dynamicCanvas.getHeight() - shiftDown) % (pt.getX())) / 2;
        cellHeight = (int) ((dynamicCanvas.getHeight() - shiftDown) / (pt.getX()));
        cellWidth = (int) ((dynamicCanvas.getWidth()) / (pt.getY()));
    }

    private void setMazeLayers() {
        setMovingObjectsLayer();
        setPickablesLayer();
        setRoadAndWallsLayer();
    }

    private void setMovingObjectsLayer() {
        Drawable[][] maze = gameLoop.getMovingObjectsLayer();
        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[i].length; j++) {
                if (maze[j][i] != null) {
                    maze[j][i].drawOnCanvas(gcD, new Point(
                                    (int) (maze[j][i].getPosition().getX() * cellWidth),
                                    (int) (maze[j][i].getPosition().getY() * cellHeight + shiftDown)),
                            cellWidth, cellHeight);
                }
            }
        }
    }

    private void setPickablesLayer() {
        Drawable[][] maze = gameLoop.getPickablesLayer();
        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[i].length; j++) {
                if (maze[j][i] != null) {
                    maze[j][i].drawOnCanvas(gcM, new Point(
                                    (int) (maze[j][i].getPosition().getX() * cellWidth),
                                    (int) (maze[j][i].getPosition().getY() * cellHeight + shiftDown)),
                            cellWidth, cellHeight);
                }
            }
        }
    }

    private void setRoadAndWallsLayer() {
        Drawable[][] maze = gameLoop.getRoadAndWallsLayer();
        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[i].length; j++) {
                if (maze[j][i] != null && !(maze[j][i] instanceof Road)) {
                    maze[j][i].drawOnCanvas(gcS, new Point(
                                    (int) (maze[j][i].getPosition().getX() * cellWidth),
                                    (int) (maze[j][i].getPosition().getY() * cellHeight + shiftDown)),
                            cellWidth, cellHeight);
                }
            }
        }
    }

    @Override
    public void updateMonsterPosition(Point oldPos, Monster monster) {
        //need to perform atransition
        gcD.clearRect(oldPos.getX() * cellWidth, oldPos.getY() * cellHeight + shiftDown, cellWidth, cellHeight);
        monster.drawOnCanvas(gcD, new Point((int) (monster.getPosition().getX() * cellWidth),
                        (int) (monster.getPosition().getY() * cellHeight + shiftDown)),
                cellWidth, cellHeight);
        //            heroOrMonster.getDownSprite().reset();
//            int i = (int)heroOrMonster.getPosition().getY()-heightCell;
//            while (i < heroOrMonster.getPosition().getY()) {
//                gc.clearRect(heroOrMonster.getPosition().getX(), i, widthCell, heightCell);
//                i++;
//                heroOrMonster.getDownSprite().drawNextSprite(gc, widthCell, heightCell, (int)heroOrMonster.getPosition().getX(), i);
//            }
    }

    @Override
    public void updateRoadsAndWalls(Point position) {
        gcS.clearRect(position.getX() * cellWidth, position.getY() * cellHeight + shiftDown, cellWidth, cellHeight);
    }

    @Override
    public void updatePickables(Point position) {
        gcM.clearRect(position.getX() * cellWidth, position.getY() * cellHeight + shiftDown, cellWidth, cellHeight);
    }

    @Override
    public void drawExplosionAnimation(Point position, int range) {
        ExplosionAnimation expl = new ExplosionAnimation(position);
        expl.startAnimation(gcAnimation, cellWidth, cellHeight, range);
    }

    @Override
    public void updateChangeInHealth(double healthPercentageLeft) {
        healthBar.setProgress(healthPercentageLeft);
    }

    @Override
    public void updateChangeInArmorPoints(double ArmorPercentageLeft) { //TODO
        this.armorBar.setProgress(ArmorPercentageLeft);
    }

    @Override
    public void updateCoins(int Coins) { //TODO
        //set default background image
        this.coins.setText(""+Coins);
    }

    @Override
    public void updateNumberOfBullets(int Bullets) { //TODO
        System.out.println("got bullets");
        this.bullets.setText(""+Bullets);
    }

    @Override
    public void updateCurrentWeapon(Weapon weapon) {
        String wep = weapon.getClass().getSimpleName();
        weaponImage.setImage(MazeMap.getInstance().getBufferedImage(wep));
    }
    //TODO setTrials
    private void setTrials() {
        trials.setText(String.valueOf(hero.getTrials()));
//        trials.setBackground(new Image("Bricks.jpg"));
    }
}