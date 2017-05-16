import java.util.ArrayList;

public class Algorithm1 extends Algorithm{

	public Algorithm1(ArrayList<Cirkel> cirkels, Main main){
		super(main);
		search(cirkels);
	}

	private void search(ArrayList<Cirkel> cirkels) {
		System.out.println(cirkels.size());
		long startTime = System.nanoTime();
		for (int i = 0; i < cirkels.size(); i++ ){
			for(int j = i + 1; j < cirkels.size() ; j++){
				addSnijnpunt(cirkels.get(i),cirkels.get(j));
			}
		}	
		long endTime = System.nanoTime();
		setTime(endTime - startTime);
	}	
}
