import edu.princeton.cs.algs4.*;
import java.util.*;

public class GiantBook{

    private static int T;
    private static int N;
    private static int[] nonisolatedNetwork;
    private static int[] giantComponents;
    private static int[] connectedNetwork;
    private static int giantComponent;
    private static int connectedComponent;
    private static int nonisolatedComponents;


    public static void main(String[] args){
        if (args.length==0){
            T = 100;
            N = 100;
        } else{

            T = Integer.parseInt(args[0]);
            N = Integer.parseInt(args[1]);
        }

        initializeElements();
        start();
        if(experimentEvaluationPass()){
            calculateData();
            }else {
                System.out.print("Evaluation failed...");
            }
    }

    public static void start() {
        for (int i = 0; i < T; i++) {

            resetComponents();

            MyUnionFind uf = new MyUnionFind(N);

            for(int iteration = 0; true; iteration++){
                if (uf.nonIsolatedComponents() && nonisolatedComponents == -1) {
                    nonisolatedComponents = iteration;
                }

                if (uf.getGreatestComponent() >= (N / 2) && giantComponent == -1) {
                    giantComponent = iteration; 
                }

                if(uf.count()==1){
                    connectedComponent = iteration;
                    break;
                }

                int p = StdRandom.uniform(N);
                int q = StdRandom.uniform(N);

                if (p == q || uf.connected(p,q)) {
                    continue;
                }

                uf.union(p, q);
            }

            nonisolatedNetwork[i] = nonisolatedComponents;
            giantComponents[i] = giantComponent;
            connectedNetwork[i] = connectedComponent;
        }
    }

    private static void initializeElements(){
        nonisolatedNetwork = new int[T];
        giantComponents = new int[T];
        connectedNetwork = new int[T];
    }

    private static void resetComponents(){
        giantComponent = -1;
        connectedComponent = -1;
        nonisolatedComponents = -1;
    }

    private static void calculateData(){
        StdOut.println("T: " + T);
        StdOut.println("N: " + N);
        StdOut.println("GiantComponent mean: " + StdStats.mean(giantComponents));
        StdOut.println("GiantComponent stddev: " + StdStats.stddev(giantComponents));
        StdOut.println("NonisolatedNetwork mean: " + StdStats.mean(nonisolatedNetwork));
        StdOut.println("NonisolatedNetwork stddev: " + StdStats.stddev(nonisolatedNetwork));
        StdOut.println("ConnectedNetworks mean: " + StdStats.mean(connectedNetwork));
        StdOut.println("ConnectedNetworks stddev: " + StdStats.stddev(connectedNetwork));
    }

     private static boolean experimentEvaluationPass() {
        boolean evaluationPassed = true;
        if (N > 1000) {
            if (StdStats.stddev(giantComponents) > StdStats.mean(giantComponents) / 7.5)
                evaluationPassed = false;
            if (StdStats.stddev(nonisolatedNetwork) > StdStats.mean(nonisolatedNetwork) / 7.5)
                evaluationPassed = false;
            if (StdStats.stddev(connectedNetwork) > StdStats.mean(connectedNetwork) / 7.5)
                evaluationPassed = false;
        }
        return evaluationPassed;
    }
}