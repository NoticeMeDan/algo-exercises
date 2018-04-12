import edu.princeton.cs.algs4.*;
import static java.lang.Integer.*;

/**
*	For local experimental purposes only
*	Will not be handed in
*/
public class Exp {
	public static void main(String[] args) {
		HashPipe hashpipe = new HashPipe();
		hashpipe.put("A", 33);
		hashpipe.put("B", 33);
		hashpipe.put("C", 33);
		hashpipe.put("D", 33);
		
		StdOut.println(hashpipe.control("D", 4));
	}

	private static Integer testHeight(String key) {
		return Integer.parseInt(toBinaryString(numberOfTrailingZeros(key.hashCode()) + 1), 2);
	}
}