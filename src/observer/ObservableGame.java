package observer;

public interface ObservableGame {
    public void registerEndGameObserver(EndOfGameObserver observer);
    public void notifyEndGameObserversOnWin();
    public void notifyEndGameObserversOnLose();

}
