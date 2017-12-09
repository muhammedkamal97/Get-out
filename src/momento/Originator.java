package momento;
/**
 * @author saraheldafrawy
 *
 */
public class Originator {
  /**variable storing the two shapes.
   */
	private TwoShapes twoShapes;

	/**sets the value for the moment.
	 * @param newTS moment
	 */
	public void set(final TwoShapes newTS) {
		this.twoShapes = newTS;
	}

	/**Creates a new Memento with a new article.
	 * @return the created moment
  */
	public Memento storeInMemento() {
		return new Memento(twoShapes);
	}

	/** @param memento Gets the article currently stored in memento.
	 * @return the stored moment
  */
	public TwoShapes restoreFromMemento(final Memento memento) {

		this.twoShapes = memento.getSavedShape();

		return this.twoShapes;
	}

}
