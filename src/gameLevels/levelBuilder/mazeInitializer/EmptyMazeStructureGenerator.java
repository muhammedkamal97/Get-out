package gameLevels.levelBuilder.mazeInitializer;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class EmptyMazeStructureGenerator {

	static private int[][] maze;
	static private boolean[][] visited;
	static private Point end = new Point(0, 0);

	/**
	 * generate a maze in form of 2D integer array.
	 * 
	 * @param wide
	 *            the width of the maze.
	 * @param length
	 *            length the width of the maze.
	 * @return the maze.
	 */
	static public int[][] generateRandomMaze(int wide, int length) {
		maze = new int[wide][length];
		visited = new boolean[wide][length];
		for (int i = 0; i < wide; i++) {
			for (int j = 0; j < length; j++) {
				maze[i][j] = 1;
				visited[i][j] = false;
			}
		}
		openCell(new Point(1, 1));
		Random rand = new Random();
		int x = rand.nextInt(wide);
		while (x % 2 != 0) {
			x = rand.nextInt(wide);
		}
		int y = rand.nextInt(length);
		while (y % 2 != 1) {
			y = rand.nextInt(length);
		}
		// x & y must one of them be even and one be odd
		// to get a solution to the maze (searching for a reason).
		dfs(x, y);

		return maze;
	}

	static private void dfs(int x, int y) {
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

	static private boolean isAllowed(Point p) {
		boolean valid = true;
		valid = valid && (p.x > 0 && p.y > 0);
		valid = valid && (p.x < maze.length - 1) && (p.y < maze[0].length - 1);
		valid = valid && !visited[p.x][p.y];
		return valid;
	}

	static private ArrayList<Integer> moves() {
		ArrayList<Integer> arr = new ArrayList<Integer>();
		arr.add(0);
		arr.add(1);
		arr.add(2);
		arr.add(3);
		Collections.shuffle(arr);
		return arr;
	}

	static private void openCell(Point p) {
		visited[p.x][p.y] = true;
		maze[p.x][p.y] = 0;
		if (end.x < p.x || end.y < p.y) {
			end = p;
		}
	}

	public static void main(String[] args) {
		int[][] m = generateRandomMaze(21, 21);
		for (int i = 0; i < 21; i++) {
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
		System.out.println("finsih at : " + end);
	}
}
