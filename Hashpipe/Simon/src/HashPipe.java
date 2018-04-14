import edu.princeton.cs.algs4.*;
import static java.lang.Integer.*;

public class HashPipe {
	private Pipe rootPipe;
	private Pipe floorPipe;
	private int N;

	public HashPipe() {
		rootPipe = new Pipe();
		floorPipe = new Pipe();
		N = 0;
	}

	public int size() {
		return N;
	}

	public void put(String key, Integer val) {
		Pipe pipe = new Pipe(key, val);
		setPipeReference(rootPipe, pipe, pipe.getHeight() - 1);
		N++;
	}
	
	public Integer get(String key) {
		Pipe pipe = getPipeFromReference(rootPipe, key, rootPipe.getHeight() -1);
		if (pipe != null) return pipe.getValue();
		return null;
	}

	public String floor(String key) {
		if (!floorPipe.getKey().equals(key)) {
			Pipe pipe = getPipeFromReference(rootPipe, key, rootPipe.getHeight() -1);
			return (pipe != null && pipe.getKey().equals(key)) ? pipe.getKey() : floorPipe.getKey();
		}
		return floorPipe.getKey();
	}

	public String control(String key, int h) {
		if (getTrailingZeros(key) < (h + 1)) return null;
		Pipe pipe = getPipeFromReference(rootPipe, key, h);
		if (pipe.getNextPipe(h) == null) return null;
		else return pipe.getNextPipe(h).getKey();
	}

	private void setPipeReference(Pipe root, Pipe p, int height) {
		if (height < 0) return;
		if (root.getNextPipe(height) == null) {
			root.setNextPipe(p, height);
			setPipeReference(root, p, height - 1);
		}
		if (root.getNextPipe(height).getKey().hashCode() > p.getKey().hashCode()) {
			p.setNextPipe(root.getNextPipe(height), height);
			root.setNextPipe(p, height);
			setPipeReference(root, p, height - 1);
		}
		else if (root.getNextPipe(height).getKey().hashCode() < p.getKey().hashCode())
			setPipeReference(root.getNextPipe(height), p, height);
	}

	private Pipe getPipeFromReference(Pipe p, String key, int height) {
		if (height < 0) return null;
		if (p != null) floorPipe = p;
		if (p.getNextPipe(height) == null)
			return getPipeFromReference(p, key, height - 1);
		if (p.getNextPipe(height).getKey().hashCode() > key.hashCode())
			return getPipeFromReference(p, key, height - 1);
		else if (p.getNextPipe(height).getKey().hashCode() < key.hashCode())
			return getPipeFromReference(p.getNextPipe(height), key, height);
		else return p.getNextPipe(height);
	}

	private static Integer getTrailingZeros(String key) {
		return Integer.parseInt(toBinaryString(numberOfTrailingZeros(key.hashCode()) + 1), 2);
	}

	private class Pipe {
		private String key;
		private int value;
		private int height;
		private Pipe[] nextPipes;

		public Pipe() {
			this.key = "";
			this.value = -1;
			this.height = 32;
			this.nextPipes = new Pipe[32];
		}

		public Pipe(String key, int value) {
			this.key = key;
			this.value = value;
			this.height = getTrailingZeros(key);
			this.nextPipes = new Pipe[height];
		}

		public String getKey() { return this.key; }

		public int getValue() { return this.value; }

		public int getHeight() { return this.height; }

		public void setNextPipe(Pipe pipe, int index) { this.nextPipes[index] = pipe; }

		public Pipe getNextPipe(int index) { return nextPipes[index]; }
	}
}