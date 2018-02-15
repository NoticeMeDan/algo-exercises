import java.util.Scanner;
import java.util.Arrays;

public class Faster{
    public static void main(String[] args){
		Scanner S 	= new Scanner(System.in);
		int N 		= Integer.parseInt(S.nextLine());
		long[] vals = new long[N];

		

		for(int i = 0; i < N; i++) vals[i] = Long.parseLong(S.nextLine());

		Arrays.sort(vals);

		for(int i = 0; i < N; i++) {
			for(int j = i+1; j < N; j++){
				for(int k = j+1; k < N; k++){
					int l = Arrays.binarySearch(vals, -1 * (vals[i] + vals[j] + vals[k]));
					if(l>k){
						System.out.println("true");
						System.out.println(vals[i] + " " + vals[j] + " " + vals[k] + " " + l);
						System.exit(0);
					}
				}
			}			
		}
		System.out.println("false");
    }
}