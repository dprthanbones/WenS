import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.TreeMap;
import java.util.TreeSet;

public class Algorithm3 extends Algorithm {

	public double MaxStraal;
	
	public Algorithm3(ArrayList<Cirkel> cirkels, Main main) {
		super(main);		
		this.MaxStraal = 0;
		ArrayList<Event> event = getEvents(cirkels);
		long startTime = System.nanoTime();
		search(event);		
		long endTime = System.nanoTime();
		setTime((endTime - startTime)/1000000);
	}
	
	private void search(ArrayList<Event> events) {
		TreeSet<Cirkel> sweepLine = new TreeSet<Cirkel>((c1,c2)-> Double.compare(c2.getMiddelpunt().getY(), c1.getMiddelpunt().getY()));
		TreeSet<Cirkel> straal = new TreeSet<Cirkel>((c1,c2)-> Double.compare(c2.getStraal(), c1.getStraal()));
		for(Event temp: events){
			//temp is startpoint
			if(temp.getLinks() == true){
				sweepLine.add(temp.getCirkel());
				straal.add(temp.getCirkel());
				if(sweepLine.size() != 0){
					Cirkel c1 = new Cirkel(new Punt(temp.getX(), temp.getY() + temp.getCirkel().getStraal() + this.MaxStraal), 0.0);
					Cirkel c2 = new Cirkel(new Punt(temp.getX(), temp.getY() - temp.getCirkel().getStraal() - this.MaxStraal), 0.0);								
					//subset of cirkels 
					TreeSet<Cirkel> sub = (TreeSet<Cirkel>) sweepLine.subSet(c1,c2);					
					for(Cirkel c : sub){							
							addSnijnpunt(temp.getCirkel(), c);
						}
				}
				if (temp.cirkel.getStraal() > this.MaxStraal){
					this.MaxStraal = temp.getCirkel().getStraal();
				}
			}
			//temp is endpoint
			else{
				sweepLine.remove(temp.getCirkel());
				if(this.MaxStraal == temp.getCirkel().getStraal()){
					this.MaxStraal = straal.first().getStraal();
				}
				straal.remove(temp.getCirkel());
						
			}
		}
	}
}