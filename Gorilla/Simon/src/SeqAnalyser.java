import edu.princeton.cs.algs4.*;
import java.util.*;
import java.text.DecimalFormat;

public class SeqAnalyser {
	private int d = 10000;
	private int k = 20;

	public String compareDNA(String sequenceP, String sequenceQ) {
		DecimalFormat df = new DecimalFormat("#.###");
		int[] vectorP = vectorFromSequence(sequenceP);
		int[] vectorQ = vectorFromSequence(sequenceQ);
		return df.format(cosAngle(vectorP, vectorQ));
	}

	private int[] vectorFromSequence(String sequence) {
		int[] vector = new int[this.d];
		String kGram;

		for (int i = 0; i + this.k < sequence.length(); i++) {
			kGram = sequence.substring(i, i + this.k);
			vector[kGramHash(kGram)]++;
		}

		return vector;
	}

	private int kGramHash(String s) {
		return Math.abs(s.hashCode() % this.d);
	}

	private double cosAngle(int[] p, int[] q) {
		double dotProduct = 0;
		double vectorPowsP = 0;
		double vectorPowsQ = 0;

		for (int i = 0; i < this.d; i++) {
			dotProduct += (p[i] * q[i]);
			vectorPowsP += Math.pow(p[i], 2);
			vectorPowsQ += Math.pow(q[i], 2);
		}

		return dotProduct / (Math.sqrt(vectorPowsP) * Math.sqrt(vectorPowsQ));
	}

	private void cosineTest() {
		int[] p1 = new int[] {0, 1};
		int[] q1 = new int[] {0, 1};
		StdOut.println("Expected: 1 :: " + cosAngle(p1, q1));

		int[] p2 = new int[] {0, 2};
		int[] q2 = new int[] {0, 2};
		StdOut.println("Expected: 1 :: " + cosAngle(p2, q2));

		int[] p3 = new int[] {4, -3};
		int[] q3 = new int[] {2, 2};
		StdOut.println("Expected: ~0.1414 :: " + cosAngle(p3, q3));

		int[] p4 = new int[] {0, 1};
		int[] q4 = new int[] {0, 2};
		StdOut.println("Expected: something :: " + cosAngle(p4, q4));

		int[] p5 = new int[] {0, 1};
		int[] q5 = new int[] {1, 0};
		StdOut.println("Expected: something :: " + cosAngle(p5, q5));

		int[] p6 = new int[] {0, 1};
		int[] q6 = new int[] {0, -1};
		StdOut.println("Expected: something :: " + cosAngle(p5, q5));
	}

	public static void main(String[] args) {
		SeqAnalyser seq = new SeqAnalyser();
		seq.cosineTest();
	}
}

