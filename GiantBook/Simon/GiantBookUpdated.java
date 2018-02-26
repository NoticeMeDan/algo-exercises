import edu.princeton.cs.algs4.*;
import java.util.*;
import java.awt.*;
import java.io.*;
import java.text.*;

public class GiantBookUpdated {
    private static int initialT;
    private static int N, T = 1;
    private static ArrayList<Point> unionPairs;
    private static Stopwatch stopwatch;

    // three lists of test testresult (to take average from later)
    private static int[] giantEmergences;
    private static int[] noisolatedEmergences;
    private static int[] connectedEmergences;
    
    private static double[] giantComponentElapsedTimes;
    private static double[] noisolatedElapsedTimes;
    private static double[] connectedElapsedTimes;
    
    // Takes T and N as args in the order: T, N
    public static void main(String[] args) {
        unionPairs = new ArrayList<>();
        N = StdIn.readInt();
        while (!StdIn.isEmpty()) {
            int p = StdIn.readInt();
            int q = StdIn.readInt();
            Point pair = new Point(p, q);
            unionPairs.add(pair);
        }
        
        initialT = T;
        initiateExperiment(T);
    }
    
    // Initializes the experiment with t rows
    private static void initiateExperiment(int t) {
        initializeEmergenceLists(T);
        startExperiment(T);
        
        // If standard deviation is to high (experimentEvaluationPass() is false)
        // then add initialT to T and rerun the experiment
        if (experimentEvaluationPass()) {
            printOutput();
            //produceStatistics();
        }
        else {
            showRejectMessage();
            T += initialT;
            initiateExperiment(T);
        }
    }
    
    // The actual experiment
    private static void startExperiment(int t) {
        
        // run the experiment t times
        for (int i = 0; i < t; i++) {
            stopwatch = new Stopwatch();
            boolean activeExperiment = true;
            // Log
            int experimentLap = 0;
            
            // log at which experiment lap the 3 criterias emerges
            int giantEmerged = -1;
            int noisolatedEmerged = -1;
            int connectedEmerged = -1;
            
            MyUnionFind uf = new MyUnionFind(N);
            
            // Keep linking nodes as long as needed (until the whole network is one big component)
            while (activeExperiment && experimentLap < unionPairs.size()) {
                
                // Check if giant components has emerged
                // Log the experimentLap if so
                if (giantEmerged == -1 && uf.getBiggestComponentSize() > N / 2) {
                    giantEmerged = experimentLap;
                    giantComponentElapsedTimes[i] = stopwatch.elapsedTime();
                    StdOut.println("Giant Comp: " + uf.getBiggestComponentSize() + " at i = " + experimentLap);
                }
                
                
                // Check if no isolated has emerged
                // Log the experimentLap if so
                if (noisolatedEmerged == -1 && uf.noIndividualsIsIsolated()) {
                    noisolatedEmerged = experimentLap;
                    noisolatedElapsedTimes[i] = stopwatch.elapsedTime();
                    StdOut.println("No isolated: " + uf.noIndividualsIsIsolated() + " at i = " + experimentLap);
                }
                
                // Check if the network is one big component
                // Log the experimentLap and end the experiment if so
                if (uf.count() == 1) {
                    connectedEmerged = experimentLap;
                    connectedElapsedTimes[i] = stopwatch.elapsedTime();
                    activeExperiment = false;
                    StdOut.println("Component count: " + uf.count() + " at i = " + experimentLap);
                }
                
                // Randomly union two nodes with values between 0 and N (exlusive)
                int p = new Double(unionPairs.get(experimentLap).getX()).intValue();
                int q = new Double(unionPairs.get(experimentLap).getY()).intValue();
                
                if (!uf.connected(p, q) && p != q) uf.union(p, q);
                
                StdOut.println("Link: " + p + " and " + q + " at i = " + experimentLap);
                
                
                experimentLap++;
            }
            
            // Add emerged-results to list
            giantEmergences[i] = giantEmerged;
            noisolatedEmergences[i] = noisolatedEmerged;
            connectedEmergences[i] = connectedEmerged;
        }
    }
    
    // Name should be self-explainatory
    private static void initializeEmergenceLists(int t) {
        giantEmergences = new int[t];
        noisolatedEmergences = new int[t];
        connectedEmergences = new int[t];
        giantComponentElapsedTimes = new double[t];
        noisolatedElapsedTimes = new double[t];
        connectedElapsedTimes = new double[t];
    }
    
    // Evaluate if standard deviation is too high (and therefor test-results is useless)
    private static boolean experimentEvaluationPass() {
        boolean evaluationPassed = true;
        if (N > 1000) {
            if (StdStats.stddev(giantEmergences) > StdStats.mean(giantEmergences) / 7.5)
                evaluationPassed = false;
            if (StdStats.stddev(noisolatedEmergences) > StdStats.mean(noisolatedEmergences) / 7.5)
                evaluationPassed = false;
            if (StdStats.stddev(connectedEmergences) > StdStats.mean(connectedEmergences) / 7.5)
                evaluationPassed = false;
        }
        return evaluationPassed;
    }
    
    // Produce the statistiscs using stringBuilder
    // Print output and create report
    private static void produceStatistics() {
        StringBuilder sb = new StringBuilder();
        
        sb.append("T: " + T + "\n");
        sb.append("N: " + N + "\n");
        sb.append("\n");
        sb.append("Giant Component: " + StdStats.mean(giantEmergences) + "\n");
        sb.append("Giant Component (stddev): " + StdStats.stddev(giantEmergences) + "\n");
        sb.append("Time elapsed: " + StdStats.stddev(giantComponentElapsedTimes) + " seconds\n");
        sb.append("\n");
        sb.append("No Isolated: " + StdStats.mean(noisolatedEmergences) + "\n");
        sb.append("No Isolated (stddev): " + StdStats.stddev(noisolatedEmergences) + "\n");
        sb.append("Time elapsed: " + StdStats.stddev(noisolatedElapsedTimes) + " seconds\n");
        sb.append("\n");
        sb.append("Connected: " + StdStats.mean(connectedEmergences) + "\n");
        sb.append("Connected (stddev): " + StdStats.stddev(connectedEmergences) + "\n");
        sb.append("Time elapsed: " + StdStats.stddev(connectedElapsedTimes) + " seconds\n");
        sb.append("\n");

        //createReport(sb);
    }
    
    // Satisfy the CodeJugde God with the correct outputs
    private static void printOutput() {
        StdOut.println(N + " " + StdStats.mean(noisolatedEmergences) + " " + StdStats.mean(giantEmergences) + " " + StdStats.mean(connectedEmergences));
    }
    
    // Create new txt-file and write the StringBuilder to it
    private static void createReport(StringBuilder sb) {
        String randomName = T + "-" + N;
        
        File file = new File("reports/" + randomName + ".txt");
        
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter(file));
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
    
    private static void showRejectMessage() {
        StdOut.println("The experiment was rejected: Standard deviation too high");
        StdOut.println("T is extended, and experiment is restarted...");
        StdOut.println();
    }
}