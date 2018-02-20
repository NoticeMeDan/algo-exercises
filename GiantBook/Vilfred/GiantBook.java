import edu.princeton.cs.algs4.*;
import java.io.*;
import java.text.*;
import java.util.*;

public class GiantBook{
	public class MyUnionFind {
    private int[] parent;   // parent[i] = parent of i
    private int[] size;     // size[i] = number of sites in subtree rooted at i
    private int count;      // number of components
    private int biggestComponentSize; // (M) the size of the current biggest component
    private Set<Integer> isolatedIndividuals; // (M) set containing sites that are isolated

    /**
     * Initializes an empty unionfind data structure with {@code n} sites
     * {@code 0} through {@code n-1}. Each site is initially in its own 
     * component.
     *
     * @param  n the number of sites
     * @throws IllegalArgumentException if {@code n < 0}
     */
    public MyUnionFind(int n) {
        biggestComponentSize = 1; // (M)
        isolatedIndividuals = new HashSet<Integer>(); // (M)
        count = n;
        parent = new int[n];
        size = new int[n];
        for (int i = 0; i < n; i++) {
            isolatedIndividuals.add(i); // (M)
            parent[i] = i;
            size[i] = 1;
        }
    }
    
    /**
     * (M) Return biggestComponentSize
     */
    public int getBiggestComponentSize() {
        return biggestComponentSize;
    }
    
    /**
     * (M) Check if there exists isolated individuals
     */
    public boolean noIndividualsIsIsolated() {
        return isolatedIndividuals.isEmpty();
    }

    /**
     * Returns the number of components.
     *
     * @return the number of components (between {@code 1} and {@code n})
     */
    public int count() {
        return count;
    }
  
    /**
     * Returns the component identifier for the component containing site {@code p}.
     *
     * @param  p the integer representing one object
     * @return the component identifier for the component containing site {@code p}
     * @throws IllegalArgumentException unless {@code 0 <= p < n}
     */
    public int find(int p) {
        validate(p);
        while (p != parent[p])
            p = parent[p];
        return p;
    }

    // validate that p is a valid index
    private void validate(int p) {
        int n = parent.length;
        if (p < 0 || p >= n) {
            throw new IllegalArgumentException("index " + p + " is not between 0 and " + (n-1));  
        }
    }

    /**
     * Returns true if the the two sites are in the same component.
     *
     * @param  p the integer representing one site
     * @param  q the integer representing the other site
     * @return {@code true} if the two sites {@code p} and {@code q} are in the same component;
     *         {@code false} otherwise
     * @throws IllegalArgumentException unless
     *         both {@code 0 <= p < n} and {@code 0 <= q < n}
     */
    public boolean connected(int p, int q) {
        return find(p) == find(q);
    }

    /**
     * Merges the component containing site {@code p} with the 
     * the component containing site {@code q}.
     *
     * @param  p the integer representing one site
     * @param  q the integer representing the other site
     * @throws IllegalArgumentException unless
     *         both {@code 0 <= p < n} and {@code 0 <= q < n}
     */
    public void union(int p, int q) {
        int rootP = find(p);
        int rootQ = find(q);
        if (rootP == rootQ) return;

        // make smaller root point to larger one
        if (size[rootP] < size[rootQ]) {
            parent[rootP] = rootQ;
            size[rootQ] += size[rootP];
            
            // (M) Update biggestComponentSize
            if (size[rootQ] > biggestComponentSize) biggestComponentSize = size[rootQ];
        }
        else {
            parent[rootQ] = rootP;
            size[rootP] += size[rootQ];
            
            // (M) Update biggestComponentSize
            if (size[rootP] > biggestComponentSize) biggestComponentSize = size[rootQ];
        }
        
        isolatedIndividuals.remove(rootP);
        isolatedIndividuals.remove(rootQ);
        
        count--;
    }
}

	//T = Times we run the experiment, N = Number of nodes. 
	private int initalT, T, N;

	//The arrays in which the time of events will be placed. 
	private int[] giantEmergences, connectedEmergences, noIsolatedEmergences;

	//Stop time to time the events
	private Stopwatch stopwatch;

	//Arrays to store timestamps
	private double[] giantElapsedTime, connectedElapsedTime, noIsolatedElapsedTime;
	private double totalTime;

	public void run(int t, int n){
		this.T = t;
		this.N = n;
		this.initalT = t;

		this.initiateExperiment();
	}

	public static void main(String[] args){
		int t = 10;
		int n = 100;

		if (args.length == 2) {
			t = Integer.parseInt(args[0]);
			n = Integer.parseInt(args[1]);
		}

		GiantBook gb = new GiantBook();
		gb.run(t,n);
	}

	private void initiateExperiment(){
		this.initiateLists();
		this.startExperiment();

		//valuate if the experiment is valid. If it is, end it and produce statistics
		//if not, increase T and run again.
		if(this.experimentPass()){
			this.produceStatistics();
		} else{
			this.getFailureMessage();
			this.T += this.initalT;
			this.initiateExperiment();
		}
	}

	private void initiateLists(){
		//Resetting each array
		this.giantEmergences = null;
		this.connectedEmergences = null;
		this.noIsolatedEmergences = null;

		//Setting the size of arrays = number of times we run the experiment
		this.giantEmergences		= new int[this.T];
		this.connectedEmergences 	= new int[this.T];
		this.noIsolatedEmergences	= new int[this.T];

		//Stopclock array
		this.giantElapsedTime			= new double[this.T];
		this.connectedElapsedTime		= new double[this.T];
		this.noIsolatedElapsedTime		= new double[this.T];
	}

