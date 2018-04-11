import edu.princeton.cs.algs4.*;

public class Exp {
	public static void main(String[] args) {
		HashPipe hashpipe = new HashPipe();
		hashpipe.put("A", 21);
		hashpipe.put("B", 33);
		StdOut.println(hashpipe.get("B"));
	}
}