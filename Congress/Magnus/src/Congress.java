import edu.princeton.cs.algs4.*;

public class Congress{
	
	private int seats;
	private int states;
	private MaxPQ<State> pq;

	public Congress(int states, int seats){
		this.states = states;
		this.seats = seats;
		this.pq = new MaxPQ<State>(states);
	}

	public void addState(State state){
		this.pq.insert(state);
	}

	public int getSeats(){
		return this.seats;
	}

	public int getStates(){
		return this.states;
	}

	public State delMax(){
		return this.pq.delMax();
	}

	public void print(){
		for(State s : this.pq){
			System.out.println(s);
		}	
	}
}