package project3;

import java.util.*;

/**
 * 
 * This class represents a DoublyLinkedList class. It should be a generic class
 * that does not permit null element
 * 
 * @author Wenqi Liao
 * @version 03/15/2021
 *
 */
public class DoublyLinkedList<E> implements Iterable<E> {

	public class Node<E> {
		
		public E data;
		public Node<E> next;
		public Node<E> prev;

		public Node(E data) {
			this.data = data;
			this.next = null;
		}
	}

	private Node<E> head;
	private Node<E> tail;
	private int size;

	/**
	 * Constructor Creates an empty list object
	 */
	public DoublyLinkedList() {
		
		head = null;
		tail = null;
		size = 0;
	}

	/**
	 * 
	 * Appends the specified element to the end of this list.
	 * 
	 * @param The specific element needed to be add.
	 * @throws ClassCastException if the class of the specified element prevents it
	 *                            from being added to this list
	 * @return Returns true if this list changed as a result of the call. Returns
	 *         false if the specified element is null.
	 */
	public boolean add(E e) throws ClassCastException {
		
		boolean adding = false;
		
		if (e == null) {
			throw new NullPointerException("the element should not be null.");
		}
		try {
			Node<E> newNode = new Node<E>(e);
			if (head == null) {
				head = tail = newNode;
				head.prev = null;
				tail.next = null;
			} else {
				tail.next = newNode;
				newNode.prev = tail;
				tail = newNode;
				tail.next = null;
			}
			adding = true;
			size++;
		} catch (ClassCastException err) {
			System.err.println("the class of the specified element prevents it " + "from being added to this list");
		}
		return adding;

	}

	/**
	 * 
	 * Appends the specified element to this list.
	 * 
	 * @param The specific element needed to be add.
	 * @throws ClassCastException if the class of the specified element prevents it
	 *                            from being added to this list
	 *                            IndexOutOfBoundsException - if pos is out of range
	 *                            (pos < 0 || pos >= size())
	 * @return Returns true if this list changed as a result of the call. Returns
	 *         false if the specified element is null.
	 */
	public boolean add(E e, int pos) throws ClassCastException {

		boolean adding = false;

		if (e == null) {
			throw new NullPointerException("the element should not be null.");
		}

		if (pos < 0 || pos > size) {
			throw new IndexOutOfBoundsException("the position is out of range");
		}

		try {
			Node<E> newNode = new Node<E>(e);

			if (head == null) {
				head = newNode;
				tail = newNode;
			} else if (pos == 0) {
				newNode.next = head;
				head.prev = newNode;
				head = newNode;
			} else if (pos == size) {
				newNode.prev = tail;
				tail.next = newNode;
				tail = newNode;
			} else {
				Node<E> nodeRef = head;
				for (int i = 1; i < pos; i++)
					nodeRef = nodeRef.next;
				newNode.next = nodeRef.next;
				nodeRef.next = newNode;
				newNode.prev = nodeRef;
				newNode.next.prev = newNode;
			}
			adding = true;
			size++;
		} catch (ClassCastException err) {
			System.err.println("he class of the specified element prevents it " + "from being added to this list");
		}
		return adding;

	}

	/**
	 * 
	 * Removes all of the elements from this list. The list will be empty after this
	 * call returns.
	 */
	public void clear() {
		
		Node<E> temp;
		while (head != null) {
			temp = head;
			head = head.next;
			temp = null;
		}
		size = 0;
	}

	/**
	 * 
	 * @param o - element whose presence in this list is to be tested
	 * @return Returns true if this list contains the specified element. More
	 *         formally, returns true if and only if this list contains at least one
	 *         element e such that Objects.equals(o, e).
	 */
	public boolean contains(Object o) {
		
		if (this.size == 0) {
			return false;
		}

		Node<E> current = head;
		while (current != null) {
			if (Objects.equals(o, current.data)) {
				return true;
			} else {
				current = current.next;
			}
		}
		return false;
	}

	/**
	 * 
	 * Compares the specified object with this list for equality.
	 * 
	 * @param o - the object to be compared for equality with this list
	 * @return Returns true if and only if the specified object is also an instance
	 *         of a DoublyLinkedList , both lists have the same size, and all
	 *         corresponding pairs of elements in the two lists are equal.
	 */
	public boolean equals(Object o) {
		
		if (this == o) {
			return true;
		}

		if (o == null) {
			return false;
		}
		if (!(o instanceof DoublyLinkedList<?>)) {
			return false;
		}

		DoublyLinkedList<E> other = (DoublyLinkedList<E>) o;
		if (this.size != other.size) {
			return false;
		}

		Iterator<E> thisItr = this.iterator();
		Iterator<E> otherItr = other.iterator();
		while (thisItr.hasNext() && otherItr.hasNext()) {
			if (!(thisItr.next().equals(otherItr.next()))) {
				return false;
			}
		}

		return true;

	}

