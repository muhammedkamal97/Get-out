package gameLevels.levelBuilder.mazeInitializer;

import drawables.Drawable;
import drawables.characters.Monster;
import drawables.obstacles.Bomb;
import drawables.obstacles.Trap;
import drawables.pickables.Gift;
import drawables.pickables.Shield;
import drawables.pickables.Weapon;
import drawables.roads.Road;
import maze.ImpMaze;
import maze.Maze;
import maze.MazeComponents;

import java.awt.*;
import java.util.Collections;
import java.util.Stack;

public class MazeAssembler {

    MazeComponents components;
    private Stack<Point> allowedPosition;
    private Drawable[][] movingObjectsLayer;
    private Drawable[][] roadAndWallsLayer;
    private Drawable[][] pickablesLayer;

    public Maze assembleMaze(MazeComponents components) {
        this.components = components;
        mapDrawableIntoMaze();
        Maze maze = new Maze();
        maze.setComponents(components);
        maze.setMovingObjectsLayer(movingObjectsLayer);
        maze.setPickablesLayer(pickablesLayer);
        maze.setRoadAndWallsLayer(roadAndWallsLayer);
        return maze;
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
                    allowedPosition.push(new Point(i, j));
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
                if (roadAndWallsLayer[i][j] == null)
                    roadAndWallsLayer[i][j] = new Road();
            }
        }
    }

    private void setWalls() {
        int indexOfWalls = 0;
        for (int i = 0; i < components.mazeStructure.length; i++) {
            for (int j = 0; j < components.mazeStructure[0].length; j++) {
                if (components.mazeStructure[i][j] == 1)//not a wall position
                    roadAndWallsLayer[i][j] = components.walls.get(indexOfWalls++);
            }
        }
    }

    private void setTraps() {
        for (Trap trap : components.traps) {
            if (!allowedPosition.isEmpty()) {
                Point position = allowedPosition.pop();
                pickablesLayer[position.x][position.y] = trap;
            }
        }
    }

    private void setBombs() {
        for (Bomb bomb : components.bombs) {
            if (!allowedPosition.isEmpty()) {
                Point position = allowedPosition.pop();
                pickablesLayer[position.x][position.y] = bomb;
            }
        }
    }

    private void setShields() {
        for (Shield shield : components.shields) {
            if (!allowedPosition.isEmpty()) {
                Point position = allowedPosition.pop();
                pickablesLayer[position.x][position.y] = shield;
            }
        }
    }

    private void setWeapons() {
        for (Weapon weapon : components.weapons) {
            if (!allowedPosition.isEmpty()) {
                Point position = allowedPosition.pop();
                pickablesLayer[position.x][position.y] = weapon;
            }
        }
    }

    private void setGift() {
        for (Gift gift : components.gifts) {
            if (!allowedPosition.isEmpty()) {
                Point position = allowedPosition.pop();
                pickablesLayer[position.x][position.y] = gift;
            }
        }
    }

    private void setMonster() {
        for (Monster monster : components.monsters) {
            //monster should have a position attribute.
            if (!allowedPosition.isEmpty()) {
                Point position = allowedPosition.pop();
                movingObjectsLayer[position.x][position.y] = monster;
            }
        }
    }
}
