package View;

import View.Graphics.ImagesMaps.MazeMap;
import drawables.Drawable;
import drawables.characters.Hero;
import drawables.characters.Monster;
import View.Graphics.ExplosionAnimation;
import drawables.characters.heros.Flash;
import drawables.characters.heros.NormalHero;
import drawables.pickables.Weapon;
import drawables.roads.Road;
import gameCore.RunnerGameAdapter;
import gameLoop.GameLoop;
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
import javafx.scene.paint.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import observer.BombExplosionObserver;
import observer.BulletMotionObserver;
import observer.EndOfGameObserver;
import observer.HeroStateObserver;
import observer.MazeLayersObserver;

import java.awt.*;
import java.util.Set;

public class CanvasController implements MazeLayersObserver, BombExplosionObserver, HeroStateObserver, EndOfGameObserver,BulletMotionObserver {

    @FXML
    private Button Menu;
    @FXML
    private Canvas dynamicCanvas;
    @FXML
    private Canvas roadCanvas;
    @FXML
    private Canvas heroCanvas;
    @FXML
    private Canvas steadyCanvas;
    @FXML
    private Canvas mazeCanvas;
    @FXML
    private Canvas animCanvas;
    @FXML
    private Canvas bulletCanvas;
    @FXML
    private ImageView weaponImage;
    @FXML
    private Label trialsLabl;
    @FXML
    private Label coins;
    @FXML
    private Label bullets;
    @FXML
    private ProgressBar healthBar;
    @FXML
    private ProgressBar armorBar;
    private GraphicsContext gcD;
    private GraphicsContext gcH;
    private GraphicsContext gcRoad;
    private GraphicsContext gcAnimation;
    private GraphicsContext gcM;
    private GraphicsContext gcS;
    private GraphicsContext gcBullets;
    private int shiftDown = 30;
    private int cellWidth;
    private int cellHeight;
    private int trials;
    private GameLoop gameLoop;
    private Hero hero;
    private Point currentPosition;
    private Drawable[][] wallMaze;
    private RunnerGameAdapter game;
    private Class<? extends Hero> classHero;
    private int level;

    //dummy values to update hero's postion don't modify them!!!!
    private int x, y;

    //TODO  Weapons Icons //set in map
    //TODO bullet will be an ellipse moving must have draw function
    //TODO modify stylesheet
    //TODO camera
    //TODO collision
    //TODO bullets observer
    //TODO memento
    //TODO Win/gameOver animation

    //TODO HERE shield bar //removed or added
    //TODO HERE score bar elly heya coins
    //TODO HERE armorpoints

    @FXML
    protected void MenuButtonAction(ActionEvent event) {

        Stage stage = (Stage) Menu.getScene().getWindow();
        stage.close();
        gameLoop.closeGame();
    }

