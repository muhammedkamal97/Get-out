package memento;

import java.util.ArrayList;
/**
 * @author saraheldafrawy
 * CareTaker is the class that add and remove from the history
 */
public class CareTaker {

	/**Where all mementos are saved.
  */
	private ArrayList<Memento> savedShapes = new ArrayList<>();
	/**history limit.
	 */
	private final int maxSize19 = 19;

	/**
	 * Adds memento to the ArrayList.
	 * @param index is the index of the ArrayList
	 * @param m is the moment to be added in history
	 */
	public void addMemento(final int index, final Memento m) {
		if (index + 1 == savedShapes.size()) {
      if (index == maxSize19) {
        savedShapes.remove(0);
      }
			savedShapes.add(m);
		} else {
			savedShapes.add(index, m);
		}
	}

	/**Gets the memento requested from the ArrayList.
	 * @param index is the ArrayList index
	 * @return required element
  */
	public Memento getMemento(final int index) {
		return savedShapes.get(index);
	}

}
