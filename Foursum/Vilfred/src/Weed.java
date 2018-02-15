import java.util.Random;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

public class Weed{
    public static void main(String[] args){
	int N = Integer.parseInt(args[0]);
	List<Long> vals = new ArrayList<>();
	Random R = new Random(Integer.parseInt(args[1]));

	vals.add(R.nextLong());
	vals.add(R.nextLong());
	vals.add(R.nextLong());
	vals.add(-(vals.get(0) + vals.get(1) + vals.get(2)));
       	if (R.nextBoolean())  vals.set(3,vals.get(3)+1);
	for (int i = 4; i<N; ++i) vals.add(R.nextLong());
	
	Collections.shuffle(vals,R);

	System.out.println(N);
	for (int i = 0; i<N; ++i) System.out.println(vals.get(i));

//		for (int i = 0; i<N; ++i)
//	    	for (int j = i+1; j<N; ++j)
//			for (int k = j+1; k<N; ++k)
//		    	for (int l = k+1; l<N; ++l)
//					if (vals.get(i) + vals.get(j) + vals.get(k) + vals.get(l) == 0) {
//						System.out.println(i+" "+j+" "+k+" "+l);
//			 			System.exit(0);
//		}
    }
}
