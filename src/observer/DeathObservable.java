package observer;

public interface DeathObservable {

    public void notifyDeathObservers();
    public void registerDeathObserver(DeathObserver observer);

}
