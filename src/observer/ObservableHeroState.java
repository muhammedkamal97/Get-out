package observer;

public interface ObservableHeroState {

    public void registerStateObserver(HeroStateObserver observer);
    public void notifyChangeInHealth();
    public void notifyChangeInArmorPoints();
    public void notifyChangeInCoins();
    public void notifyChangeInNumberOfBullets();
    public void notifyChangeInCurrentWeapon();
}
