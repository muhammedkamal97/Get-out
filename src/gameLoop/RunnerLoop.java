package gameLoop;

import drawables.Drawable;
import drawables.characters.Hero;
import drawables.characters.Monster;
import drawables.characters.commands.Command;
import drawables.characters.commands.motionCommands.MoveDownCommand;
import drawables.characters.commands.motionCommands.MoveLeftCommand;
import drawables.characters.commands.motionCommands.MoveRightCommand;
import drawables.characters.commands.motionCommands.MoveUpCommand;
import drawables.characters.heros.states.DirectionDownState;
import drawables.characters.heros.states.DirectionLeftState;
import drawables.characters.heros.states.DirectionRightState;
import drawables.characters.heros.states.DirectionUpState;
import drawables.obstacles.Bomb;
import gameLoop.monsterAI.MonsterThread;
import gameLoop.monsterAI.RandomMotion;
import maze.Maze;
import observer.BombExplosionObserver;
import observer.MazeLayersObserver;

import java.awt.*;
import java.util.ArrayList;

public class RunnerLoop implements GameLoop{

    private Maze maze;
    private Hero hero;
    private Command moveCommand;
    private int livesLeft = 3;


    @Override
    public void setLoopMaze(Maze maze) {
        this.maze = maze;
    }

    @Override
    public void setHero(Hero hero) {
        this.hero = hero;
        this.maze.setMazeHero(hero);
        hero.setMaze(maze);
        hero.registerDeathObserver(this);
    }

    @Override
    public void shoot() {
        hero.shoot();
    }

    @Override
    public void holdNextWeapon() {
        hero.holdNextWeapon();
    }

    @Override
    public void holdPreviousWeapon() {
        hero.holdPreviousWeapon();
    }

    @Override
    public void moveHeroUp() {
        moveCommand = new MoveUpCommand();
        hero.move(moveCommand,maze);
    }

    @Override
    public void moveHeroLeft() {
        moveCommand = new MoveLeftCommand();
        hero.move(moveCommand,maze);
    }

    @Override
    public void moveHeroDown() {
        moveCommand = new MoveDownCommand();
        hero.move(moveCommand,maze);
    }

    @Override
    public void moveHeroRight() {
        moveCommand = new MoveRightCommand();
        hero.move(moveCommand,maze);
    }

    @Override
    public void update() {
        //when the hero dies it restarts the level
    }


    @Override
    public void initiateLoop() {

        ArrayList<Monster> monsters = this.maze.getMonsters();

        for(Monster monster : monsters)
        {
            MonsterThread thread = new MonsterThread();
            RandomMotion motion = new RandomMotion();

            motion.setMonster(monster);
            thread.setMonster(monster);
            thread.setMaze(this.maze);
            thread.setMotion(motion);

            thread.start();
        }

    }

    @Override
    public void lookLeft() {
        hero.setDirectionState(new DirectionLeftState());
    }

    @Override
    public void lookRight() {
        hero.setDirectionState(new DirectionRightState());
    }

    @Override
    public void lookUp() {
        hero.setDirectionState(new DirectionUpState());
    }

    @Override
    public void lookDown() {
        hero.setDirectionState(new DirectionDownState());
    }

    @Override
    public void updateDeadObservable() {
        livesLeft--;
        if(livesLeft < 0){
            throw new RuntimeException("the game has ended");
            //call controller to go to main menu
        } else {
            throw new RuntimeException("you died ya krodia");
            //call controller to make a new level
        }
    }

    ///Sarah's
    @Override
    public Point getMazeDimensions() {
        return maze.getDimensions();
    }

    @Override
    public Drawable[][] getMovingObjectsLayer() {
        return maze.getMovingObjectsLayer();
    }
    @Override
    public Drawable[][] getRoadAndWallsLayer() {
        return maze.getRoadAndWallsLayer();
    }
    @Override
    public Drawable[][] getPickablesLayer() {
        return maze.getPickablesLayer();
    }

    @Override
    public void registerAsMazeLayerObserver(MazeLayersObserver observer) {
        maze.registerMazeLayersObserver(observer);
    }

    @Override
    public void registerAsBombObserver(BombExplosionObserver observer){
        ArrayList<Bomb> tmp = maze.getBombs();
        for (int i = 0 ; i < tmp.size() ;i++)
            tmp.get(i).registerExplosionObserver(observer);
    }



}
