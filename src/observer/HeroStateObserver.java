package observer;

import drawables.pickables.Weapon;

public interface HeroStateObserver {

    public void updateChangeInHealth(double healthPercentageLeft);
    public void updateChangeInArmorPoints(double ArmorPercentageLeft);
    public void updateCoins(int coins);//not the change but the coins held by the hero
    public void updateNumberOfBullets(int bullets);
    public void updateCurrentWeapon(Weapon weapon);


}
