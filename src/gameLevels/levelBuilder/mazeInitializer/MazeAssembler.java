package gameLevels.levelBuilder.mazeInitializer;

import drawables.Drawable;
import drawables.characters.Monster;
import drawables.obstacles.Bomb;
import drawables.obstacles.Trap;
import drawables.obstacles.Wall;
import drawables.obstacles.walls.Steel;
import drawables.pickables.Gift;
import drawables.pickables.Shield;
import drawables.pickables.Weapon;
import drawables.roads.Road;
import maze.ImpMaze;
import maze.Maze;
import maze.MazeComponents;

import java.awt.*;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;

public class MazeAssembler {

    MazeComponents components;
    private Stack<Point> allowedPosition;
    private Drawable[][] movingObjectsLayer;
    private Drawable[][] roadAndWallsLayer;
    private Drawable[][] pickablesLayer;

    private Class[] walls;
    private char[][] map;

    private Maze maze;

    public Maze assembleMaze(MazeComponents components) {
        this.components = components;
        createMap();

        maze = new Maze();
        mapDrawableIntoMaze();
        maze.setComponents(components);
        maze.setMovingObjectsLayer(movingObjectsLayer);
        maze.setPickablesLayer(pickablesLayer);
        maze.setRoadAndWallsLayer(roadAndWallsLayer);
        return maze;
    }

    public void setWalls(Class[] walls) {this.walls = walls;}

    private void createMap() {
        int[][] maze = this.components.mazeStructure;
        this.map = new char[maze.length][maze[0].length];
        for(int i = 0 ; i < this.map.length ; i++)
        {
            for(int j = 0 ; j < this.map[i].length ; j++)
            {
                if(i == 0 || i == this.map.length - 1 || j == 0 || j == this.map[i].length - 1)
                    map[i][j] = '!';
                else if (maze[i][j] == 0)
                    map[i][j] = 'f';
                else
                    map[i][j] = 't';
            }
        }
    }

    private void dfs(int i , int j , int wallNumber)
    {
        try {
            Constructor<?> ctor = this.walls[wallNumber].getConstructor();
            ctor.setAccessible(true);
            Wall wall = (Wall) ctor.newInstance();
            wall.setMaze(maze);
            roadAndWallsLayer[i][j] = wall;
            roadAndWallsLayer[i][j].setPosition(new Point(j,i));
            components.walls.add(wall);
        } catch (Exception e) {
            throw new RuntimeException("Failed to create instance");
        }

        map[i][j] = 'f';

        int right = i + 1;
        int left = i - 1;
        int up = j - 1;
        int down = j + 1;

        if(map[right][j] == 't')
            dfs(right,j,wallNumber);
        if(map[left][j] == 't')
            dfs(left,j,wallNumber);
        if(map[i][up] == 't')
            dfs(i,up,wallNumber);
        if(map[i][down] == 't')
            dfs(i,down,wallNumber);
    }

    private void mapDrawableIntoMaze() {
        movingObjectsLayer = new Drawable[components.mazeStructure.length][components.mazeStructure[0].length];
        roadAndWallsLayer = new Drawable[components.mazeStructure.length][components.mazeStructure[0].length];
        pickablesLayer = new Drawable[components.mazeStructure.length][components.mazeStructure[0].length];
        allowedPosition = new Stack<>();
        for (int i = 0; i < components.mazeStructure.length; i++) {
            for (int j = 0; j < components.mazeStructure[0].length; j++) {
                if (components.mazeStructure[i][j] == 0 && i > components.mazeStructure.length / 4
                        && j > components.mazeStructure[0].length / 4)//not a wall position
                    allowedPosition.push(new Point(j, i));
            }
        }
        Collections.shuffle(allowedPosition);
        setMonster();
        setGift();
        setWeapons();
        setShields();
        setBombs();
        setTraps();
        setWalls();
        setRoads();
    }

    private void setRoads(){
        for (int i = 0; i < components.mazeStructure.length; i++) {
            for (int j = 0; j < components.mazeStructure[0].length; j++) {
                if (roadAndWallsLayer[i][j] == null){
                    roadAndWallsLayer[i][j] = new Road();
                    roadAndWallsLayer[i][j].setMaze(maze);
                    roadAndWallsLayer[i][j].setPosition(new Point(j,i));
                }
            }
        }
    }

    private void setWalls() {

        int wallNumber = 0;
        components.walls = new ArrayList<>();
        for (int i = 0; i < components.mazeStructure.length; i++) {
            for (int j = 0; j < components.mazeStructure[0].length; j++) {

                if(i == 0 || i == components.mazeStructure.length - 1 || j == 0 || j == components.mazeStructure[0].length - 1)
                {
                    roadAndWallsLayer[i][j] = new Steel();
                    roadAndWallsLayer[i][j].setMaze(maze);
                    roadAndWallsLayer[i][j].setPosition(new Point(j,i));
                }
                else {
                    if(this.map[i][j] == 't')
                    {
                        if(wallNumber ==  this.walls.length)
                            wallNumber = 0;
                        dfs(i,j,wallNumber);
                        wallNumber++;
                    }
                }
            }
        }
    }

    private void setTraps() {
        for (Trap trap : components.traps) {
            trap.setMaze(maze);
            if (!allowedPosition.isEmpty()) {
                Point position = allowedPosition.pop();
                pickablesLayer[position.y][position.x] = trap;
                trap.setPosition(position);
            }
        }
    }

    private void setBombs() {

        for (Bomb bomb : components.bombs) {
            bomb.setMaze(maze);
            if (!allowedPosition.isEmpty()) {
                Point position = allowedPosition.pop();
                pickablesLayer[position.y][position.x] = bomb;
                bomb.setPosition(position);
            }
        }
    }

    private void setShields() {

        for (Shield shield : components.shields) {
          shield.setMaze(maze);
            if (!allowedPosition.isEmpty()) {
                Point position = allowedPosition.pop();
                pickablesLayer[position.y][position.x] = shield;
                shield.setPosition(position);
            }
        }
    }

    private void setWeapons() {
        for (Weapon weapon : components.weapons) {
            weapon.setMaze(maze);
            if (!allowedPosition.isEmpty()) {
                Point position = allowedPosition.pop();
                pickablesLayer[position.y][position.x] = weapon;
                weapon.setPosition(position);
            }
        }
    }

    private void setGift() {
        for (Gift gift : components.gifts) {
            gift.setMaze(maze);
            if (!allowedPosition.isEmpty()) {
                Point position = allowedPosition.pop();
                pickablesLayer[position.y][position.x] = gift;
                gift.setPosition(position);
            }
        }
    }

    private void setMonster() {
        for (Monster monster : components.monsters) {
            monster.setMaze(maze);
            //monster should have a position attribute.
            if (!allowedPosition.isEmpty()) {
                Point position = allowedPosition.pop();
                movingObjectsLayer[position.y][position.x] = monster;
                monster.setPosition(position);
            }
        }
    }
}
