package maze;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;

import drawables.Drawable;
import drawables.pickables.*;
import drawables.obstacles.*;
import drawables.characters.Monster;

public class ImpMaze extends Maze{


	private final int Wall = 1;
	
	private Stack<Point> allowedPosition;
	private Drawable[][] drawables;

	@Override
	public Drawable getItemInPosition(Point position) {
		return drawables[position.x][position.y];
	}

	@Override
	public void mapDrawableIntoMaze(MazeComponents components) {
		drawables = new Drawable[maze.length][maze[0].length];
		allowedPosition = new Stack<Point>();
		for(int i = 0;i < maze.length;i++){
			for(int j = 0;j < maze[0].length;j++){
				if(maze[i][j] == 0 && i < maze.length/4 && j < maze[0].length/4)//not a wall position
					allowedPosition.push(new Point(i,j));
			}
		}
		Collections.shuffle(allowedPosition);
		setMonster(components);
		setGift(components);
		setWeapons(components);
		setShields(components);
		setBombs(components);
		setTraps(components);
		setWalls(components);
	}
	
			private void setWalls(MazeComponents components) {
				// TODO Auto-generated method stub
				int indexOfWalls = 0;
				for(int i = 0;i < maze.length;i++){
					for(int j = 0;j < maze[0].length;j++){
						if(maze[i][j] == 1)//not a wall position
							drawables[i][j] = components.walls.get(indexOfWalls++); 
					}
				}
			}
		
			private void setTraps(MazeComponents components) {
				// TODO Auto-generated method stub
				for(Trap trap : components.traps){
					if(!allowedPosition.isEmpty()){
						Point position = allowedPosition.pop();
						drawables[position.x][position.y] = trap;
					}
				}
			}
		
			private void setBombs(MazeComponents components) {
				// TODO Auto-generated method stub
				for(Bomb bomb : components.bombs){
					if(!allowedPosition.isEmpty()){
						Point position = allowedPosition.pop();
						drawables[position.x][position.y] = bomb;
					}
				}
			}
		
			private void setShields(MazeComponents components) {
				// TODO Auto-generated method stub
				for(Shield shield : components.shields){
					if(!allowedPosition.isEmpty()){
						Point position = allowedPosition.pop();
						drawables[position.x][position.y] = shield;
					}
				}
			}
		
			private void setWeapons(MazeComponents components) {
				// TODO Auto-generated method stub
				for(Weapon weapon : components.weapons){
					if(!allowedPosition.isEmpty()){
						Point position = allowedPosition.pop();
						drawables[position.x][position.y] = weapon;
					}
				}
			}
		
			private void setGift(MazeComponents components) {
				for(Gift gift : components.gifts){
					if(!allowedPosition.isEmpty()){
						Point position = allowedPosition.pop();
						drawables[position.x][position.y] = gift;
					}
				}
			}
		
			private void setMonster(MazeComponents components){
				for(Monster monster : components.monsters){
					//monster should have a position attribute.
					if(!allowedPosition.isEmpty()){
						Point position = allowedPosition.pop();
						drawables[position.x][position.y] = monster;
					}
				}
			}
}
