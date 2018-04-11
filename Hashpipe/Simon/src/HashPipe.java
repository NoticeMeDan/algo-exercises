import edu.princeton.cs.algs4.*;
import static java.lang.Integer.*;

public class HashPipe {
	private Pipe rootPipe;
	private int N;

	public HashPipe() {
		rootPipe = new Pipe();
	}

	public int size() {
		return 0;
	}
	
	public void put(String key, Integer val) {
		Pipe pipe = new Pipe(key, val);
		setPipeReference(rootPipe);
	}
	
	public Integer get(String key) {
		getPipeFromReference(rootPipe, key);
		return null;
	}
	
	public String floor(String key) {
		return null;
	}

	public String control(String key) {
		return rootPipe.toString();
	}

	private void setPipeReference(Pipe p) {
		for (int i = p.getHeight() - 1; i >= 0; i--) {
			if (p.getNextPipe(i) == null)
				p.setNextPipe(p, i);
			else
				setPipeReference(p.getNextPipe(i));
		}
	}

	private Pipe getPipeFromReference(Pipe p, String key) {
		for (int i = p.getHeight() - 1; i >= 0; i--) {
			if (p.getNextPipe(i) == null) continue;
			if (p.getKey().hashCode() < key.hashCode()) continue;
			if (p.getKey().hashCode() > key.hashCode())
				return getPipeFromReference(p.getNextPipe(i), key);
			if (p.getKey().equals(key)) 
				return p;
		}
		return null;
	}

	private class Pipe {
		private String key;
		private int value;
		private int height;
		private Pipe[] nextPipes;

		// Creates a root pipe
		public Pipe() {
			this.key = null;
			this.value = -1;
			this.height = 32;
			this.nextPipes = new Pipe[32];
		}

		public Pipe(String key, int value) {
			this.key = key;
			this.value = value;
			this.height = computeTrailingZeroHeight(key);
			this.nextPipes = new Pipe[height];
		}

		public String getKey() { return this.key; }

		public int getValue() { return this.value; }

		public int getHeight() { return this.height; }

		public void setNextPipe(Pipe pipe, int index) { this.nextPipes[index] = pipe; }

		public Pipe getNextPipe(int index) { return nextPipes[index]; }

		private int computeTrailingZeroHeight(String s) {
			return Integer.parseInt(toBinaryString(numberOfTrailingZeros(key.hashCode()) + 1), 2);
		}
	}
}