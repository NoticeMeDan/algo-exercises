import java.util.Optional;

public class HashPipe {
	private Pipe rootPipe;
	private Pipe floorPipe;
	private int size;

	public HashPipe() {
		this.rootPipe = new Pipe();
		//this.floorPipe = new Pipe();
		this.size = 0;
	}

	// Return number of elements
	public int size() {
		return size;
	}

	// Put key/val pair into the table
	public void put(String key, Integer val) {
		Pipe newPipe = new Pipe(key, val);
		this.insertPipe(this.rootPipe, newPipe, newPipe.getHeight());
		this.size++;
	}

	// Value associated with key
	public Integer get(String key) {
		Optional<Pipe> pipe = getPipe(this.rootPipe, key, this.rootPipe.getHeight());
		return pipe.isPresent() ? pipe.get().getValue() : null;
	}

//    // Largest key less than or equal to key
//    public String floor(String key) {
//        if (!floorPipe.getKey().equals(key)) {
//            Pipe pipe = getPipe(rootPipe, key, rootPipe.getHeight() -1);
//            return (pipe != null && pipe.getKey().equals(key)) ? pipe.getKey() : floorPipe.getKey();
//        }
//        return floorPipe.getKey();
//    }

	// Return content of pipe at given key and height
	public String control(String key, int h) {
		if (getPipeHeight(key) < (h + 1)) return null;
		Optional<Pipe> pipe = this.getPipe(this.rootPipe, key, h);
		if (pipe.isPresent()) {
			Optional<Pipe> nextPipe = pipe.get().getNextPipe(h);
			return nextPipe.map(Pipe::getKey).orElse(null);
		}
		return null;
	}

	// Insert pipe
	private void insertPipe(Pipe from, Pipe to, int height) {
		if (height < 0) return;
		Optional<Pipe> nextPipe = from.getNextPipe(height);

		if (!nextPipe.isPresent()) {
			from.setNextPipe(to, height);
			insertPipe(from, to, height - 1);
		} else {
			if (this.keyIsBefore(nextPipe.get().getKey(), to.getKey())) {
				this.insertPipe(nextPipe.get(), to, height);
			}
			else if (this.keyIsAfter(nextPipe.get().getKey(), to.getKey())) {
				to.setNextPipe(nextPipe.get(), height);
				from.setNextPipe(to, height);
				this.insertPipe(from, to, height - 1);
			}
		}
	}

	// Get value by key
	private Optional<Pipe> getPipe(Pipe from, String key, int height) {
		if (height < 0) return Optional.empty();
		Optional<Pipe> nextPipe = from.getNextPipe(height);

		if (!nextPipe.isPresent()) { return getPipe(from, key, height - 1); }
		else {
			if (this.keyIsBefore(nextPipe.get().getKey(), key)) {
				return getPipe(nextPipe.get(), key, height);
			}
			else if (this.keyIsAfter(nextPipe.get().getKey(), key)) {
				return getPipe(from, key, height - 1);
			}
			else return nextPipe;
		}
	}

	private boolean keyIsAfter(String a, String b) {
		return a.compareTo(b) > 0;
	}

	private boolean keyIsBefore(String a, String b) {
		return a.compareTo(b) < 0;
	}

	private int getPipeHeight(String key) {
		return Integer.numberOfTrailingZeros(key.hashCode()) + 1;
	}

	private class Pipe {
		private String key;
		private int value;
		private PipeProxy[] elements;

		private class PipeProxy {
			Pipe pipe;
			PipeProxy(Pipe pipe) { this.pipe = pipe; }
		}

		Pipe() {
			this.key = "";
			this.value = 0;
			this.elements = new PipeProxy[32];
		}

		Pipe(String key, int value) {
			this.key = key;
			this.value = value;
			this.elements = new PipeProxy[getPipeHeight(key)];
		}

		String getKey() { return this.key; }

		int getValue() { return this.value; }

		Optional<Pipe> getNextPipe(int index) {
			return this.elements[index] == null ? Optional.empty() : Optional.of(this.elements[index].pipe);
		}

		void setNextPipe(Pipe pipe, int index) { this.elements[index] = new PipeProxy(pipe); }

		int getHeight() { return this.elements.length - 1; }
	}

	public static void main(String[] args) {
		// Test
		int i=0;
		//        String [] in = new String[0];
		String [] in = new String[26];
		i=0;
		for(char c = 'A'; c <= 'Z'; c++ ) in[i++] = "" + c;

		HashPipe H = new HashPipe();

		for( int j=0;j<in.length;j++ ) {
			H.put(in[j], j);
			System.out.print("Insert: ");
			System.out.println(in[j]);
			for( int g=0;g<j;g++ ) {
				for( int h=0;h<32;h++ ) {
					String ctrl = H.control(in[g],h);
					if( ctrl != null ) System.out.print(ctrl);
					else System.out.print(".");
					System.out.print(" ");
				}
				System.out.print(" : ");
				System.out.println(in[g]);
			}
		}
	}
}
