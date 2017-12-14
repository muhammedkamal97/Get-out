package gameLevels.levelBuilder;

import drawables.characters.Monster;
import drawables.characters.monsters.Behaviors.ShootingBehavior;
import drawables.obstacles.Bomb;
import drawables.obstacles.Trap;
import drawables.obstacles.Wall;
import drawables.pickables.Gift;
import drawables.pickables.Shield;
import drawables.pickables.Weapon;
import gameLevels.LevelProperties;
import gameLevels.levelBuilder.mazeInitializer.EmptyMazeStructureGenerator;
import gameLevels.levelBuilder.mazeInitializer.MazeAssembler;
import maze.Maze;
import maze.MazeComponents;
import randomizer.RandomComponentsFiller;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class LevelBuilder {

    private LevelProperties properties;
    private MazeAssembler assembler = new MazeAssembler();
    private MazeComponents components = new MazeComponents();

    private EmptyMazeStructureGenerator generator
            = new EmptyMazeStructureGenerator();
    private RandomComponentsFiller random = new RandomComponentsFiller();

    public void setLevelProperties(LevelProperties properties) {
        this.properties = properties;
    }

    public Maze buildLevelMaze() {
        buildMazeStructure();
        buildPickables();
        buildMonsters();
        buildObstacles();
        return assembler.assembleMaze(components);
    }

    private void buildMazeStructure() {

        components.mazeStructure = generator.generateRandomMaze(
                properties.getMazeWidth(),
                properties.getMazeLength()
        );

    }

    private void buildMonsters() {
        components.monsters = (ArrayList<Monster>) random.generateRandomArray(
                properties.getAllowedMonsters(),
                properties.getNumberOfMonsters()
        );

    }

    private void buildObstacles() {
        buildWalls();
        buildBombs();
        buildTraps();
    }

    private void buildWalls() {
       this.assembler.setWalls(properties.getAllowedWalls());
    }

    private void buildBombs() {
        components.bombs = (ArrayList<Bomb>) random.generateRandomArray(
                properties.getAllowedBombs(),
                properties.getNumberOfBombs()
        );
    }

    private void buildTraps() {
        components.traps = (ArrayList<Trap>) random.generateRandomArray(
                properties.getAllowedTraps(),
                properties.getNumberOfTraps()
        );
    }

    private void buildPickables() {
        buildGifts();
        buildWeapons();
        buildShields();
    }

    private void buildGifts() {
        components.gifts = (ArrayList<Gift>) random.generateRandomArray(
                properties.getAllowedGifts(),
                properties.getNumberOfGifts()
        );
    }

    private void buildShields() {
        components.shields = (ArrayList<Shield>) random.generateRandomArray(
                properties.getAllowedShields(),
                properties.getNumberOfShields()
        );
    }

    private void buildWeapons() {
        components.weapons = (ArrayList<Weapon>) random.generateRandomArray(
                properties.getAllowedWeapons(),
                properties.getNumberOfWeapons()
        );
    }
}