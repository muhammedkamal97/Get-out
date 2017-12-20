package gameLevels.Levels;

import drawables.characters.monsters.DestroyerMonster;
import drawables.characters.monsters.GiantMonster;
import drawables.characters.monsters.ShootingMonster;
import drawables.obstacles.bombs.Dynamite;
import drawables.obstacles.bombs.Lazer;
import drawables.obstacles.bombs.Piston;
import drawables.obstacles.traps.Cage;
import drawables.obstacles.traps.Fire;
import drawables.obstacles.traps.Hole;
import drawables.obstacles.traps.RandomTeleport;
import drawables.obstacles.walls.Bricks;
import drawables.obstacles.walls.Steel;
import drawables.obstacles.walls.Tree;
import drawables.obstacles.walls.Wood;
import drawables.pickables.gifts.Ammo;
import drawables.pickables.gifts.Coins;
import drawables.pickables.gifts.HealthCareProvider;
import drawables.pickables.gifts.TrialGift;
import drawables.pickables.shields.NormalShield;
import drawables.pickables.shields.PlatinumShield;
import drawables.pickables.shields.SteelShield;
import drawables.pickables.shields.WoodenShield;
import drawables.pickables.weapons.AK47;
import drawables.pickables.weapons.MP5;
import drawables.pickables.weapons.NuclearHead;
import drawables.pickables.weapons.RPG;
import gameLevels.LevelProperties;

public class Level3 implements LevelProperties {


    @Override
    public int getNumberOfMonsters() {
        return 3;
    }

    @Override
    public int getNumberOfTraps() {
        return 2;
    }

    @Override
    public int getNumberOfGifts() {
        return 4;
    }

    @Override
    public int getNumberOfBombs() {
        return 3;
    }

    @Override
    public int getNumberOfWeapons() {
        return 5;
    }

    @Override
    public int getNumberOfShields() {
        return 2;
    }

    @Override
    public int getMazeWidth() {
        return 30;
    }

    @Override
    public int getMazeLength() {
        return 30;
    }

    @Override
    public Class[] getAllowedWeapons() {
        return new Class[]{MP5.class, AK47.class, NuclearHead.class, RPG.class};
    }

    @Override
    public Class[] getAllowedWalls() {
        return new Class[]{Tree.class, Bricks.class, Wood.class};
    }

    @Override
    public Class[] getAllowedMonsters() {
        return new Class[] {DestroyerMonster.class, GiantMonster.class, ShootingMonster.class};
    }

    @Override
    public Class[] getAllowedGifts() {
        return new Class[] {Coins.class, Ammo.class, TrialGift.class, HealthCareProvider.class};
    }

    @Override
    public Class[] getAllowedBombs() {
        return new Class[]{Dynamite.class, Lazer.class, Piston.class};
    }

    @Override
    public Class[] getAllowedTraps() {
        return new Class[]{Cage.class, Fire.class, Hole.class, RandomTeleport.class};
    }

    @Override
    public Class[] getAllowedShields() {
        return new Class[]{NormalShield.class, PlatinumShield.class, SteelShield.class, WoodenShield.class};
    }
}
