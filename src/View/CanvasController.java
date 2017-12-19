package View;

import View.Graphics.ImagesMaps.CharactersMap;
import View.Graphics.ImagesMaps.MazeMap;
import drawables.Drawable;
import drawables.characters.Hero;
import drawables.characters.Monster;
import View.Graphics.ExplosionAnimation;
import drawables.pickables.Weapon;
import drawables.pickables.weapons.NormalGun;
import drawables.roads.Road;
import gameCore.RunnerGameAdapter;
import gameLoop.GameLoop;
import javafx.animation.AnimationTimer;
import javafx.embed.swing.SwingFXUtils;
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
    private ShapeIntersectionDetector intersectionDetector;

    /**
     * dummy values to update hero's postion don't modify them!!!!
     */
    private double x, y;

    //TODO draw bullets image
    //TODO modify stylesheet
    //TODO camera
    //TODO memento
    //TODO Win/gameOver animation
    //TODO teleport transition animation
    //TODO change sprites for bomb explosions
    //TODO Traps animation
    //TODO get maximum lvl to prevent it & display credits
    //TODO shield update armor points

    @FXML
    protected void MenuButtonAction(ActionEvent event) {
        gameLoop.closeGame();

        Stage stage = (Stage) Menu.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void keyPressedSwitch(KeyEvent event) {
        switch (event.getCode()) {
            case LEFT:
            case KP_LEFT:
                x -= cellWidth/4.0;
                if (!checkForWallsLeft()) {
                    if (x == (-cellWidth)) {
                        gameLoop.moveHeroLeft();
                        if (!hero.getPosition().equals(currentPosition)) {
                            x += cellWidth;
                        } else {
                            x += cellWidth/4.0;
                        }
                    }
                } else {
                    x += cellWidth/4.0;
                }
                gameLoop.lookLeft();
                currentPosition = hero.getPosition();
                gcH.clearRect(0, 0, heroCanvas.getWidth(), heroCanvas.getHeight());
                hero.drawOnCanvas(gcH, new Point((int) (currentPosition.getX() * cellWidth + x),
                                (int) (currentPosition.getY() * cellHeight + y + shiftDown)),
                        cellWidth, cellHeight);
                break;
            case RIGHT:
            case KP_RIGHT:
                x += cellWidth/4.0;
                if (!checkForWallsRight()) {

                    if (x == cellWidth) {
                        gameLoop.moveHeroRight();
                        if (!hero.getPosition().equals(currentPosition)) {
                            x -= cellWidth;
                        } else {
                            x -= cellWidth/4.0;
                        }
                    }
                }else {
                    x -= cellWidth/4.0;
                }
                gameLoop.lookRight();
                currentPosition = hero.getPosition();
                gcH.clearRect(0, 0, heroCanvas.getWidth(), heroCanvas.getHeight());
                hero.drawOnCanvas(gcH, new Point((int) (currentPosition.getX() * cellWidth + x),
                                (int) (currentPosition.getY() * cellHeight + y + shiftDown)),
                        cellWidth, cellHeight);
                break;
            case UP:
            case KP_UP:
                y -= cellHeight/4.0;
                if (!checkForWallsUp()) {

                    if (y == -(cellHeight)) {
                        gameLoop.moveHeroUp();
                        if (!hero.getPosition().equals(currentPosition)) {
                            y += cellHeight;
                        } else {
                            y += cellHeight/4.0;
                        }
                    }
                }else {
                    y += cellHeight/4.0;
                }
                gameLoop.lookUp();
                currentPosition = hero.getPosition();
                gcH.clearRect(0, 0, heroCanvas.getWidth(), heroCanvas.getHeight());
                hero.drawOnCanvas(gcH, new Point((int) (currentPosition.getX() * cellWidth + x),
                                (int) (currentPosition.getY() * cellHeight + y + shiftDown))
                        , cellWidth, cellHeight);
                break;
            case DOWN:
            case KP_DOWN:
                y += cellHeight/4.0;
                if (!checkForWallsDown()) {

                    if (y == cellHeight) {
                        gameLoop.moveHeroDown();
                        if (!hero.getPosition().equals(currentPosition)) {
                            y -= cellHeight;
                        } else {
                            y -= cellHeight/4.0;
                        }
                    }
                }else {
                    y -= cellHeight/4.0;
                }
                gameLoop.lookDown();
                currentPosition = hero.getPosition();
                gcH.clearRect(0, 0, heroCanvas.getWidth(), heroCanvas.getHeight());
                hero.drawOnCanvas(gcH, new Point((int) (currentPosition.getX() * cellWidth + x),
                        (int) (currentPosition.getY() * cellHeight + y + shiftDown)), cellWidth, cellHeight);
                break;
            default:
                break;
        }

    }

    private boolean checkForWallsLeft() {
        if (!intersectionDetector.intersects(wallMaze[hero.getPosition().y][hero.getPosition().x - 1],
                new Point((int) (currentPosition.getX() * cellWidth + x),
                        (int) (currentPosition.getY() * cellHeight + y)), cellWidth, cellHeight , -1 ,0)) {
            if (!intersectionDetector.intersects(wallMaze[hero.getPosition().y - 1][hero.getPosition().x - 1],
                    new Point((int) (currentPosition.getX() * cellWidth + x),
                            (int) (currentPosition.getY() * cellHeight + y)), cellWidth, cellHeight , 0,-1)) {
                if (!intersectionDetector.intersects(wallMaze[hero.getPosition().y + 1][hero.getPosition().x - 1],
                        new Point((int) (currentPosition.getX() * cellWidth + x),
                                (int) (currentPosition.getY() * cellHeight + y)), cellWidth, cellHeight,0,1)) {
                    return false;
                }
            }

        }
        return true;
    }

    private boolean checkForWallsRight() {
        if (!intersectionDetector.intersects(wallMaze[hero.getPosition().y][hero.getPosition().x + 1],
                new Point((int) (currentPosition.getX() * cellWidth + x),
                        (int) (currentPosition.getY() * cellHeight + y)), cellWidth, cellHeight,1,0)) {
            if (!intersectionDetector.intersects(wallMaze[hero.getPosition().y - 1][hero.getPosition().x + 1],
                    new Point((int) (currentPosition.getX() * cellWidth + x),
                            (int) (currentPosition.getY() * cellHeight + y)), cellWidth, cellHeight,0,-1)) {
                if (!intersectionDetector.intersects(wallMaze[hero.getPosition().y + 1][hero.getPosition().x + 1],
                        new Point((int) (currentPosition.getX() * cellWidth + x),
                                (int) (currentPosition.getY() * cellHeight + y)), cellWidth, cellHeight,0,1)) {
                    return false;
                }
            }

        }
        return true;
    }
    private boolean checkForWallsDown() {
        if (!intersectionDetector.intersects(wallMaze[hero.getPosition().y + 1][hero.getPosition().x - 1],
                new Point((int) (currentPosition.getX() * cellWidth + x),
                        (int) (currentPosition.getY() * cellHeight + y)), cellWidth, cellHeight,-1,0)) {
            if (!intersectionDetector.intersects(wallMaze[hero.getPosition().y + 1][hero.getPosition().x],
                    new Point((int) (currentPosition.getX() * cellWidth + x),
                            (int) (currentPosition.getY() * cellHeight + y)), cellWidth, cellHeight,0,1)) {
                if (!intersectionDetector.intersects(wallMaze[hero.getPosition().y + 1][hero.getPosition().x + 1],
                        new Point((int) (currentPosition.getX() * cellWidth + x),
                                (int) (currentPosition.getY() * cellHeight + y)), cellWidth, cellHeight,1,0)) {
                    return false;
                }
            }

        }
        return true;
    }
    private boolean checkForWallsUp() {
        if (!intersectionDetector.intersects(wallMaze[hero.getPosition().y - 1][hero.getPosition().x],
                new Point((int) (currentPosition.getX() * cellWidth + x),
                        (int) (currentPosition.getY() * cellHeight + y)), cellWidth, cellHeight,0,-1)) {
            if (!intersectionDetector.intersects(wallMaze[hero.getPosition().y - 1][hero.getPosition().x - 1],
                    new Point((int) (currentPosition.getX() * cellWidth + x),
                            (int) (currentPosition.getY() * cellHeight + y)), cellWidth, cellHeight,-1,0)) {
                if (!intersectionDetector.intersects(wallMaze[hero.getPosition().y - 1][hero.getPosition().x + 1],
                        new Point((int) (currentPosition.getX() * cellWidth + x),
                                (int) (currentPosition.getY() * cellHeight + y)), cellWidth, cellHeight,1,0)) {
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
        this.intersectionDetector = new ShapeIntersectionDetector();
        this.classHero = hero;

        trials = 100; //TODO to be  set to hero's
        setImgLabls();
        this.game = new RunnerGameAdapter();
        gcRoad = roadCanvas.getGraphicsContext2D();
        //shaklo we7sh
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
        gameLoop.registerAsHeroStateObserver(this);
        currentPosition = new Point(1,1);
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
    Point mazeDimensions;
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
    }

    @Override
    public void updateRoadsAndWalls(Point position) {
        gcS.clearRect(position.getX() * cellWidth, position.getY() * cellHeight + shiftDown, cellWidth, cellHeight);
    }

    @Override
    public void updatePickables(Point position) {
        gcH.clearRect(0, 0, heroCanvas.getWidth(), heroCanvas.getHeight()); // if it is a trap
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
    public void updateCoins(int Coins) {
        this.coins.setText("" + Coins);
    }

    @Override
    public void updateNumberOfBullets(int Bullets) { //TODO
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
        System.out.println("You Win!!!!!!!");
        this.level++;
        gcAnimation.clearRect(0, 0, animCanvas.getWidth(), animCanvas.getHeight());
        gcBullets.clearRect(0, 0, bulletCanvas.getWidth(), bulletCanvas.getHeight());
//        animForWinOrLose();
        startGame(classHero, level);
    }

    @Override
    public void updateOnLose() {
        System.out.println("Game Over!!!!!!!");
        setTrials(--trials);
        gcAnimation.clearRect(0, 0, animCanvas.getWidth(), animCanvas.getHeight());
        gcBullets.clearRect(0, 0, bulletCanvas.getWidth(), bulletCanvas.getHeight());
//        animForWinOrLose();
        startGame(classHero, level);
//       TODO in start scene story mode w select level (get level number)
    }

    private void animForWinOrLose() {
        ArrayList<BufferedImage> listblue = new ArrayList<>();
        ArrayList<BufferedImage> listgreen = new ArrayList<>();
        ArrayList<BufferedImage> listred = new ArrayList<>();
        Image img = new Image("Fireworks.png");
        BufferedImage buffImg = SwingFXUtils.fromFXImage(img, null);
        int TileW = (int) img.getWidth() / 3;
        int TileH = (int) img.getHeight() / 6;
        for (int j = 0; j < 6; j++) {
            listblue.add(buffImg.getSubimage(0 * TileW, j * TileH, TileW, TileH));
        }
        for (int j = 0; j < 6; j++) {
            listred.add(buffImg.getSubimage(1 * TileW, j * TileH, TileW, TileH));
        }
        for (int j = 0; j < 6; j++) {
            listgreen.add(buffImg.getSubimage(2 * TileW, j * TileH, TileW, TileH));
        }
        boolean red = true;
        boolean blue = true;
        boolean green = true;
        animOnWin(new Point(40, 64), listblue, blue);
        animOnWin(new Point(260, 464), listgreen, green);
        animOnWin(new Point(160, 264), listred, red);
        int i = 0;
        while (blue || red || green) {
            i++;
        }
        startGame(classHero, level);
    }

    private void animOnWin(Point pt, ArrayList<BufferedImage> list, boolean flag) {
        new AnimationTimer() {
            long start = System.currentTimeMillis();
            int count = 0;

            public void handle(long currentNanoTime) {
                long current = System.currentTimeMillis();
                if (current - start >= 200) {
                    start = System.currentTimeMillis();
                    gcAnimation.clearRect(pt.x, pt.y,
                            400, 400);
                    Image imgTo = SwingFXUtils.toFXImage(list.get(count), null);
                    gcAnimation.drawImage(imgTo, pt.x, pt.y,
                           400, 400);
                    count++;
                }
                if (count == list.size()) {
                    gcAnimation.clearRect(0, 0,
                            animCanvas.getWidth()/4, animCanvas.getHeight());
                    this.stop();
                }
            }
        }.start();
    }

//    class StatusTimer extends AnimationTimer {
//
//        private volatile boolean running;
//
//        @Override
//        public void start() {
//            super.start();
//            running = true;
//        }
//
//        @Override
//        public void stop() {
//            super.stop();
//            running = false;
//        }
//
//        public boolean isRunning() {
//            return running;
//        }
//
//    ...
//
//    }

    @Override
    public void updateBulletMotionObserver(Point pastPosition, Point currentPosition, boolean destroyed) {
        this.gcBullets.setFill(Color.BLACK);
        this.gcBullets.clearRect(pastPosition.x * cellWidth, (pastPosition.y) * cellHeight + shiftDown, cellWidth, cellHeight);
        this.gcBullets.fillOval(currentPosition.x * cellWidth, (currentPosition.y) * cellHeight + shiftDown, cellWidth, cellHeight);
        if (destroyed)
            this.gcBullets.clearRect(currentPosition.x * cellWidth, (currentPosition.y) * cellHeight + shiftDown, cellWidth, cellHeight);
    }

}