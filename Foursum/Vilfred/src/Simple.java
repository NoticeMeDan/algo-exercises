import java.util.Scanner;

public class Simple {
    public static void main(String[] args){
		Scanner S 	= new Scanner(System.in);
		int N 		= Integer.parseInt(S.nextLine());
		long[] vals = new long[N];

		for(int i = 0; i < N; i++) vals[i] = Long.parseLong(S.nextLine());
		
		for(int i = 0; i < N; i++) {
			for(int j = i+1; j < N; j++){
				for(int k = j+1; k < N; k++){
					for(int l = k+1; l < N; l++){
						if (vals[i] + vals[j] + vals[k] + vals[l] == 0) {
						    System.err.println(i+" "+j+" "+k+" "+l);
						    System.out.println(true);
						    System.exit(0);
						}
					}
				}
			}			
		}
    	System.out.println(false);
   	}
}
