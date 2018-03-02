import edu.princeton.cs.algs4.*;

public class Demo {

	public static void main(String[]args) {
		Congress c = new Congress(StdIn.readInt(),StdIn.readInt());

		while(!StdIn.isEmpty()) {
			StdIn.readLine();
			String name = StdIn.readLine();
			double population = StdIn.readDouble();
			c.addState(new State(name, population));
		}

		int count = 0;
		for(int i=c.getStates(); i<c.getSeats(); i++) {
			State state = c.delMax();
			state.addSeat();
			c.addState(state);
			count++;
		}
			c.print();
			System.out.println(count);
	}
}