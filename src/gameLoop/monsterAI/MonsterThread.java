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
import java.util.Iterator;

/**
 * Created by Mahmoud on 12/15/2017.
 */
public class MonsterThread extends Thread{

    private ArrayList<Monster> monster;
    private ArrayList<IMonsterBehavior> motion;
    private Maze maze;

    public void setMonster(ArrayList<Monster> monster) {this.monster = monster;}

    public void  setMaze(Maze maze) {this.maze = maze;}

    @Override
    public void run()
    {
    	motion = new ArrayList<IMonsterBehavior>();

    	for(Monster monster: this.monster)
    		motion.add(new RandomMotion());

        while(this.monster.size() != 0) {

            int i = 0;
            for (Monster monster : this.monster){
            	if(monster.getHealthPoints() == 0){
                    this.monster.remove(monster);
                    this.motion.remove(i);
                }
                while (!monster.move(this.motion.get(i).movementMind(), this.maze))
                    this.motion.get(i).reThink();
                i++;
            }
            try {
                sleep(1000);
            } catch (InterruptedException e) {

            }
        }
    }

    public void terminate(){
        motion.clear();
        monster.clear();
        this.stop();
    }

}
