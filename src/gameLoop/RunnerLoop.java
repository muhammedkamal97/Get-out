package gameLoop;

import drawables.characters.Hero;
import drawables.characters.commands.Command;
import drawables.characters.commands.motionCommands.MoveDownCommand;
import drawables.characters.commands.motionCommands.MoveLeftCommand;
import drawables.characters.commands.motionCommands.MoveRightCommand;
import drawables.characters.commands.motionCommands.MoveUpCommand;
import maze.Maze;

public class RunnerLoop implements GameLoop{

    private Maze maze;
    private Hero hero;
    private Command moveCommand;
    @Override
    public void setLoopMaze(Maze maze) {
        this.maze = maze;
    }

    @Override
    public void setHero(Hero hero) {
        this.hero = hero;
    }

    @Override
    public void shoot() {
        hero.shoot(this.maze);
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
        //starts monsters thread
    }

}
