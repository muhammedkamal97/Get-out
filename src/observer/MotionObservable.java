package observer;

public interface MotionObservable {
    public void registerMotionObservers(MotionObserver observer);
    public void notifyMotionObservers();
}
