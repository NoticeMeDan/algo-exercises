import edu.princeton.cs.algs4.*;
import java.util.*;
import java.awt.*;

public class GiantBookAdjusted {
    private static int N;
    private static ArrayList<Point> unionPairs;
    
    private static int experimentLap;
    private static int giantEmerged;
    private static int noisolatedEmerged;
    private static int connectedEmerged;
    
    public static void main(String[] args) {
        unionPairs = new ArrayList<>();
        N = StdIn.readInt();
        while (!StdIn.isEmpty()) {
            int p = StdIn.readInt();
            int q = StdIn.readInt();
            Point pair = new Point(p, q);
            unionPairs.add(pair);
        }
        
        runExperiment();
        StdOut.println(N + " " + noisolatedEmerged + " " + giantEmerged + " " + connectedEmerged);
    }
    
    private static void runExperiment() {
        experimentLap = 1;
        giantEmerged = -1;
        noisolatedEmerged = -1;
        connectedEmerged = -1;
        
        MyUnionFind uf = new MyUnionFind(N);
                
        for (Point pair : unionPairs) {
            int p = new Double(pair.getX()).intValue();
            int q = new Double(pair.getY()).intValue();
            
            if (p != q && !uf.connected(p, q)) uf.union(p, q);
            
            if (giantEmerged == -1 && uf.getBiggestComponentSize() >= N / 2.0) 
                giantEmerged = experimentLap;
            
            if (noisolatedEmerged == -1 && uf.noIndividualsIsIsolated()) 
                noisolatedEmerged = experimentLap;
            
            if (uf.count() == 1) 
                connectedEmerged = experimentLap;
            
            experimentLap++;
        }
    }
    
}