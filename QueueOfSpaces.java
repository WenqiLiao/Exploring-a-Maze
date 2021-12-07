package project3;

import project3.DoublyLinkedList.Node;

import project3.Labyrinth;

/**
 * 
 * This class should implement the PossibleLocations interface. It should 
 * be a reference based stack that uses your own doubly linked list as
 * the internal storage. Note that in order to implement the interface the
 * typical enqueue method needs to be called add and the typical dequeue 
 * method needs to be called remove.
 * 
 * @author Wenqi Liao
 * @version 03/15/2021
 *
 */
public class QueueOfSpaces<E> implements PossibleLocations {
	
	DoublyLinkedList<Location> locs = new DoublyLinkedList<Location>();
	
	
    /**
    * Add a Location object to this collection.
    * @param s object to be added
    * @throws NullPointerException if the given location is null
    */
	public void add(Location s) {
		
		if (s == null) {
			throw new NullPointerException("the given location is null.");
		} else {
			locs.add(s);
		}
	}


	/**
     * Remove the next object from this collection. The specific
     * item returned is determined by the underlying structure
     * by which this collection is represented.
     * @return the next object, or null if set is empty
     */
	public Location remove() {
		
		if (locs.isEmpty()) {
			return null;
		} else {
			return locs.remove(0);
		}
	}

	/**
	 * Determines if this collection is empty or not.
	 * @return  true, if set is empty, false, otherwise.
	 */
	public boolean isEmpty() {

		return locs.isEmpty();
	}
	
    /**
     * Returns a string representation of this collection.
     * The string representation consists of a list of the collection's
     * elements in the order they would be removed and returned by future
     * calls to remove(). The elements should be enclosed in square brackets (`"[]"`).
     * Adjacent elements are separated by the characters `", "` (comma and space).
     * Elements are converted to strings as by `String.valueOf(Object)`.
     * @return string representation of this colleciton
     */
	public String toString() {
		return locs.toString();
		
	}

	
}