	private void startExperiment(){
		Stopwatch totalTimeWatch = new Stopwatch();
		//We will run the loop to fill the arrays
		for(int i = 0; i<this.T; i++){
			//As long as continue = true, run the experiment
			boolean activeExperiment = true; 
			//to keep track of the lap
			int experimentLap = 0;
			//starting stopwatch
			stopwatch = new Stopwatch();

			//Set to -1 because lap 0 is a value, so we want this tracked.
			int giantEmerged 		= -1;
			int connectedEmerged 	= -1;
			int noIsolatedEmerged 	= -1;

			//Creating the union-find object
			MyUnionFind uf = new MyUnionFind(this.N);

			while(activeExperiment){
				//Check if the 3 events have occured
				//checking for giantEmerged
				if(giantEmerged == -1 && uf.getBiggestComponentSize() > this.N/2){
					giantEmerged = experimentLap;
					giantElapsedTime[i] = stopwatch.elapsedTime();
				}

				//checking for noIsolatedEmerged
				if(noIsolatedEmerged == -1 && uf.noIndividualsIsIsolated()){
					noIsolatedEmerged = experimentLap;
					noIsolatedElapsedTime[i] = stopwatch.elapsedTime();
				}

				//checking if all are connected
				if(uf.count() == 1){
					connectedEmerged = experimentLap;
					connectedElapsedTime[i] = stopwatch.elapsedTime();
					//If everything is connected, the experiment ends.
					activeExperiment = false; 
				}

				//Creating new unions
				int p = StdRandom.uniform(N);
				int q = StdRandom.uniform(N);

				//Check if p and q aren't connected and the same value.
				if (!uf.connected(p, q) && p != q) uf.union(p, q);

                //Go to next lap
                experimentLap++;
			}
			//Adding the result to the list
			this.giantEmergences[i] = giantEmerged;
			this.connectedEmergences[i] = connectedEmerged;
			this.noIsolatedEmergences[i] = noIsolatedEmerged;
		}
		this.totalTime = totalTimeWatch.elapsedTime();
	}

	private boolean experimentPass(){
		boolean hasPassed = true;

		//If the number of nodes is greater than 1000 we want to check the standard deviation
		if (this.N > 1000) {
			//We want the standard deviation to be less than the mean value divided by 7.5. 
            if (StdStats.stddev(this.giantEmergences) > StdStats.mean(this.giantEmergences) / 7.5)
                hasPassed = false;
            if (StdStats.stddev(this.noIsolatedEmergences) > StdStats.mean(this.noIsolatedEmergences) / 7.5)
                hasPassed = false;
            if (StdStats.stddev(this.connectedEmergences) > StdStats.mean(this.connectedEmergences) / 7.5)
                hasPassed = false;
        }
        return hasPassed;
	}

	//Creating statistics using the StringBuilder
	private void produceStatistics(){
		StringBuilder sb = new StringBuilder();

		sb.append("T: " + this.T + "\n");
		sb.append("N: " + this.N + "\n");
		sb.append("\n");
		sb.append("Giant Component: " + StdStats.mean(this.giantEmergences) + "\n");
        sb.append("Giant Component (stddev): " + StdStats.stddev(this.giantEmergences) + "\n");
		sb.append("Time elapsed: " + StdStats.stddev(giantElapsedTime) + " seconds\n");
        sb.append("\n");
        sb.append("No Isolated: " + StdStats.mean(this.noIsolatedEmergences) + "\n");
        sb.append("No Isolated (stddev): " + StdStats.stddev(this.noIsolatedEmergences) + "\n");
        sb.append("Time elapsed: " + StdStats.stddev(noIsolatedElapsedTime) + " seconds\n");
        sb.append("\n");
        sb.append("Connected: " + StdStats.mean(this.connectedEmergences) + "\n");
        sb.append("Connected (stddev): " + StdStats.stddev(this.connectedEmergences) + "\n");
        sb.append("Time elapsed: " + StdStats.stddev(connectedElapsedTime) + " seconds\n");
        sb.append("\n");
        sb.append("Total time " + totalTime + "\n");

        StdOut.println(sb);
        createReport(sb);
        StdOut.println("A report has been generated");
	}

	//Create a txt file using StringBuilder and BufferedWriter
	private void createReport(StringBuilder sb){
		//Creating a name with the given T and N values
		String randomName = this.T + "-" + this.N;

        //Creating the file
        File file = new File("reports/" + randomName + ".txt");
        
        BufferedWriter writer = null;
        try {
            //Write into the file
            writer = new BufferedWriter(new FileWriter(file));

            //Array of strings, splitted by \n
            String[] lines = sb.toString().split("\n");
            writer.write("----------------------------------------");
            writer.newLine();
            for (String line : lines) {
                writer.write(line);
                writer.newLine();
            }
            writer.write("----------------------------------------");
            writer.close();
        }
        catch (IOException iex) {
            StdOut.println(iex.getMessage());
        }
	}

	private void getFailureMessage() {
        StdOut.println("The experiment was rejected: Standard deviation too high");
        StdOut.println("T is extended, and experiment is restarted...");
        StdOut.println();
    }
}