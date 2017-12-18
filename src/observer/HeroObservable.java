package observer;

public interface HeroObservable {

    public void notifyDeathObservers();
    public void registerDeathObserver(DeathObserver observer);

    public void notifyWinObservers();
    public void registerWinObserver(HeroWinObserver observer);

}
