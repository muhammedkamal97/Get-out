package maze;

import drawables.characters.Monster;
import drawables.characters.monsters.Behaviors.ShootingBehavior;
import drawables.obstacles.Bomb;
import drawables.obstacles.Trap;
import drawables.obstacles.Wall;
import drawables.pickables.Gift;
import drawables.pickables.Shield;
import drawables.pickables.Weapon;

import java.util.ArrayList;

public class MazeComponents {
    public ArrayList<Monster> monsters;
    public ArrayList<Weapon> weapons;
    public ArrayList<Shield> shields;
    public ArrayList<Wall> walls;
    public ArrayList<Bomb> bombs;
    public ArrayList<Gift> gifts;
    public ArrayList<Trap> traps;
    public int[][] mazeStructure;
}
