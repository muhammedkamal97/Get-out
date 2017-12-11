package observer;

public interface ObservedSubject {

   public void notifyObservers();
   public void RegisterObserver(SubjectObserver observer);

}
