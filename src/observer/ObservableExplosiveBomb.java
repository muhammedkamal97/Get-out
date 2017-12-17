package observer;

public interface ObservableExplosiveBomb {

    public void registerExplosionObserver(BombExplosionObserver observer);
    public void notifyExplosionObservers();
}
