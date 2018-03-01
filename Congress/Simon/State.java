public class State implements Comparable<State> {
    private final String name;
    private int seats;
    private int population;
    private double priority;
    
    public State(String name, int population) {
        this.name = name;
        this.population = population;
        this.priority = 0;
        this.seats = 0;
    }
    
    public int getPopulation() { return this.population; }
    
    public double getPriority() { return this.priority; }
    
    public void setPriority(double priority) { this.priority = priority; }
    
    public int getSeats() { return this.seats; }
    
    public void setPopulation(int population) { this.population = population; }
    
    public void addSeat() { this.seats++; }
    
    public String toString() { return String.format("%s %d", this.name, this.seats); }
    
    @Override
    public int compareTo(State state) { return Double.compare(this.getPriority(), state.getPriority()); }
}