	// inner class implementing an iterator for this list
	private class Itr implements Iterator<E> {

		private Node<E> current = head;

		public boolean hasNext() {
			return current != null;
		}

		public E next() {
			E tmp = current.data;
			current = current.next;
			return tmp;
		}
	}

	/**
	 * 
	 * Return an iterator over elements of this list.
	 * 
	 * @return an iterator over elements of this list.
	 */
	public Iterator<E> iterator() {

		return new Itr();
	}

	/**
	 * 
	 * @return Returns the element at the specified position in this list or null if
	 *         such position does not exist.
	 * @param pos - index of the element to return
	 */
	public E get(int pos) {

		Node<E> current = head;
		if (pos < 0 || pos >= size) {
			return null;
		} else {
			for (int i = 1; i <= pos; i++) {
				current = current.next;
			}
		}
		return current.data;
	}

	/**
	 * 
	 * @return Returns true if this list contains no elements.
	 */
	public boolean isEmpty() {

		if (size() == 0) {
			return true;
		}
		return false;
	}

	/**
	 * 
	 * @return Returns the index of the first occurrence of the specified element in
	 *         this list, or -1 if this list does not contain the element. More
	 *         formally, returns the lowest index i such that (o==null ?
	 *         get(i)==null : o.equals(get(i))), or -1 if there is no such index.
	 * @param pos - o - element to search for
	 */
	public int indexOf(Object o) {
		
		if (size == 0) {
			return -1;
		}
		int index = 0;
		Node<E> current = head;
		while (current != null) {
			if (current.data.equals(o)) {
				return index;
			}
			index++;
			current = current.next;
		}
		return -1;
	}

	/**
	 * 
	 * Removes the first occurrence of the specified element from this list, if it
	 * is present. If this list does not contain the element, it is unchanged. More
	 * formally, removes the element with the lowest index i such that
	 * Objects.equals(o, get(i)) (if such an element exists).
	 * 
	 * @param o - element to be removed from this list, if present
	 * @throws ClassCastException - if the type of the specified element is
	 *                            incompatible with this list NullPointerException -
	 *                            if the specified element is null
	 * @return Returns true if this list contained the specified element (or
	 *         equivalently, if this list changed as a result of the call).
	 */
	public boolean remove(Object o) {

		if (o == null) {
			throw new NullPointerException("The object o should not be null");
		}
		if (this.contains(o)) {
			try {
				int pos = this.indexOf(o);
				if (pos != -1) {
					remove(pos);
					return true;
				}
			} catch (ClassCastException err) {
				System.err.println(" the type of the specified element is incompatible with this list");
			}
		}
		return false;
	}

	/**
	 * 
	 * Removes the element at the specified position pos in this list. Shifts any
	 * subsequent elements to the left (subtracts one from their indices).
	 * 
	 * @param pos - the index of the element to be removed
	 * @throws IndexOutOfBoundsException - if pos is out of range (pos < 0 || pos >=
	 *                                   size())
	 * @return Returns the element that was removed from the list.
	 */
	public E remove(int pos) {
		
		if (pos < 0 || pos >= size) {
			throw new IndexOutOfBoundsException("Invalid position given");
		}
		if (!isEmpty()) {
			Node<E> tmp = head;
			for (int i = 0; i < pos; i++) {
				tmp = tmp.next;
			}
			if (tmp.prev != null) {
				tmp.prev.next = tmp.next;
			} else {
				head = tmp.next;
			}
			if (tmp.next != null) {
				tmp.next.prev = tmp.prev;
			}
			size--;
			return tmp.data;
		}
		return null;
	}

	/**
	 * 
	 * @return Returns the number of elements in this list.
	 */
	public int size() {
		
		int counter = 0;
		Node<E> tmp = head;
		while (tmp != null) {
			counter++;
			tmp = tmp.next;
		}
		return counter;
	}

	/**
	 * 
	 * @return Returns a string representation of this list. The string
	 *         representation consists of a list of the collection's elements in the
	 *         order they are returned by its iterator, enclosed in square brackets
	 *         ("[]"). Adjacent elements are separated by the characters ", " (comma
	 *         and space). Elements are converted to strings as by
	 *         String.valueOf(Object).
	 */
	public String toString() {

		if (head == null) {
			return "[]";
		}

		String toReturn = "";
		Iterator<E> thisItr = this.iterator();
		while (thisItr.hasNext()) {
			toReturn += String.valueOf(thisItr.next()) + ", ";
		}
		return "[ " + toReturn.substring(0, size - 1) + "]";
	}

}
