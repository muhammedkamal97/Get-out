package View;

import View.Graphics.ImagesMaps.MazeMap;
import drawables.Drawable;
import drawables.characters.Hero;
import drawables.characters.Monster;
import View.Graphics.ExplosionAnimation;
import drawables.pickables.Weapon;
import drawables.pickables.weapons.NormalGun;
import drawables.pickables.weapons.bullets.Bullet;
import drawables.roads.Road;
import gameCore.RunnerGameAdapter;
import gameLoop.GameLoop;
import javafx.animation.AnimationTimer;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.PerspectiveCamera;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import observer.BombExplosionObserver;
import observer.BulletMotionObserver;
import observer.EndOfGameObserver;
import observer.HeroStateObserver;
import observer.MazeLayersObserver;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class CanvasController implements MazeLayersObserver,
        BombExplosionObserver, HeroStateObserver,
        EndOfGameObserver, BulletMotionObserver {

    @FXML
    private Button Menu;
    @FXML
    private Canvas dynamicCanvas;
    @FXML
    private Canvas dummyCanvas;
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
    @FXML
    private Label scoreLabel;
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
    private int maxLvl;
    private PerspectiveCamera camera;
    private ShapeIntersectionDetector intersectionDetector;
    private boolean cameraIsOn;
    private boolean soundIsOn;
    private boolean musicIsOn;

    /**
     * dummy values to update hero's postion don't modify them!!!!
     */
    private double x, y;

    //TODO memento when he dies return him to the last check point
    //TODO get maximum lvl to prevent it & display credits  @Gamal
    //TODO decorator @Abdelrahman
    //TODO construct classes
    //TODO check bullets directions
    //TODO check comments and TODOs in code
    //TODO Direction states in bullets to be modified
    //TODO check animation
    //TODO hero menu modifications

    @FXML
    protected void MenuButtonAction(ActionEvent event) {
        gameLoop.closeGame();
        Stage stage = (Stage) Menu.getScene().getWindow();
        stage.close();

        try {
            new MenuScene().startView();
        } catch (Exception e) {
            //error Logger
            throw new RuntimeException("Could not open main menu");
        }
    }

    @FXML
    public void keyPressedSwitch(KeyEvent event) {
        switch (event.getCode()) {
            case LEFT:
            case KP_LEFT:
                x -= cellWidth / 4.0;
                if (!checkForWallsLeft()) {
                    if (x == (-cellWidth)) {
                        gameLoop.moveHeroLeft();
                        if (!hero.getPosition().equals(currentPosition)) {
                            x += cellWidth;
                        } else {
                            x += cellWidth / 4.0;
                        }
                    }
                } else {
                    x += cellWidth / 4.0;
                }
                gameLoop.lookLeft();
                currentPosition = hero.getPosition();
                gcH.clearRect(0, 0, heroCanvas.getWidth(), heroCanvas.getHeight());
                hero.drawOnCanvas(gcH, new Point((int) (currentPosition.getX() * cellWidth + x),
                                (int) (currentPosition.getY() * cellHeight + y + shiftDown)),
                        cellWidth, cellHeight);
                setHeroCamera();
                break;
            case RIGHT:
            case KP_RIGHT:
                x += cellWidth / 4.0;
                if (!checkForWallsRight()) {

                    if (x == cellWidth) {
                        gameLoop.moveHeroRight();
                        if (!hero.getPosition().equals(currentPosition)) {
                            x -= cellWidth;
                        } else {
                            x -= cellWidth / 4.0;
                        }
                    }
                } else {
                    x -= cellWidth / 4.0;
                }
                gameLoop.lookRight();
                currentPosition = hero.getPosition();
                gcH.clearRect(0, 0, heroCanvas.getWidth(), heroCanvas.getHeight());
                hero.drawOnCanvas(gcH, new Point((int) (currentPosition.getX() * cellWidth + x),
                                (int) (currentPosition.getY() * cellHeight + y + shiftDown)),
                        cellWidth, cellHeight);
                setHeroCamera();
                break;
            case UP:
            case KP_UP:
                y -= cellHeight / 4.0;
                if (!checkForWallsUp()) {

                    if (y == -(cellHeight)) {
                        gameLoop.moveHeroUp();
                        if (!hero.getPosition().equals(currentPosition)) {
                            y += cellHeight;
                        } else {
                            y += cellHeight / 4.0;
                        }
                    }
                } else {
                    y += cellHeight / 4.0;
                }
                gameLoop.lookUp();
                currentPosition = hero.getPosition();
                gcH.clearRect(0, 0, heroCanvas.getWidth(), heroCanvas.getHeight());
                hero.drawOnCanvas(gcH, new Point((int) (currentPosition.getX() * cellWidth + x),
                                (int) (currentPosition.getY() * cellHeight + y + shiftDown))
                        , cellWidth, cellHeight);
                setHeroCamera();
                break;
            case DOWN:
            case KP_DOWN:
                y += cellHeight / 4.0;
                if (!checkForWallsDown()) {

                    if (y == cellHeight) {
                        gameLoop.moveHeroDown();
                        if (!hero.getPosition().equals(currentPosition)) {
                            y -= cellHeight;
                        } else {
                            y -= cellHeight / 4.0;
                        }
                    }
                } else {
                    y -= cellHeight / 4.0;
                }
                gameLoop.lookDown();
                currentPosition = hero.getPosition();
                gcH.clearRect(0, 0, heroCanvas.getWidth(), heroCanvas.getHeight());
                hero.drawOnCanvas(gcH, new Point((int) (currentPosition.getX() * cellWidth + x),
                        (int) (currentPosition.getY() * cellHeight + y + shiftDown)), cellWidth, cellHeight);
                setHeroCamera();
                break;
            default:
                break;
        }

    }

    private boolean checkForWallsLeft() {
        if (!intersectionDetector.intersects(wallMaze[hero.getPosition().y][hero.getPosition().x - 1],
                new Point((int) (currentPosition.getX() * cellWidth + x),
                        (int) (currentPosition.getY() * cellHeight + y)), cellWidth, cellHeight, -1, 0)) {
            if (!intersectionDetector.intersects(wallMaze[hero.getPosition().y - 1][hero.getPosition().x - 1],
                    new Point((int) (currentPosition.getX() * cellWidth + x),
                            (int) (currentPosition.getY() * cellHeight + y)), cellWidth, cellHeight, 0, -1)) {
                if (!intersectionDetector.intersects(wallMaze[hero.getPosition().y + 1][hero.getPosition().x - 1],
                        new Point((int) (currentPosition.getX() * cellWidth + x),
                                (int) (currentPosition.getY() * cellHeight + y)), cellWidth, cellHeight, 0, 1)) {
                    return false;
                }
            }

        }
        return true;
    }

    private boolean checkForWallsRight() {
        if (!intersectionDetector.intersects(wallMaze[hero.getPosition().y][hero.getPosition().x + 1],
                new Point((int) (currentPosition.getX() * cellWidth + x),
                        (int) (currentPosition.getY() * cellHeight + y)), cellWidth, cellHeight, 1, 0)) {
            if (!intersectionDetector.intersects(wallMaze[hero.getPosition().y - 1][hero.getPosition().x + 1],
                    new Point((int) (currentPosition.getX() * cellWidth + x),
                            (int) (currentPosition.getY() * cellHeight + y)), cellWidth, cellHeight, 0, -1)) {
                if (!intersectionDetector.intersects(wallMaze[hero.getPosition().y + 1][hero.getPosition().x + 1],
                        new Point((int) (currentPosition.getX() * cellWidth + x),
                                (int) (currentPosition.getY() * cellHeight + y)), cellWidth, cellHeight, 0, 1)) {
                    return false;
                }
            }

        }
        return true;
    }

    private boolean checkForWallsDown() {
        if (!intersectionDetector.intersects(wallMaze[hero.getPosition().y + 1][hero.getPosition().x - 1],
                new Point((int) (currentPosition.getX() * cellWidth + x),
                        (int) (currentPosition.getY() * cellHeight + y)), cellWidth, cellHeight, -1, 0)) {
            if (!intersectionDetector.intersects(wallMaze[hero.getPosition().y + 1][hero.getPosition().x],
                    new Point((int) (currentPosition.getX() * cellWidth + x),
                            (int) (currentPosition.getY() * cellHeight + y)), cellWidth, cellHeight, 0, 1)) {
                if (!intersectionDetector.intersects(wallMaze[hero.getPosition().y + 1][hero.getPosition().x + 1],
                        new Point((int) (currentPosition.getX() * cellWidth + x),
                                (int) (currentPosition.getY() * cellHeight + y)), cellWidth, cellHeight, 1, 0)) {
                    return false;
                }
            }

        }
        return true;
    }

    private boolean checkForWallsUp() {
        if (!intersectionDetector.intersects(wallMaze[hero.getPosition().y - 1][hero.getPosition().x],
                new Point((int) (currentPosition.getX() * cellWidth + x),
                        (int) (currentPosition.getY() * cellHeight + y)), cellWidth, cellHeight, 0, -1)) {
            if (!intersectionDetector.intersects(wallMaze[hero.getPosition().y - 1][hero.getPosition().x - 1],
                    new Point((int) (currentPosition.getX() * cellWidth + x),
                            (int) (currentPosition.getY() * cellHeight + y)), cellWidth, cellHeight, -1, 0)) {
                if (!intersectionDetector.intersects(wallMaze[hero.getPosition().y - 1][hero.getPosition().x + 1],
                        new Point((int) (currentPosition.getX() * cellWidth + x),
                                (int) (currentPosition.getY() * cellHeight + y)), cellWidth, cellHeight, 1, 0)) {
                    return false;
                }
            }

        }
        return true;
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
            case E:
                gameLoop.holdNextWeapon();
                break;
            case Q:
                gameLoop.holdPreviousWeapon();
                break;
            case B:
                camera.setTranslateZ(camera.getTranslateZ() + 500);
                break;
            case N:
                camera.setTranslateZ(camera.getTranslateZ() - 500);
                break;
            case V:
                cameraIsOn = !cameraIsOn;
                if (!cameraIsOn) {
                    setCameraPosition();
                }
                break;
            case T:
                soundIsOn = !soundIsOn;
                if (!soundIsOn) {
                    //TODO set sound
                }
                break;
            case R:
                musicIsOn = !musicIsOn;
                if (!musicIsOn) {
                    //TODO set music
                }
                break;
            default:
                break;
        }
    }

    public void initLogin(Class<? extends Hero> hero, int lvl, int maxLvl, PerspectiveCamera camera) {
        this.intersectionDetector = new ShapeIntersectionDetector();
        this.maxLvl = maxLvl;
        this.level = lvl;
        this.classHero = hero;
        this.camera = camera;
        setCameraPosition();

        trials = 6; //TODO to be  set to hero's
        setImgLabls();
        this.game = RunnerGameAdapter.getInstance();
        gcRoad = roadCanvas.getGraphicsContext2D();
//        shaklo we7sh
//        gcRoad.drawImage(new Image("GrassRoad.jpg"), 0, 0, roadCanvas.getWidth(), roadCanvas.getHeight());
        gcD = dynamicCanvas.getGraphicsContext2D();
        gcH = heroCanvas.getGraphicsContext2D();
        gcAnimation = animCanvas.getGraphicsContext2D();
        gcM = mazeCanvas.getGraphicsContext2D();
        gcS = steadyCanvas.getGraphicsContext2D();
        gcBullets = bulletCanvas.getGraphicsContext2D();

        startGame(hero, lvl);

    }

    private void setCameraPosition () {
        camera.setTranslateZ(-1975); // zoom in and out
        camera.setTranslateX(dummyCanvas.getWidth()/2);
        camera.setTranslateY(dummyCanvas.getHeight()/2);
    }

    private void setHeroCamera () {
        if (cameraIsOn) {
            camera.setTranslateZ(-1500); // zoom in and out
            if (currentPosition.x * cellWidth < 706) {
                camera.setTranslateX(706);
            } else if (currentPosition.x * cellWidth > (dummyCanvas.getWidth() - 706)) {
                camera.setTranslateX(dummyCanvas.getWidth() - 706);
            } else {
                camera.setTranslateX(currentPosition.x * cellWidth + x);
            }
            if (currentPosition.y * cellHeight < 400) {
                camera.setTranslateY(400);
            } else if (currentPosition.y * cellHeight > (dummyCanvas.getHeight() - 400)) {
                camera.setTranslateY(dummyCanvas.getHeight() - 400);
            } else {
                camera.setTranslateY(currentPosition.y* cellHeight + y);
            }
        }
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
        gameLoop.registerAsHeroStateObserver(this);
        currentPosition = new Point(1, 1);
        updateBar();
        setGlobalVariables();
        setMazeLayers();
        x = y = 0;
        gameLoop.initiateLoop();
    }

    private void updateBar() {
        this.updateChangeInHealth(100);
        this.updateChangeInArmorPoints(0);
        this.updateCurrentWeapon(new NormalGun());
        this.updateNumberOfBullets(6);
    }

    private Point mazeDimensions;

    private void setGlobalVariables() {

        healthBar.setProgress(1.0);
        healthBar.setStyle("-fx-accent: red;");
        armorBar.setProgress(0.0);
        armorBar.setStyle("-fx-accent: green;");
        setTrials(trials);
        coins.setText("0");
        mazeDimensions = gameLoop.getMazeDimensions();
        shiftDown += ((dynamicCanvas.getHeight() - shiftDown) % (mazeDimensions.getX())) / 2;
        cellHeight = (int) ((dynamicCanvas.getHeight() - shiftDown) / (mazeDimensions.getX()));
        cellWidth = (int) ((dynamicCanvas.getWidth()) / (mazeDimensions.getY()));
    }

    private void setMazeLayers() {
        setMovingObjectsLayer();
        setPickablesLayer();
        setRoadAndWallsLayer();
    }

    private void setMovingObjectsLayer() {
        Drawable[][] maze = gameLoop.getMovingObjectsLayer();
        gcD.clearRect(0, 0, dynamicCanvas.getWidth(), dynamicCanvas.getHeight());
        gcH.clearRect(0, 0, heroCanvas.getWidth(), heroCanvas.getHeight());

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
        gcM.clearRect(0, 0, mazeCanvas.getWidth(), mazeCanvas.getHeight());
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
        gcS.clearRect(0, 0, steadyCanvas.getWidth(), steadyCanvas.getHeight());
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
        gcD.clearRect(oldPos.getX() * cellWidth, oldPos.getY() * cellHeight + shiftDown, cellWidth, cellHeight);
        if (monster != null)
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

        increaseScore(10);
    }

    private void increaseScore(int value) {
        String valueText = this.scoreLabel.getText();
        int valueInt = Integer.parseInt(valueText);
        valueInt += value;
        this.scoreLabel.setText(Integer.toString(valueInt));
    }

    @Override
    public void updateRoadsAndWalls(Point position) {
        gcS.clearRect(position.getX() * cellWidth, position.getY() * cellHeight + shiftDown, cellWidth, cellHeight);
    }

    @Override
    public void updatePickables(Point position) {
        gcM.clearRect(position.getX() * cellWidth, position.getY() * cellHeight + shiftDown, cellWidth, cellHeight);
        increaseScore(3);
    }

    @Override
    public void drawExplosionAnimation(Point position, int range) {
        ExplosionAnimation expl = new ExplosionAnimation(position);
        expl.startAnimation(gcAnimation, cellWidth, cellHeight, range);
        increaseScore(5);
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
    public void updateCoins(int Coins) {
        this.coins.setText("" + Coins);
    }

    @Override
    public void updateNumberOfBullets(int Bullets) {
        this.bullets.setText("" + Bullets);
    }

    @Override
    public void updateCurrentWeapon(Weapon weapon) {
        if (weapon != null) {
            String wep = weapon.getClass().getSimpleName();
            weaponImage.setImage(MazeMap.getInstance().getBufferedImage(wep));
        } else {
            weaponImage.setImage(new Image("null.png"));
        }
    }

    private void setImgLabls() {
        ImageView img = new ImageView("heart.png");
        img.setFitHeight(30);
        img.setFitWidth(30);
        trialsLabl.setGraphic(img);
        img = new ImageView("bullet2.png");
        img.setFitHeight(30);
        img.setFitWidth(30);
        bullets.setGraphic(img);
        img = new ImageView("coin.png");
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
        System.out.println("You Win!!!!!!!");
        this.level++;
        gcAnimation.clearRect(0, 0, animCanvas.getWidth(), animCanvas.getHeight());
        gcBullets.clearRect(0, 0, bulletCanvas.getWidth(), bulletCanvas.getHeight());
//        animForWinOrLose();

        increaseScore(30);
        if(this.level <= this.maxLvl)
            startGame(classHero, level);
        else
        {
            ((Stage)this.Menu.getScene().getWindow()).close();
            try {
                new CreditsScene().startView();
            } catch (Exception e) {
                throw new RuntimeException("Could not launch credit");
            }
        }
    }

    @Override
    public void updateOnLose() {
        System.out.println("Game Over!!!!!!!");
        setTrials(--trials);
        gcAnimation.clearRect(0, 0, animCanvas.getWidth(), animCanvas.getHeight());
        gcBullets.clearRect(0, 0, bulletCanvas.getWidth(), bulletCanvas.getHeight());
//        animOnWin();

        increaseScore(-20);

        if(this.trials > 0)
            startGame(classHero, level);
        else
        {
            gameLoop.closeGame();
            Stage stage = (Stage) Menu.getScene().getWindow();
            stage.close();

            try {
                new MenuScene().startView();
            } catch (Exception e) {
                throw new RuntimeException("Failed to load menu");
            }
        }
    }

    private void animOnWin() {
        ArrayList<BufferedImage> listblue = new ArrayList<>();
        ArrayList<BufferedImage> listgreen = new ArrayList<>();
        ArrayList<BufferedImage> listred = new ArrayList<>();
        Image img = new Image("Fireworks.png");
        BufferedImage buffImg = SwingFXUtils.fromFXImage(img, null);
        int TileW = (int) img.getWidth() / 3;
        int TileH = (int) img.getHeight() / 6;
        for (int j = 0; j < 6; j++) {
            listblue.add(buffImg.getSubimage(0, j * TileH, TileW, TileH));
        }
        for (int j = 0; j < 6; j++) {
            listred.add(buffImg.getSubimage(1 * TileW, j * TileH, TileW, TileH));
        }
        for (int j = 0; j < 6; j++) {
            listgreen.add(buffImg.getSubimage(2 * TileW, j * TileH, TileW, TileH));
        }

        new AnimationTimer() {
            long start = System.currentTimeMillis();
            int count = 0;
            //TODO fix Win/gameOver animation
            public void handle(long currentNanoTime) {
                long current = System.currentTimeMillis();
                if (current - start >= 400) {
                    gcAnimation.clearRect(0, 0,
                            animCanvas.getWidth(), animCanvas.getHeight());
                    start = System.currentTimeMillis();
                    Image imgTo = SwingFXUtils.toFXImage(listblue.get(count), null);
                    gcAnimation.drawImage(imgTo, 640, 160,
                            400, 400);
                    imgTo = SwingFXUtils.toFXImage(listgreen.get(count), null);
                    gcAnimation.drawImage(imgTo, 140, 80,
                            400, 400);
                    imgTo = SwingFXUtils.toFXImage(listred.get(count), null);
                    gcAnimation.drawImage(imgTo, 200, 50,
                            400, 400);
                    count++;
                }
                if (count == listblue.size()) {
                    startGame(classHero, level);
                    this.stop();
                }
            }
        }.start();
    }

    @Override
    public void updateBulletMotionObserver(Bullet bullet, Point pastPosition, Point currentPosition, boolean destroyed) {
        this.gcBullets.clearRect(pastPosition.x * cellWidth, pastPosition.y * cellHeight + shiftDown, cellWidth, cellHeight);
        bullet.drawOnCanvas(this.gcBullets,currentPosition.x * cellWidth,currentPosition.y* cellHeight + shiftDown,cellWidth, cellHeight, hero.getDirectionState());
        if (destroyed)
            this.gcBullets.clearRect(currentPosition.x * cellWidth, (currentPosition.y) * cellHeight + shiftDown, cellWidth, cellHeight);
    }

}