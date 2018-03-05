public class State implements Comparable<State>{
	private int population, numberOfseats;
	private String name;
	private double divisor;

	State(String name, int population){
		this.population 	= population;
		this.name 			= name; 
		this.numberOfseats 	= 0;
		this.divisor 		= 1.0;
	}

	public void addSeat(){
		this.numberOfseats ++;
		int s = this.numberOfseats;
		this.divisor = Math.sqrt(s*(s+1));
	}

	public int compareTo(State that){ return Double.compare(this.getPriority(), that.getPriority()); }

	private double getPriority(){ return (double) this.population / this.divisor; }

	public String toString(){ return String.format("%s %d", this.name, this.numberOfseats); }
}