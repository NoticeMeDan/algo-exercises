import edu.princeton.cs.algs4.*;

public class GiantBook {
	private int initialT;
	private int N, T;

	private int[] connectionLaps, giantLaps, nonIsolatedLaps;

	private void run(int n, int t) {
		this.N = n;
		this.T = t;
		this.initialT = t;

		StdOut.printf("Input: N = %s, T = %s\n", this.N, this.T);

		this.initExperiment();
	}

	private void initExperiment() {
		// Init/Reset occurence arrays
		this.connectionLaps = 	new int[this.T];
		this.giantLaps = 		new int[this.T];
		this.nonIsolatedLaps = 	new int[this.T];

		this.doExperiment();

		if (this.evaluateExperiment()) {
			this.printStats();
		} else {
			StdOut.println("T too small, increasing by user provided value.");
			this.T += this.initialT;
			this.initExperiment();
		}
	}

	private void doExperiment() {
		// Run experiment T times
		for (int i = 0; i < this.T; i++) {
			boolean shouldContinueExperiment = true;
			int lapCount = 0;

			int connectionLap 	= -1;
			int giantLap 		= -1;
			int nonIsolatedLap 	= -1;

			MyUnionFind uf = new MyUnionFind(this.N);

			while (shouldContinueExperiment) {
				if (uf.count() == 1) {
					connectionLap = lapCount;
					shouldContinueExperiment = false;
				}

				int p = StdRandom.uniform(this.N);
				int q = StdRandom.uniform(this.N);

				if (p == q) continue;
				// if (uf.connected(p, q)) continue;

				uf.union(p, q);
				lapCount++;
			}

			this.connectionLaps[i] = connectionLap;
		}
	}

	public boolean evaluateExperiment() {
		boolean hasPassed = true;

		if (StdStats.stddev(this.connectionLaps) >= StdStats.mean(this.connectionLaps) / 10) hasPassed = false;

		return hasPassed;
	}

	public void printStats() {
		StringBuilder sb = new StringBuilder();

		sb.append("T: " + this.T + "\n");
		sb.append("N: " + this.N + "\n");
		// sb.append("\n");
		// sb.append("Giant Component: " + StdStats.mean(giantEmergences) + "\n");
		// sb.append("Giant Component (stddev): " + StdStats.stddev(giantEmergences) + "\n");
		// sb.append("\n");
		// sb.append("No Isolated: " + StdStats.mean(noisolatedEmergences) + "\n");
		// sb.append("No Isolated (stddev): " + StdStats.stddev(noisolatedEmergences) + "\n");
		sb.append("\n");
		sb.append("Connected: " + StdStats.mean(this.connectionLaps) + "\n");
		sb.append("Connected (stddev): " + StdStats.stddev(this.connectionLaps) + "\n");

		StdOut.println(sb);
	}

	public static void main(String[] args) {
		int n = 100;
		int t = 10;

		if (args.length == 2) {
			n = Integer.parseInt(args[0]);
			t = Integer.parseInt(args[1]);
		}

		GiantBook gb = new GiantBook();
		gb.run(n, t);
	}

}