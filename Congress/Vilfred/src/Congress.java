import edu.princeton.cs.algs4.*;
import java.util.*;
import java.lang.*;

public class Congress <Key extends Comparable<Key>>{
	private MaxPQ<State> pq;
	private int availableSeats;

	public Congress(State[] states, int numberOfSeats){
		this.pq = new MaxPQ<>();
		this.availableSeats = numberOfSeats;
		
		for(State state : states){
			state.addSeat();
			this.availableSeats --;
			this.pq.insert(state);
		}
	}

	public String toString() {
        StringBuilder sb = new StringBuilder();
        for (State state : this.pq) {
            sb.append(state.toString());
            sb.append('\n');
        }
        return sb.toString();
    }

	private void assignSeats(){
		for(int i = 0; i < this.availableSeats; i++){
			State s = this.pq.delMax();
			s.addSeat();
			this.pq.insert(s);
			this.availableSeats --;
		}
	}

	public static void main(String[] args){
		Scanner S = new Scanner(System.in);
        int numStates = Integer.parseInt(S.nextLine());
        int numSeats  = Integer.parseInt(S.nextLine());
        State[] states = new State[numStates];

        for (int i = 0; i < numStates; i++) {
            String stateName = S.nextLine();
            int statePop = Integer.parseInt(S.nextLine());
            states[i] = new State(stateName, statePop);
        }
        S.close();

        Congress c = new Congress(states, numSeats);
        c.assignSeats();
        System.out.print(c);
	}
}
