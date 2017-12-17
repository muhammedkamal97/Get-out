package gameLevels.Levels;

import drawables.characters.monsters.DestroyerMonster;
import drawables.characters.monsters.NormalMonster;
import drawables.obstacles.bombs.Dynamite;
import drawables.obstacles.bombs.NormalBomb;
import drawables.obstacles.traps.Cage;
import drawables.obstacles.traps.Fire;
import drawables.obstacles.traps.Hole;
import drawables.obstacles.traps.RandomTeleport;
import drawables.obstacles.walls.Tree;
import drawables.obstacles.walls.Wood;
import drawables.pickables.gifts.Ammo;
import drawables.pickables.gifts.Coins;
import drawables.pickables.gifts.HealthCareProvider;
import drawables.pickables.shields.BombShield;
import drawables.pickables.shields.FlameShield;
import drawables.pickables.shields.MonesterShield;
import drawables.pickables.shields.TrapShield;
import drawables.pickables.weapons.AK47;
import drawables.pickables.weapons.MP5;
import drawables.pickables.weapons.NormalGun;
import drawables.pickables.weapons.NuclearHead;
import drawables.pickables.weapons.RPG;
import gameLevels.LevelProperties;

public class Level1 implements LevelProperties {


    @Override
    public int getNumberOfLives() {
        return 0;
    }

    @Override
    public int getNumberOfMonsters() {
        return 2;
    }

    @Override
    public int getNumberOfTraps() {
        return 2;
    }

    @Override
    public int getNumberOfGifts() {
        return 2;
    }

    @Override
    public int getNumberOfBombs() {
        return 3;
    }

    @Override
    public int getNumberOfWeapons() {
        return 2;
    }

    @Override
    public int getNumberOfShields() {
        return 1;
    }

    @Override
    public int getMazeWidth() {
        return 20;
    }

    @Override
    public int getMazeLength() {
        return 20;
    }

    @Override
    public Class[] getAllowedWeapons() {
        return new Class[]{MP5.class, AK47.class, NuclearHead.class, NormalGun.class, RPG.class};
    }

    @Override
    public Class[] getAllowedWalls() {
        return new Class[]{Tree.class, Wood.class};
    }

    @Override
    public Class[] getAllowedMonsters() {
        return new Class[] {NormalMonster.class, DestroyerMonster.class};
    }

    @Override
    public Class[] getAllowedGifts() {
        return new Class[] {Coins.class, Ammo.class, HealthCareProvider.class};
    }

    @Override
    public Class[] getAllowedBombs() {
        return new Class[]{NormalBomb.class, Dynamite.class};
    }

    @Override
    public Class[] getAllowedTraps() {
        return new Class[]{Cage.class, Fire.class, Hole.class, RandomTeleport.class};
    }

    @Override
    public Class[] getAllowedShields() {
        return new Class[]{BombShield.class, FlameShield.class, MonesterShield.class, TrapShield.class};
    }
}
