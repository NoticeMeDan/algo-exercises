public class State implements Comparable<State> {
	private String name;
	private double population;
	private int seats;

	public State(String name, double population){
		this.name = name;
		this.population = population;
		this.seats = 0;
	}

	public double getPopulation(){
		return this.population;
	}

	public void addSeat(){
		this.seats++;
	}

	private double getQuota(){
		double divisor = Math.sqrt(this.seats*(this.seats+1));
		return this.population/divisor;
	}

	@Override
	public String toString(){
		return this.name + " " + this.seats;
	}

	@Override
	public int compareTo(State state) {
	return Double.compare(this.getQuota(), state.getQuota());
	}
}