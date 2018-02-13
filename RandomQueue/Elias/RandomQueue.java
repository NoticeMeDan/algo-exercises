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

	public Iterator<T> iterator() { // return an iterator over the items in random order
		return new RandomQueueIterator(this.items.clone());
	}

	private void resize(int capacity) {
		T[] temp = (T[]) new Object[capacity];

		for (int i = 0; i < size; i++) {
			temp[i] = items[i];
		}

		items = temp;
	}

	private class RandomQueueIterator implements Iterator<T> {
		private int index;
		private int size;
		private T[] q;

		public RandomQueueIterator(T[] elements) {
			this.index = 0;
			this.q = elements;
			this.size = elements.length - 1;

			this.randomize();
		}

		public void randomize() {
			for (int i = 0; i < this.q.length; i++) {
				int randomIndex = StdRandom.uniform(size);

				// Do a switcheroo between i and randomIndex
				T temp = this.q[i];
				this.q[i] = this.q[randomIndex];
				this.q[randomIndex] = temp;
			}
		}

		public boolean hasNext() {
			return this.index < this.size;
		}

		public void remove() { throw new UnsupportedOperationException();  }

		public T next() {
			if (!hasNext()) throw new NoSuchElementException();

			T item = this.q[this.index];
			this.index++;
			return item;
		}
	}

	// The main method below tests your implementation. Do not change it.
	public static void main(String args[]) {
		// Build a queue containing the Integers 1,2,...,6:
		RandomQueue<Integer> Q= new RandomQueue<Integer>();
		for (int i = 1; i < 7; ++i) Q.enqueue(i); // autoboxing! cool!

		boolean passedAll = true;
		int sum = 0;
		int rep = 100;
		for(int x=0;x<rep;x++) {
			int [] taken = new int[7];
			Iterator<Integer> I= Q.iterator();
			int j =0;
			while (I.hasNext()){
				j++;
				int k = I.next();
				if( k <1 || k > 6 )
					StdOut.printf("Not an element %d (at %d deque)\n",k, j);
				taken[k]++;
				if( 3 == j ) sum += k;
			}

			boolean allOut = true;
			for (int i = 1; i < 7; ++i) if(taken[i] != 1) allOut = false;
			if( ! allOut) {
				for (int i = 1; i < 7; ++i)
					StdOut.printf("Taken[%d] = %d\n", i,taken[i]);
				passedAll = false;
			}
		}
		if( passedAll)
			StdOut.println("All Out on all iterators passed");
		{
			double mean = (1.0*sum)/rep;
			double minM =3.0, maxM = 4.1;
			if( mean > minM && mean < maxM )
				StdOut.printf("Standard mean on iterator between %5.2f and %5.2f\n", minM,maxM);
			else
				StdOut.printf("Standard mean on iterator outside %5.2f and %5.2f:  %5.2f\n",
							  minM,maxM,mean);
		}
  }
}
