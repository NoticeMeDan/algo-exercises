import edu.princeton.cs.algs4.*;

public class Experiment {
    private static Congress congress;
    private static State state;
  
    public static void main(String[] args) {
        int numberOfStates = StdIn.readInt();
        int numberOfSeats = StdIn.readInt();
        congress = new Congress(numberOfStates, numberOfSeats);
        
        while (!StdIn.isEmpty()) {
            StdIn.readLine(); // <- Skip the empty line
            String name = StdIn.readLine();
            int population = StdIn.readInt();
            State s = new State(name, population);
            congress.addState(s);
        }
        
        congress.assignSeats();
        congress.printSeats();
    }
}