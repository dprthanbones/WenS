import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Algorithm2 extends Algorithm {

	ArrayList<Cirkel> active;
	ArrayList<Cirkel> event;
	
	public Algorithm2(ArrayList<Cirkel> cirkels, Main main) {
		super(main);
		ArrayList<Cirkel> active = new ArrayList<Cirkel>();
		ArrayList<Event> event = getEvents(cirkels);
		System.out.println("start search");
		search(event);
		// TODO Auto-generated constructor stub
	}
	
	class Event{
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
	}
	
	public Comparator<Event> getCompByName(){   
		Comparator comp = new Comparator<Event>(){
		@Override
	    public int compare(Event e1, Event e2){
			if (e1.getX() < e2.getX())
				return -1;
			else if (e1.getX() > e2.getX())
				return 1;
			else return 0;
	    }      
	};
	return comp;
	}
	
	public ArrayList<Event> getEvents(ArrayList<Cirkel> cirkel){
		ArrayList<Event> event = new ArrayList<Event>();
		for(Cirkel temp : cirkel){
			Event r = new Event(temp.getMiddelpunt().getX()+temp.getStraal(), false, temp);
			event.add(r);
			Event l = new Event(temp.getMiddelpunt().getX()-temp.getStraal(), true, temp);
			event.add(l);
		}
		Collections.sort(event, getCompByName());
		return event;
	}
	
	private void search(ArrayList<Event> events) {
		for(Event temp: events){
			System.out.println(this.active.size());
			if(temp.getLinks() == true){
				for(Cirkel cirk:this.active){
					addSnijnpunt(temp.getCirkel(),cirk);
				}
				this.active.add(temp.getCirkel());
			}
			else{
				try{
					this.active.remove(temp.getCirkel());
					//System.out.println("succes");
				}catch (NullPointerException e){
					//System.out.println("error");
				}
				
			}
		}
	}
}	