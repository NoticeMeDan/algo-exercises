import edu.princeton.cs.algs4.*;
import java.util.Optional;

public class HashPipe {
	private int size;
	private Pipe rootPipe;

	public HashPipe() {
		this.size = 0;
		this.rootPipe = new Pipe();
	}

	// Return number of elements
	public int size() { return this.size; }

	// Put key/val pair into the table
	public void put(String key, Integer val) {
		Pipe newPipe = new Pipe(key, val);
		this.insertPipe(this.rootPipe, newPipe, newPipe.getHeight());
	}

	// Value associated with key
	public Integer get(String key) { return null; }

	// Largest key less than or equal to key
	public String floor(String key) { return null; }

	// Return content of pipe at given key and height
	public String control(String key, int h) { return null; }

	// Insert pipe
	private void insertPipe(Pipe from, Pipe to, int height) {
		if (height < 0) return; // Avoid NPE's
		if (!from.getNextPipe(height).isPresent()) { // If "from" does not have a pipe pointer, make it point to "to", and go down a level
			from.setNextPipe(to, height);
			StdOut.println("Oh hi");
			this.insertPipe(from, to, height - 1);
		}
		else if (from.getNextPipe(height).get().getKey().compareTo(to.getKey()) < 0) {
			this.insertPipe(from.getNextPipe(height).get(), to, height);
		}
		else if (from.getNextPipe(height).get().getKey().compareTo(to.getKey()) > 0) {
			to.setNextPipe(from.getNextPipe(height).get(), height);
			from.setNextPipe(to, height);
			this.insertPipe(from, to, height - 1);
		}
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
			this.elements = new PipeProxy[this.getPipeHeight(key)];
		}

		private int getPipeHeight(String key) {
			return Integer.numberOfTrailingZeros(key.hashCode()) + 1;
		}

		public String getKey() { return this.key; }

		public int getValue() { return this.value; }

		Optional<Pipe> getNextPipe(int index) {
			return this.elements[index] == null ? Optional.empty() : Optional.of(this.elements[index].pipe);
		}

		void setNextPipe(Pipe pipe, int index) { this.elements[index] = new PipeProxy(pipe); }

		int getHeight() { return this.elements.length; }
	}

	public static void main(String[] args) {
		StdOut.println("Hi mom");

		HashPipe H = new HashPipe();
		H.put("K", 10);

		// // Test
  //       int i=0;
  //       //        String [] in = new String[0];
  //       String [] in = new String[26];
  //       i=0;
  //       for(char c = 'A'; c <= 'Z'; c++ ) in[i++] = "" + c;

  //       HashPipe H = new HashPipe();

  //       for( int j=0;j<in.length;j++ ) {
  //           H.put(in[j], j);
  //           System.out.print("Insert: ");
  //           System.out.println(in[j]);
  //           for( int g=0;g<j;g++ ) {
  //               for( int h=0;h<32;h++ ) {
  //                   String ctrl = H.control(in[g],h);
  //                   if( ctrl != null ) System.out.print(ctrl);
  //                   else System.out.print(".");
  //                   System.out.print(" ");
  //               }
  //               System.out.print(" : ");
  //               System.out.println(in[g]);
  //           }
  //       }
	}
}
