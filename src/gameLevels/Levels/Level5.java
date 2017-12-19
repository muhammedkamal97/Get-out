package gameLevels.Levels;

import drawables.characters.monsters.GiantMonster;
import drawables.characters.monsters.NormalMonster;
import drawables.characters.monsters.ShootingMonster;
import drawables.obstacles.bombs.Dynamite;
import drawables.obstacles.bombs.Lazer;
import drawables.obstacles.bombs.NormalBomb;
import drawables.obstacles.traps.Cage;
import drawables.obstacles.traps.Fire;
import drawables.obstacles.traps.RandomTeleport;
import drawables.obstacles.walls.Bricks;
import drawables.obstacles.walls.Wood;
import drawables.pickables.gifts.Ammo;
import drawables.pickables.gifts.HealthCareProvider;
import drawables.pickables.weapons.AK47;
import drawables.pickables.weapons.MP5;
import drawables.pickables.weapons.NuclearHead;
import drawables.pickables.weapons.RPG;
import gameLevels.LevelProperties;

public class Level5 implements LevelProperties {
    @Override
    public int getNumberOfMonsters() {
        return 7;
    }

    @Override
    public int getNumberOfTraps() {
        return 4;
    }

    @Override
    public int getNumberOfGifts() {
        return 2;
    }

    @Override
    public int getNumberOfBombs() {
        return 6;
    }

    @Override
    public int getNumberOfWeapons() {
        return 3;
    }

    @Override
    public int getNumberOfShields() {
        return 0;
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
        return new Class[]{AK47.class, MP5.class, RPG.class, NuclearHead.class};
    }

    @Override
    public Class[] getAllowedWalls() {
        return new Class[]{Bricks.class, Wood.class};
    }

    @Override
    public Class[] getAllowedMonsters() {
        return new Class[]{NormalMonster.class,GiantMonster.class, ShootingMonster.class};
    }

    @Override
    public Class[] getAllowedGifts() {
        return new Class[]{HealthCareProvider.class, Ammo.class,Ammo.class,Ammo.class};
    }

    @Override
    public Class[] getAllowedBombs() {
        return new Class[]{Lazer.class, Dynamite.class, NormalBomb.class};
    }

    @Override
    public Class[] getAllowedTraps() {
        return new Class[]{Fire.class, Cage.class, RandomTeleport.class};
    }

    @Override
    public Class[] getAllowedShields() {
        return new Class[0];
    }
}
