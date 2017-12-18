package gameLoop.monsterAI;

import java.awt.Point;
import java.util.ArrayList;
import java.util.LinkedList;

public class UtilitesAI {
	private static int[][] arr;
	private static ArrayList<Point> path;
	public static int[][] pairOfShortestPath(int[][] maze,ArrayList<Point> pathIntersections){
		path = pathIntersections;
		arr = maze;
		int vertices = pathIntersections.size();
		int[][] graph = new int[vertices][vertices];
		for(int i = 0;i < vertices;i++){
			for(int j = i+1;j < vertices;j++){
				if(graph[i][j] != 0) continue;
				graph[i][j] = bfs(path.get(i), path.get(j));
				graph[j][i] = graph[i][j];
			}
		}
		return graph;
	}

	private static int bfs(Point start,Point end){
		boolean visit[][] = new boolean[arr.length][arr[0].length];
		LinkedList<Point> queue = new LinkedList<Point>();
		LinkedList<Integer> road = new LinkedList<Integer>();
		visit[start.x][start.y] = true;
		queue.add(start);
		road.add(0);
		int edge = 0;
		while(!queue.isEmpty()){
			Point current = queue.pollFirst();
			int weight = road.pollFirst();
			if(current.equals(end)){
				edge = weight;
				break;
			}
			visit[current.x][current.y] = true;
			weight++;
			Point next = new Point(current.x+1,current.y);
			if(allowedCell(next, arr)&&!visit[current.x+1][current.y]){
				queue.add(new Point(next));
				road.add(weight);
			}
			next.x = next.x - 2;
			if(allowedCell(next, arr)&&!visit[current.x-1][current.y]){
				queue.add(new Point(next));
				road.add(weight);
			}
			next.x++;
			next.y++;
			if(allowedCell(next, arr)&&!visit[current.x][current.y+1]){
				queue.add(new Point(next));
				road.add(weight);
			}
			next.y -= 2;
			if(allowedCell(next, arr)&&!visit[current.x+1][current.y-1]){
				queue.add(new Point(next));
				road.add(weight);
			}
		}
		return edge;
	}

	public static ArrayList<Point> pathIntersections(int[][] maze){
		ArrayList<Point> intersections = new ArrayList<Point>();
		for(int i = 0;i < maze.length;i++){
			for(int j = 0;j < maze[0].length;j++){
				Point current = new Point(i,j);
				if(!allowedCell(current,maze)) continue;
				if(isIntersection(current, maze)) intersections.add(current);
			}
		}
		return intersections;
	}

	private static boolean allowedCell(Point p,int[][] maze){
		boolean allowed = true;
		allowed = allowed && (p.x > 0 && p.y > 0);
		allowed = allowed && (p.x < maze.length - 1 && p.y < maze[0].length-1);
		allowed = allowed && (maze[p.x][p.y] != 1);
		return allowed;
	}

	private static boolean isIntersection(Point p,int[][] maze){
		int intersections = 0;
		if(maze[p.x + 1][p.y] != 1){
			intersections++;
		}
		if(maze[p.x - 1][p.y] != 1){
			intersections++;
		}
		if(maze[p.x][p.y + 1] != 1){
			intersections++;
		}
		if(maze[p.x][p.y - 1] != 1){
			intersections++;
		}
		return intersections > 2;
	}
	public static void main(String[] args) {
		int[][] maze = new int[][]{{1,1,1,1,1,1,1},
								   {1,0,1,0,1,0,1},
								   {1,0,1,0,0,0,1},
								   {1,0,1,1,0,0,1},
								   {1,0,0,1,0,1,1},
								   {1,1,0,0,0,0,1},
								   {1,1,1,1,1,1,1}};
		System.out.println(pathIntersections(maze));
		int[][] graph = pairOfShortestPath(maze, pathIntersections(maze));
		for(int i = 0;i<4;i++){
			for(int j = 0;j<4;j++){
				System.out.print(graph[i][j]+" ");
			}
			System.out.println();
		}
	}
}
