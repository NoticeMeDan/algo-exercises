import java.util.Scanner;

public class Simple {
    public static void main(String[] args) {
        // For scanner method
        Scanner S= new Scanner(System.in);
        int N = Integer.parseInt(S.nextLine());
        long[] vals = new long[N];
        for(int i= 0; i < N; i+= 1) vals[i] = Long.parseLong(S.nextLine());
        
        // For directory method
        /* int N = Integer.parseInt(args[0]);
        long[] vals = new long[N];
        for(int i = 1; i <= N; i+= 1) vals[i-1] = Long.parseLong(args[i]); */

        for (int i = 0; i < N; i++)
            for (int j = i + 1; j < N; j++)
                for (int k = j + 1; k < N; k++)
                    for (int l = k + 1; l < N; l++)
                        if (vals[i] + vals[j] + vals[k] + vals[l] == 0) {
                             System.err.println(i+" "+j+" "+k+" "+l);
                             System.out.println(true);
                             return;
                        }
        System.out.println(false);
    }
}
