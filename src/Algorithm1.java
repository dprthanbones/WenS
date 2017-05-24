import java.util.ArrayList;

public class Algorithm1 extends Algorithm{

	public Algorithm1(ArrayList<Cirkel> cirkels, Main main){
		super(main);
		long startTime = System.nanoTime();
		search(cirkels);
		long endTime = System.nanoTime();
		setTime((endTime - startTime)/1000000);
	}

	private void search(ArrayList<Cirkel> cirkels) {
		for (int i = 0; i < cirkels.size(); i++ ){
			for(int j = i + 1; j < cirkels.size() ; j++){
				addSnijnpunt(cirkels.get(i),cirkels.get(j));
			}
		}	
	}	
}
