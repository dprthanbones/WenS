
public class Event {

	double xCoordinaat;
	boolean links;
	Cirkel cirkel;
	
	public Event (double x, boolean bool, Cirkel cirkel){
		this.xCoordinaat= x;
		this.links = bool;
		this.cirkel = cirkel;
	}
	
	public boolean getLinks(){
		return this.links;
	}
	
	public double getX(){
		return this.xCoordinaat;
	}
	
	public Cirkel getCirkel(){
		return this.cirkel;
	}
	
	public double getY(){
		return this.getCirkel().getMiddelpunt().getY();
	}
	
}
