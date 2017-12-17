package gameLoop.monsterAI;

import drawables.characters.Monster;
import drawables.characters.commands.Command;
import drawables.characters.commands.motionCommands.MoveDownCommand;
import drawables.characters.commands.motionCommands.MoveLeftCommand;
import drawables.characters.commands.motionCommands.MoveRightCommand;
import drawables.characters.commands.motionCommands.MoveUpCommand;
import maze.Maze;

import java.awt.*;

/**
 * Created by Mahmoud on 12/15/2017.
 */
public class MonsterThread extends Thread{

    private Monster monster;
    private IMonsterBehavior motion;
    private Maze maze;

    public void setMonster(Monster monster) {this.monster = monster;}

    public void setMotion(IMonsterBehavior motion) {this.motion = motion;}

    public void  setMaze(Maze maze) {this.maze = maze;}

    @Override
    public void run()
    {
        while(this.monster.getHealthPoints() > 0)
        {
            while(!this.monster.move(this.motion.movementMind(),this.maze))
                this.motion.reThink();

            try {
                sleep(4000);
            } catch (InterruptedException e) {

            }
        }
    }

}
