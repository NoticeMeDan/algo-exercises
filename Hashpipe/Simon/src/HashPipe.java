import edu.princeton.cs.algs4.*;
import static java.lang.Integer.*;

public class HashPipe {
	private Pipe rootPipe;
	private int N;

	public HashPipe() {
		rootPipe = new Pipe();
		N = 0;
	}

	public int size() {
		return N;
	}

	public void put(String key, Integer val) {
		Pipe pipe = new Pipe(key, val);
		setPipeReference(rootPipe, pipe);
		N++;
	}
	
	public Integer get(String key) {
		Pipe pipe = getPipeFromReference(rootPipe, key, (rootPipe.getHeight() -1));
		StdOut.println(pipe);
		return null;
	}
	
	public String floor(String key) {
		return null;
	}

	public String control(String key) {
		for (int i = rootPipe.getHeight() - 1; i >= 0; i--) {
			if (rootPipe.getNextPipe(i) == null)
				StdOut.println("Null at: " + i);
			else
				StdOut.println(rootPipe.getNextPipe(i).getKey() + " at: " + i);
		}
		return rootPipe.toString();
	}

	private void setPipeReference(Pipe root, Pipe p) {
		for (int height = p.getHeight() - 1; height >= 0; height--) {
			if (root.getNextPipe(height) == null)
				root.setNextPipe(p, height);
			else {
				if (root.getNextPipe(height).getKey().hashCode() > p.getKey().hashCode()) {
					p.setNextPipe(root.getNextPipe(height), height);
					root.setNextPipe(p, height);
				}
				else if (root.getNextPipe(height).getKey().hashCode() < p.getKey().hashCode()) {
					root.getNextPipe(height).setNextPipe(p, height);
				}
				else break;
			}
		}
	}

	private Pipe getPipeFromReference(Pipe p, String key, int height) {
		if (height == 0) return null;
		if (p.getNextPipe(height) == null)
			return getPipeFromReference(p, key, height - 1);
		if (p.getNextPipe(height).getKey().hashCode() > key.hashCode())
			return getPipeFromReference(p, key, height - 1);
		if (p.getNextPipe(height).getKey().hashCode() < key.hashCode())
			return getPipeFromReference(p.getNextPipe(height), key, height - 1);
		return null;
	}

	private class Pipe {
		private String key;
		private int value;
		private int height;
		private Pipe[] nextPipes;

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