package gameLoop.monsterAI;

import drawables.characters.Monster;
import drawables.characters.commands.Command;
import drawables.characters.commands.motionCommands.MoveDownCommand;
import drawables.characters.commands.motionCommands.MoveLeftCommand;
import drawables.characters.commands.motionCommands.MoveRightCommand;
import drawables.characters.commands.motionCommands.MoveUpCommand;
import maze.Maze;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Mahmoud on 12/15/2017.
 */
public class MonsterThread extends Thread{

    private ArrayList<Monster> monster;
    private IMonsterBehavior motion;
    private Maze maze;

    public void setMonster(ArrayList<Monster> monster) {this.monster = monster;}

    public void setMotion(IMonsterBehavior motion) {this.motion = motion;}

    public void  setMaze(Maze maze) {this.maze = maze;}

    @Override
    public void run()
    {
        while(this.monster.size() != 0) {

            for (Monster monster : this.monster){
                if(monster.getHealthPoints() == 0)
                    this.monster.remove(monster);
                while (!monster.move(this.motion.movementMind(), this.maze))
                    this.motion.reThink();
            }
            try {
                sleep(1000);
            } catch (InterruptedException e) {

            }
        }
    }

}
