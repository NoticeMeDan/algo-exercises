import edu.princeton.cs.algs4.*;

public class Congress {
	
	private int states;
	private int seats;
	private MaxPQ<State> pq;

	public Congress(int states, int seats){
		this.states = states;
		this.seats = seats;
		this.pq = new MaxPQ<State>();
	}

	private void insertState(State state){
		pq.insert(state);
	}

	private State delegateSeat(State state){
		state.addSeat();
		this.seats--;
		return state;
	}

	private int getSeats(){
		return this.seats;
	}

	private State delMax(){
		return this.pq.delMax();
	}

	private void print(){
		for(State s : this.pq){
			System.out.println(s);
		}
	}

	public static void main(String[]args){
		Congress c = new Congress(StdIn.readInt(),StdIn.readInt());

		while(!StdIn.isEmpty()){
			StdIn.readLine();
			State state = new State(StdIn.readLine(),StdIn.readDouble());
			c.delegateSeat(state);
			c.insertState(state);
		}

		while(c.getSeats()!=0){
			State state = c.delMax();
			state = c.delegateSeat(state);
			c.insertState(state);
		}
		c.print();
	}
}