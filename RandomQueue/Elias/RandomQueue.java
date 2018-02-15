import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;


public class RandomQueue<T> implements Iterable<T> {
	// Your code goes here.
	// Mine takes ca. 60 lines, my longest method has 5 lines.
	private T[] items;
	private int size;

	@SuppressWarnings("unchecked")
	public RandomQueue() { // create an empty random queue
		this.items = (T[]) new Object[1];
		this.size = 0;
	}

	public boolean isEmpty() {// is it empty?
		return this.size == 0;
	}

	public int size() {// return the number of elements
		return this.size;
	}

	public void enqueue(T item) { // add an item
		// Resize array if needed
		if (this.items.length == this.size)
			this.resize(items.length * 2);

		this.items[size] = item;
		this.size++;
	}

	public T sample(){ // return (but do not remove) a random item
		int randomIndex = StdRandom.uniform(this.size);
		return this.items[randomIndex];
	}

	public T dequeue(){ // remove and return a random item
		if (this.isEmpty())
			throw new NoSuchElementException("Queue Underflow");

		int randomIndex = StdRandom.uniform(this.size);

		T randomItem = this.items[randomIndex];

		// Put last item into randomItems place, and remove last item
		this.items[randomIndex] = this.items[this.size - 1];
		this.items[this.size - 1] = null;
		this.size--;

		// Resize array if needed
		if (this.size > 0 && this.size == this.items.length / 4)
			this.resize(this.items.length / 2);

		return randomItem;
	}

	@SuppressWarnings("unchecked")
	private void resize(int capacity) {
		T[] temp = (T[]) new Object[capacity];

		for (int i = 0; i < size; i++) {
			temp[i] = items[i];
		}

		this.items = temp;
	}

	public Iterator<T> iterator() { // return an iterator over the items in random order
		return new RandomQueueIterator(this.items.clone());
	}

	private class RandomQueueIterator implements Iterator<T> {
		int index;
		T[] q;

		public RandomQueueIterator(T[] items) {
			this.index = 0;
			this.q = items;

			this.randomize();
		}

		public boolean hasNext() { return this.index < size; }

		public void remove() { throw new UnsupportedOperationException(); }

		public T next() {
			if (!hasNext()) throw new NoSuchElementException();

			T item = q[index];
			this.index++;
			return item;
		}

		public void randomize(){
			for(int i = 0; i < size; i++){
				int randomIndex = StdRandom.uniform(0, size);

				T temp = this.q[i];
				this.q[i] = this.q[randomIndex];
				this.q[randomIndex] = temp;
			}
		}
	}

	// The main method below tests your implementation. Do not change it.
	public static void main(String args[]) {
		// Build a queue containing the Integers 1,2,...,6:
		RandomQueue<Integer> Q= new RandomQueue<Integer>();
		for (int i = 1; i < 7; ++i) Q.enqueue(i); // autoboxing! cool!

		// Print 30 die rolls to standard output
		StdOut.print("Some die rolls: ");
		for (int i = 1; i < 30; ++i) StdOut.print(Q.sample() +" ");
		StdOut.println();
		// Let’s be more serious: do they really behave like die rolls?
		int[] rolls= new int [10000];
		for (int i = 0; i < 10000; ++i)
		rolls[i] = Q.sample(); // autounboxing! Also cool!
		StdOut.printf("Mean (should be around 3.5): %5.4f\n", StdStats.mean(rolls));
		StdOut.printf("Standard deviation (should be around 1.7): %5.4f\n",
		StdStats.stddev(rolls));

		// Now remove 3 random values
		StdOut.printf("Removing %d %d %d\n", Q.dequeue(), Q.dequeue(), Q.dequeue());
		// Add 7,8,9
		for (int i = 7; i < 10; ++i) Q.enqueue(i);
		// Empty the queue in random order
		while (!Q.isEmpty()) StdOut.print(Q.dequeue() +" ");
		StdOut.println();

		// Let’s look at the iterator. First, we make a queue of colours:
		RandomQueue<String> C= new RandomQueue<String>();
		C.enqueue("red"); C.enqueue("blue"); C.enqueue("green"); C.enqueue("yellow");
		Iterator I= C.iterator();
		Iterator J= C.iterator();

		StdOut.print("Two colours from first shuffle: "+I.next()+" "+I.next()+" ");
		StdOut.print("\nEntire second shuffle: ");
		while (J.hasNext()) StdOut.print(J.next()+" ");
		StdOut.print("\nRemaining two colours from first shuffle: "+I.next()+" "+I.next());
  }
}
