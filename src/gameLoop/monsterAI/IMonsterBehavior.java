package gameLoop.monsterAI;

import drawables.characters.Monster;
import drawables.characters.commands.Command;

public interface IMonsterBehavior {
	public Command movementMind();
	public void setMonster(Monster monster);
	public void reThink();
}
