package gameLevels.levelBuilder.mazeInitializer;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class EmptyMazeStructureGenerator {

	private int[][] maze;
	private boolean[][] visited;
	private Point end = new Point(0, 0);

	/**
	 * generate a maze in form of 2D integer array.
	 * 
	 * @param wide
	 *            the width of the maze.
	 * @param hight
	 *            higth the width of the maze.
	 * @return the maze.
	 */
	public int[][] generateRandomMaze(int hight, int wide) {
		int higth = (hight%2 == 1) ? hight : hight+1;
		int width = (wide%2 == 1) ? wide : wide+1;
		maze = new int[higth][width];
		visited = new boolean[higth][width];
		for (int i = 0; i < higth; i++) {
			for (int j = 0; j < width; j++) {
				maze[i][j] = 1;
				visited[i][j] = false;
			}
		}
		Random rand = new Random();
		int x = rand.nextInt(higth);
		while (x % 2 != 1) {
			x = rand.nextInt(higth);
		}
		int y = rand.nextInt(width);
		while (y % 2 != 1) {
			y = rand.nextInt(width);
		}
		
		dfs(x, y);
		openCell(new Point(1,1));
		openCell(new Point(1, 0));
		openCell(new Point(higth-2,width-1));
		if(maze[2][1] == 1 &&maze[1][2] == 1 &&maze[2][2] == 1) {
			return generateRandomMaze(hight,wide);
		}

		return maze;
	}

	private void dfs(int x, int y) {
		Point p = new Point(x, y);
		Point next;
		Point wall;
		ArrayList<Integer> move = moves();
		for (Integer i : move) {
			switch (i) {
			case 1:// up
				next = new Point(p.x - 2, p.y);
				wall = new Point(p.x - 1, p.y);
				if (isAllowed(next)) {
					openCell(next);
					openCell(wall);
					dfs(next.x, next.y);
					break;
				}
			case 2:// down
				next = new Point(p.x + 2, p.y);
				wall = new Point(p.x + 1, p.y);
				if (isAllowed(next)) {
					openCell(next);
					openCell(wall);
					dfs(next.x, next.y);
					break;
				}
			case 3:// left
				next = new Point(p.x, p.y - 2);
				wall = new Point(p.x, p.y - 1);
				if (isAllowed(next)) {
					openCell(next);
					openCell(wall);
					dfs(next.x, next.y);
					break;
				}
			case 0:// right
				next = new Point(p.x, p.y + 2);
				wall = new Point(p.x, p.y + 1);
				if (isAllowed(next)) {
					openCell(next);
					openCell(wall);
					dfs(next.x, next.y);
					p = next;
					break;
				}
			}
		}

	}

	private boolean isAllowed(Point p) {
		boolean valid = true;
		valid = valid && (p.x > 0 && p.y > 0);
		valid = valid && (p.x < maze.length-1) && (p.y < maze[0].length-1);
		valid = valid && !visited[p.x][p.y];
		return valid;
	}

	private ArrayList<Integer> moves() {
		ArrayList<Integer> arr = new ArrayList<Integer>();
		arr.add(0);
		arr.add(1);
		arr.add(2);
		arr.add(3);
		Collections.shuffle(arr);
		return arr;
	}

	private void openCell(Point p) {
		visited[p.x][p.y] = true;
		maze[p.x][p.y] = 0;
		if (end.x < p.x || end.y < p.y) {
			end = p;
		}
	}
	
	public Point getEndPoint(){
		return end;
	}
	public Point getStartPoint(){
		return new Point(1,0);
	}

	public static void main(String[] args) {
		EmptyMazeStructureGenerator e = new EmptyMazeStructureGenerator();
		int[][] m = e.generateRandomMaze(24, 20);
		for (int i = 0; i < 25; i++) {
			for (int j = 0; j < 21; j++) {
				if (m[i][j] != 0)
					System.out.print('#');
				else
					System.out.print(" ");
				System.out.print(" ");
			}
			System.out.println();
		}
		System.out.println("start at : 1,1");
		System.out.println("finsih at : " + e.end);
	}

}
