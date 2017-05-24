import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class InputGenerator {

	public static void main(String[] args){
		new InputGenerator(10, 1);
	}
	
	public InputGenerator(int n, int alg){
		try {
			writeInput(n,alg);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void writeInput(int n, int alg) throws IOException{
		FileWriter in;
		in = new FileWriter(System.getProperty("user.dir") + File.separator + "input.txt");
		in.write(String.valueOf(alg));
		in.write(System.lineSeparator());
		in.write(String.valueOf(n));
		in.write(System.lineSeparator());
		for(int i = 0; i < n; i++){
			double x = generator(0.0, 1.0);
			double y = generator(0.0, 1.0);
			double r = generator(0.01, 0.5);
			in.write(String.valueOf(x + " " + y + " " + r));
			in.write(System.lineSeparator());
		}in.flush();
		in.close();
	}
	
	public double generator(double rangeMin, double rangeMax){
		Random r = new Random();
		double randomValue = rangeMin + (rangeMax - rangeMin) * r.nextDouble();
		return randomValue;
	}
	
}
