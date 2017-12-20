package gameLevels.Levels;

import drawables.obstacles.bombs.Dynamite;
import drawables.obstacles.bombs.NormalBomb;
import drawables.obstacles.walls.Tree;
import drawables.obstacles.walls.Wood;
import drawables.pickables.gifts.Ammo;
import drawables.pickables.gifts.Coins;
import drawables.pickables.gifts.HealthCareProvider;
import drawables.pickables.shields.NormalShield;
import drawables.pickables.shields.PlatinumShield;
import drawables.pickables.shields.SteelShield;
import drawables.pickables.shields.WoodenShield;
import drawables.pickables.weapons.AK47;
import drawables.pickables.weapons.MP5;
import drawables.pickables.weapons.NormalGun;
import drawables.pickables.weapons.NuclearHead;
import drawables.pickables.weapons.RPG;
import gameLevels.LevelProperties;

public class Level1 implements LevelProperties {


    @Override
    public int getNumberOfMonsters() {
        return 0;
    }

    @Override
    public int getNumberOfTraps() {
        return 0;
    }

    @Override
    public int getNumberOfGifts() {
        return 2;
    }

    @Override
    public int getNumberOfBombs() {
        return 1;
    }

    @Override
    public int getNumberOfWeapons() {
        return 1;
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
        return new Class[0];
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
        return new Class[0];
    }

    @Override
    public Class[] getAllowedShields() {
        return new Class[]{PlatinumShield.class, WoodenShield.class, NormalShield.class, SteelShield.class};
    }
}
