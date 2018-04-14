// File: Tests.java
// Basically a copy-paste from CodeJudge
// Will not be handed in
import edu.princeton.cs.algs4.StdDraw;
import java.util.HashMap;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
public class Test {
    public static void main(String[] args) {
        // Test 
        int i=0;
        //        String [] in = new String[0];
        String [] in = new String[26];
        i=0;
        for(char c = 'A'; c <= 'Z'; c++ ) in[i++] = "" + c;
                 
        HashPipe H = new HashPipe();
 
        for(int j = 0; j < in.length; j++) {
                H.put(in[j], j);
                
                System.out.print("Insert: ");
                System.out.println(in[j]);

                for(int g = 0; g < j; g++) {

                    for(int h = 0; h < 32; h++) {
                        String ctrl = H.control(in[g], h);
                        if (ctrl != null) System.out.print(ctrl);
                        else System.out.print(".");
                        System.out.print(" ");
                    }

                    System.out.print(" : ");
                    System.out.println(in[g]);
                }

            }
    }
}