import edu.princeton.cs.algs4.*;

public class Exp {
	public static void main(String[] args) {
		HashPipe hashpipe = new HashPipe();
		hashpipe.put("T", 21);
		hashpipe.put("B", 45);

		hashpipe.control("test");

		StdOut.println("Size: " + hashpipe.size());
		StdOut.println("Result A: " + hashpipe.get("A"));
		StdOut.println("Result B: " + hashpipe.get("B"));
	}
}