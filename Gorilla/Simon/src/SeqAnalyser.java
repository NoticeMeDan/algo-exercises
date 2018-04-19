import edu.princeton.cs.algs4.*;

public class SeqAnalyser {
	public static void main(String[] args) {
		while(!StdIn.isEmpty()) {
			StdOut.println(StdIn.readLine());
		}
	}

	private static double cosAngle(int[] p, int[] q) {
		if (p.length != q.length) return 0.0;
		double dotProduct = 0;
		double vectorPowsP = 0;
		double vectorPowsQ = 0;

		for (int i = 0; i < p.length; i++) { 
			dotProduct += (p[i] * q[i]);
			vectorPowsP += Math.pow(p[i], 2);
			vectorPowsQ += Math.pow(q[i], 2);
		}

		return dotProduct / (Math.sqrt(vectorPowsP) * Math.sqrt(vectorPowsQ));
	}

	private static void cosineTest() {
		int[] p1 = new int[] {0, 1};
		int[] q1 = new int[] {0, 1};
		StdOut.println("Expected: 1 :: " + cosAngle(p1, q1));

		int[] p2 = new int[] {0, 2};
		int[] q2 = new int[] {0, 2};
		StdOut.println("Expected: 1 :: " + cosAngle(p2, q2));

		int[] p3 = new int[] {4, -3};
		int[] q3 = new int[] {2, 2};
		StdOut.println("Expected: ~0.1414 :: " + cosAngle(p3, q3));
	}
}

