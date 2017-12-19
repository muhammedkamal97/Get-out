package gameLoop.monsterAI;

import java.util.Random;

import drawables.characters.Monster;
import drawables.characters.commands.Command;
import drawables.characters.commands.motionCommands.*;

public class RandomMotion implements IMonsterBehavior{
	private final Command[] commands = new Command[]{new MoveUpCommand(),
			new MoveDownCommand(),
			new MoveLeftCommand(),
			new MoveRightCommand()};
	private Command currentCommand;
	private Command prevCommand;
	private Monster monster;
	@Override
	public Command movementMind() {
		if(currentCommand != null)
			return currentCommand;
		Random rand = new Random();
		int selectCommand = rand.nextInt(4);
		currentCommand = commands[selectCommand];
		while(prevCommand == currentCommand){
			selectCommand = rand.nextInt(4);
			currentCommand = commands[selectCommand];
		}
		return currentCommand;
	}
	@Override
	public void setMonster(Monster monster) {
		// TODO Auto-generated method stub
		this.monster = monster;
	}
	@Override
	public void reThink() {
		// TODO Auto-generated method stub
		prevCommand = currentCommand;
		currentCommand = null;
	}
}
