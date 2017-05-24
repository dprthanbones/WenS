import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public abstract class Algorithm {

	private Main Main;
	private long time = 0;
	
	public Algorithm(Main main){
		setMain(main);
	}
	
	public void setMain(Main main){
		this.Main = main;
	}
	
	public Main getMain(){
		return this.Main;
	}
	
	public void addSnijnpunt(Cirkel cirkel1, Cirkel cirkel2) {
		Punt[] snijpunten = cirkel1.snijpuntAndereCirkel(cirkel2);
		if (snijpunten.length >= 1){
			if(Double.isNaN(snijpunten[0].getX()) || Double.isNaN(snijpunten[0].getY())) return;			
				getMain().addSnijpunten(snijpunten[0]);			
		}if(snijpunten.length == 2){
			if(Double.isNaN(snijpunten[1].getX()) || Double.isNaN(snijpunten[1].getY())) return;			
				getMain().addSnijpunten(snijpunten[1]);
		}
	}
	
	public void setTime(long time){
		this.time = time;
	}
	
	public long getTime(){
		return this.time;
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
}