    @FXML
    public void keyPressedSwitch(KeyEvent event) {
        switch (event.getCode()) {
            case LEFT:
            case KP_LEFT:
                x -= 10;
//                if (!checkForWallsLeft()) {
                    if (x < (-cellWidth / 6)) {
                        gameLoop.moveHeroLeft();
                        if (!hero.getPosition().equals(currentPosition)) {
                            x += cellWidth;
                        } else {
                            x += 10;
                        }
                    }
//                } else {
//                    x += 10;
//                }
                gameLoop.lookLeft();
                currentPosition = hero.getPosition();
                gcH.clearRect(0,0,heroCanvas.getWidth(), heroCanvas.getHeight());
                hero.drawOnCanvas(gcH, new Point((int) (currentPosition.getX() * cellWidth + x),
                                (int) (currentPosition.getY() * cellHeight + y + shiftDown)),
                        cellWidth, cellHeight);
                break;
            case RIGHT:
            case KP_RIGHT:
                x += 10;
                if (x > cellWidth / 6) {
                    gameLoop.moveHeroRight();
                    if (!hero.getPosition().equals(currentPosition)) {
                        x -= cellWidth;
                    } else {
                        x -= 10;
                    }
                }
                gameLoop.lookRight();
                currentPosition = hero.getPosition();
                gcH.clearRect(0,0,heroCanvas.getWidth(), heroCanvas.getHeight());
                hero.drawOnCanvas(gcH, new Point((int) (currentPosition.getX() * cellWidth + x),
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
                currentPosition = hero.getPosition();
                gcH.clearRect(0,0,heroCanvas.getWidth(), heroCanvas.getHeight());
                hero.drawOnCanvas(gcH, new Point((int) (currentPosition.getX() * cellWidth + x),
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
                currentPosition = hero.getPosition();
                gcH.clearRect(0,0,heroCanvas.getWidth(), heroCanvas.getHeight());
                hero.drawOnCanvas(gcH, new Point((int) (currentPosition.getX() * cellWidth + x),
                        (int) (currentPosition.getY() * cellHeight + y + shiftDown)), cellWidth, cellHeight);
                break;
            default:
                break;
        }

    }

    private boolean checkForWallsLeft() {
        if (!hero.intersects(wallMaze[hero.getPosition().y][hero.getPosition().x - 1],
                new Point((int) (currentPosition.getX() * cellWidth + x),
                        (int) (currentPosition.getY() * cellHeight + y)), cellWidth, cellHeight)) {
            if (!hero.intersects(wallMaze[hero.getPosition().y - 1][hero.getPosition().x - 1],
                    new Point((int) (currentPosition.getX() * cellWidth + x),
                            (int) (currentPosition.getY() * cellHeight + y)), cellWidth, cellHeight)) {
                if (!hero.intersects(wallMaze[hero.getPosition().y + 1][hero.getPosition().x - 1],
                        new Point((int) (currentPosition.getX() * cellWidth + x),
                                (int) (currentPosition.getY() * cellHeight + y)), cellWidth, cellHeight)) {
                    return true;
                }
            }

        }
        return false;
    }

    @FXML
    public void keyReleasedSwitch(KeyEvent event) {
        switch (event.getCode()) {
            case LEFT:
            case KP_LEFT:
                hero.drawOnReleased(gcH, new Point((int) (currentPosition.getX() * cellWidth + x),
                        (int) (currentPosition.getY() * cellHeight + y + shiftDown)), cellWidth, cellHeight);
                break;
            case RIGHT:
            case KP_RIGHT:
                hero.drawOnReleased(gcH, new Point((int) (currentPosition.getX() * cellWidth + x),
                        (int) (currentPosition.getY() * cellHeight + y + shiftDown)), cellWidth, cellHeight);
                break;
            case UP:
            case KP_UP:
                hero.drawOnReleased(gcH, new Point((int) (currentPosition.getX() * cellWidth + x),
                        (int) (currentPosition.getY() * cellHeight + y + shiftDown)), cellWidth, cellHeight);
                break;
            case DOWN:
            case KP_DOWN:
                hero.drawOnReleased(gcH, new Point((int) (currentPosition.getX() * cellWidth + x),
                        (int) (currentPosition.getY() * cellHeight + y + shiftDown)), cellWidth, cellHeight);
                break;
            case SPACE:
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

    public void initLogin(Class<? extends Hero> hero, int lvl) {
        this.classHero = hero;
        trials = 100; //TODO to be  set to hero's
        setImgLabls();
        this.game = new RunnerGameAdapter();
        gcRoad= roadCanvas.getGraphicsContext2D();
//        gcRoad.drawImage(new Image("GrassRoad.jpg"), 0, 0, roadCanvas.getWidth(), roadCanvas.getHeight());
        gcD = dynamicCanvas.getGraphicsContext2D();
        gcH = heroCanvas.getGraphicsContext2D();
        gcAnimation = animCanvas.getGraphicsContext2D();
        gcM = mazeCanvas.getGraphicsContext2D();
        gcS = steadyCanvas.getGraphicsContext2D();
        gcBullets = bulletCanvas.getGraphicsContext2D();

        startGame(hero, lvl);

    }

    private void startGame(Class<? extends Hero> hero, int lvl) {
        try {
            this.hero = hero.getConstructor().newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        game.InitializeMaze(lvl);
        gameLoop = game.getGameLoop();
        gameLoop.setHero(this.hero);
        gameLoop.registerEndGameObserver(this);
        gameLoop.registerAsMazeLayerObserver(this);
        gameLoop.registerAsBombObserver(this);
        gameLoop.registerAsBulletMotionObserver(this);

        setGlobalVariables();
        setMazeLayers();
        x = y = 0;
        gameLoop.initiateLoop();
    }

    private void setGlobalVariables() {

        healthBar.setProgress(1.0);
        healthBar.setStyle("-fx-accent: red;");
        armorBar.setProgress(0.0);
        armorBar.setStyle("-fx-accent: green;");
        setTrials(trials);
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
        gcD.clearRect(0,0,dynamicCanvas.getWidth(),dynamicCanvas.getHeight());
        gcH.clearRect(0,0,heroCanvas.getWidth(),heroCanvas.getHeight());

        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[i].length; j++) {
                if (maze[j][i] != null) {
                    if (maze[j][i] instanceof Hero) {
                        maze[j][i].drawOnCanvas(gcH, new Point(
                                        (int) (maze[j][i].getPosition().getX() * cellWidth),
                                        (int) (maze[j][i].getPosition().getY() * cellHeight + shiftDown)),
                                cellWidth, cellHeight);
                    } else {
                        maze[j][i].drawOnCanvas(gcD, new Point(
                                        (int) (maze[j][i].getPosition().getX() * cellWidth),
                                        (int) (maze[j][i].getPosition().getY() * cellHeight + shiftDown)),
                                cellWidth, cellHeight);
                    }
                }
            }
        }
    }

    private void setPickablesLayer() {
        Drawable[][] maze = gameLoop.getPickablesLayer();
        gcM.clearRect(0,0,mazeCanvas.getWidth(),mazeCanvas.getHeight());
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
        this.wallMaze = gameLoop.getRoadAndWallsLayer();
        gcS.clearRect(0,0,steadyCanvas.getWidth(),steadyCanvas.getHeight());
        for (int i = 0; i < wallMaze.length; i++) {
            for (int j = 0; j < wallMaze[i].length; j++) {
                if (wallMaze[j][i] != null && !(wallMaze[j][i] instanceof Road)) {
                    wallMaze[j][i].drawOnCanvas(gcS, new Point(
                                    (int) (wallMaze[j][i].getPosition().getX() * cellWidth),
                                    (int) (wallMaze[j][i].getPosition().getY() * cellHeight + shiftDown)),
                            cellWidth, cellHeight);
                }
            }
        }
    }

    @Override
    public void updateMonsterPosition(Point oldPos, Monster monster) {
        //need to perform atransition
        gcD.clearRect(oldPos.getX() * cellWidth, oldPos.getY() * cellHeight + shiftDown, cellWidth, cellHeight);
    if(monster!= null)
        monster.drawOnCanvas(gcD, new Point((int) (monster.getPosition().getX() * cellWidth),
                        (int) (monster.getPosition().getY() * cellHeight + shiftDown)),
                cellWidth, cellHeight);
//                    heroOrMonster.getDownSprite().reset();
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
        gcH.clearRect(0,0,heroCanvas.getWidth(), heroCanvas.getHeight()); // if it is a trap
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
        this.armorBar.setProgress(ArmorPercentageLeft); //TODO Observer not working guess(heroState)
    }

    @Override
    public void updateCoins(int Coins) {
        System.out.println("got Coins"); //TODO Observer not working guess(heroState)
        this.coins.setText("" + Coins);
    }

    @Override
    public void updateNumberOfBullets(int Bullets) { //TODO
        System.out.println("got bullets"); //TODO Observer not working guess(heroState)
        this.bullets.setText("" + Bullets);
    }

    @Override
    public void updateCurrentWeapon(Weapon weapon) {
        String wep = weapon.getClass().getSimpleName();
        weaponImage.setImage(MazeMap.getInstance().getBufferedImage(wep));
    }

    public void setImgLabls () {
        ImageView img = new ImageView("heart.png");
        img.setFitHeight(30);
        img.setFitWidth(30);
        trialsLabl.setGraphic(img);
        img = new ImageView("bullet2.png");
        img.setFitHeight(30);
        img.setFitWidth(30);
        bullets.setGraphic(img);
        img = new ImageView("Bricks.jpg");
        img.setFitHeight(30);
        img.setFitWidth(30);
        coins.setGraphic(img);
        weaponImage.setImage(new Image("null.png"));

    }
    private void setTrials(int trl) {
        trialsLabl.setText(String.valueOf(trl));
    }

    @Override
    public void updateOnWin() {
        //TODO play winner animation
        System.out.println("You Win!!!!!!!");
        this.level++;
        startGame(this.classHero, this.level);
        gcAnimation.clearRect(0,0,animCanvas.getWidth(), animCanvas.getHeight());
    }

    @Override
    public void updateOnLose() {
        //TODO play gameOver animation
        System.out.println("Game Over!!!!!!!");
        setTrials(--trials);
        startGame(this.classHero, this.level);
//       TODO in start scene story mode w select level (get level number)
        gcAnimation.clearRect(0,0,animCanvas.getWidth(), animCanvas.getHeight());

    }

    @Override
    public void updateBulletMotionObserver(Point pastPosition, Point currentPosition, boolean destroyed) {
        this.gcBullets.setFill(Color.BLACK);
        this.gcBullets.clearRect(pastPosition.x*cellWidth,(pastPosition.y + 1.5)*cellHeight + shiftDown,cellWidth,cellHeight);
        this.gcBullets.fillOval(currentPosition.x*cellWidth,(currentPosition.y + 1.5)*cellHeight + shiftDown,cellWidth,cellHeight);
        if(destroyed)
            this.gcBullets.clearRect(currentPosition.x*cellWidth,(currentPosition.y + 1.5)*cellHeight + shiftDown,cellWidth,cellHeight);
        //TODO clear bullet when monster dies
    }
}