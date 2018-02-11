import edu.princeton.cs.algs4.*;
import java.io.*;
import java.text.*;
import java.util.*;

public class GiantBook{
	//T = Times we run the experiment, N = Number of nodes. 
	private int initalT, T, N;
	//The arrays in which the time of events will be placed. 
	private int[] giantEmergences, connectedEmergences, noIsolatedEmergences;

	public void run(int t, int n){
		this.T = t;
		this.N = n;
		this.initalT = t;

		StdOut.printf("Input: N = %s, T = %s\n", this.N, this.T);

		this.initiateExperiment();
	}
	public static void main(String[] args){
		int t = 10;
		int n = 100;

		if (args.length == 2) {
			n = Integer.parseInt(args[0]);
			t = Integer.parseInt(args[1]);
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
	}

	private void startExperiment(){
		//We will run the loop to fill the arrays
		for(int i = 0; i<this.T; i++){
			//As long as continue = true, run the experiment
			boolean activeExperiment = true; 
			//to keep track of the lap
			int experimentLap = 0;

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
				}

				//checking for noIsolatedEmerged
				if(noIsolatedEmerged == -1 && uf.noIndividualsIsIsolated()){
					noIsolatedEmerged = experimentLap;
				}

				//checking if all are connected
				if(uf.count() == 1){
					connectedEmerged = experimentLap;
					//If everything is connected, the experiment ends.
					activeExperiment = false; 
				}

				//Creating new unions
				int p = StdRandom.uniform(N);
				int q = StdRandom.uniform(N);

				//Check if p and q aren't connected and the same value.
				if (!uf.connected(p, q) && p != q) uf.union(p, q);
                //
                experimentLap++;
			}
			//Adding the result to the list
			this.giantEmergences[i] = giantEmerged;
			this.connectedEmergences[i] = connectedEmerged;
			this.noIsolatedEmergences[i] = noIsolatedEmerged;
		}
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

        sb.append("\n");
        sb.append("No Isolated: " + StdStats.mean(this.noIsolatedEmergences) + "\n");
        sb.append("No Isolated (stddev): " + StdStats.stddev(this.noIsolatedEmergences) + "\n");

        sb.append("\n");
        sb.append("Connected: " + StdStats.mean(this.connectedEmergences) + "\n");
        sb.append("Connected (stddev): " + StdStats.stddev(this.connectedEmergences) + "\n");

        sb.append("\n");

        StdOut.println(sb);
        createReport(sb);
        StdOut.println("A report has been generated");
	}

	//Create a txt file using StringBuilder and BufferedWriter
	private void createReport(StringBuilder sb){
		//Creaitng a name with the given T and N values
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