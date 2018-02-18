import java.util.Scanner;
import java.util.Arrays;

public class Simplefaster
{
    public static void main(String[] args) {
	Scanner S= new Scanner(System.in);
	int N = Integer.parseInt(S.nextLine());
	long[] vals = new long[N];
	for (int i= 0; i < N; i+= 1) vals[i] = Long.parseLong(S.nextLine());
       
       	// your code goes here and uses the following
		Arrays.sort(vals);
       	for (int i = 0; i < N; i++) {
            for (int j = i+1; j < N; j++) {
                for (int k = j+1; k < N; k++) {
                    int l = Arrays.binarySearch(vals, -(vals[i] + vals[j] + vals[k]));
					if (l>k) {
						System.out.println();						
		    			System.out.println(true);
						System.out.println("Index: val[" + i + "] , vals[" + j + "] , vals[" + k + "] , vals[" + l + "].");
		    			System.out.println("Values: " + vals[i] + " , " + vals[j] + " , " + vals[k] + " , " + vals[l]);
		    			System.out.println();
			    		System.exit(0);
					}
        		}
        	}
        }            	
        System.out.println(false);
        System.out.println();
    }
}