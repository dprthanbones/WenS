import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Algorithm2 extends Algorithm {

	public ArrayList<Cirkel> event;
	
	public Algorithm2(ArrayList<Cirkel> cirkels, Main main) {
		super(main);		
		ArrayList<Event> event = getEvents(cirkels);
		long startTime = System.nanoTime();
		search(event);
		long endTime = System.nanoTime();
		setTime((endTime - startTime)/1000000);
	}
	
	private void search(ArrayList<Event> events) {	
		ArrayList<Cirkel> sweepLine = new ArrayList<Cirkel>();
		for(Event temp: events){
			if(temp.getLinks() == true){
				for(Cirkel cirk:sweepLine){
					addSnijnpunt(temp.getCirkel(),cirk);
				}
				sweepLine.add(temp.getCirkel());
			}
			else{
				sweepLine.remove(temp.getCirkel());	
			}
		}	
	}
}