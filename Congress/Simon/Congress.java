import edu.princeton.cs.algs4.*;

public class Congress {
    private int statesInCountry, freeSeats;
    private MaxPQ<State> pq;
  
    public Congress(int states, int seats) {
        this.statesInCountry = states;
        this.freeSeats = seats;
        this.pq = new MaxPQ<>(statesInCountry);
    }
  
    public void addState(State state) {
        state.addSeat();
        state.setPriority(calculateSuccessiveQuotients(state));
        this.pq.insert(state);
        freeSeats--;
    }
    
    public void assignSeats() {
        while (freeSeats > 0) {
            State s = this.pq.delMax();
            s.addSeat();
            s.setPriority(calculateSuccessiveQuotients(s));
            this.pq.insert(s);
            freeSeats--;
        }
    }
    
    public void printSeats() {
        for (State state : pq)
            StdOut.println(state);
    }
    
    private double calculateSuccessiveQuotients(State state) {
        int s = state.getSeats();
        int v = state.getPopulation();        
        return v / (Math.sqrt(s * (++s)));
    }
  
}