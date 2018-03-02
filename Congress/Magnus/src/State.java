public class State implements Comparable<State>{

	private String name;
	private double population;
	private int seats;
	private double ebola;

	public State(String name, double population){
		this.name = name;
		this.population = population;
		this.seats = 1;
		this.ebola = 1.0;
	}

	public int getSeats(){
		return seats;
	}

	public String getName(){
		return this.name;
	}

	public void setPopulation(double population){
		this.population = population;
	}

	public void addSeat(){
		this.seats++;
		this.ebola = Math.sqrt(this.seats*(this.seats+1));
	}

	//Reduce the population according to the huntington hill method
	public double spreadEbola(){ 
		return (this.population/this.ebola);
	}

	@Override
	public String toString(){
		return this.name + " " + this.seats;
	}

	@Override
	public int compareTo(State state) {
	return Double.compare(this.spreadEbola(), state.spreadEbola());
	}
}