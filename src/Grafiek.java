import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Grafiek {

	public static void main(String[] args){
		new Grafiek();
	}
	
	public Grafiek(){
		ArrayList<Punt> v = calcPoints();
		try {
			writeOutputX(v);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}try {
			writeOutputY(v);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public ArrayList<Punt> calcPoints(){
		ArrayList<Punt> PointList = new ArrayList<Punt>();
		for (int i = 1; i < 3011; i = i+10){
			System.out.println(i/30);
			InputGenerator gen = new InputGenerator(i,3);
			Main main = new Main();
			Punt punt = new Punt(i, main.getAlgoritme().getTime());
			PointList.add(punt);
		}return PointList;
	}
	
	
	public void writeOutputX(ArrayList<Punt> v) throws IOException{
		FileWriter out;
		try {
		out = new FileWriter(System.getProperty("user.dir") + File.separator + "y.txt");
		for(int i = 0; i < v.size(); i++){
			out.write(String.valueOf(v.get(i).getY()));
				out.write(System.lineSeparator());
			}		
		out.flush();
		out.close();	
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
	public void writeOutputY(ArrayList<Punt> v) throws IOException{
		FileWriter out;
		try {
		out = new FileWriter(System.getProperty("user.dir") + File.separator + "x.txt");
		for(int i = 0; i < v.size(); i++){
			out.write(String.valueOf(v.get(i).getX()));
				out.write(System.lineSeparator());
			}		
		out.flush();
		out.close();	
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
	
}
