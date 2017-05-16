import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
	
	private int algoritme;
	private Algorithm algo;
	private ArrayList<Cirkel> cirkels = new ArrayList<Cirkel>();
	private ArrayList<Punt> snijpunten = new ArrayList<Punt>();
	private boolean error = false;
	
	/**
	/*	Runs application
	 */
	public static void main(String[] args){
		new Main();
	}
	
	/**
	 * constructor
	 */
	public Main(){
		File file = new File(System.getProperty("user.dir") + File.separator + "input.txt");
		setCirkels(this.readInput(file));
		run();
		try {
			writeOutput();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * sets the type of algorithm to use
	 * @param algorithm1
	 */
	public void setAlg(int algorithm1){
		this.algoritme = algorithm1;
	}
	
	/**
	 * returns the type of algorithm (1,2 or 3)
	 * @return
	 */
	public int getAlg(){
		return this.algoritme;
	}
	
	/**
	 * sets the cirkels to evaluate
	 * @param cirkels
	 */
	private void setCirkels(ArrayList<Cirkel> cirkels) {
		this.cirkels = cirkels;
	}

	/**
	 * return the cirkels to evaluate
	 * @return
	 */
	public ArrayList<Cirkel> getCirkels() {
		return cirkels;
	}
	
	/**
	 * adds a cirkel to the list of cirkels to evaluate
	 * @param cirkel
	 */
	public void addCirkel(Cirkel cirkel){
		this.cirkels.add(cirkel);
	}

	/**
	 * creates a new algorithm according to the desired algorithm
	 */
	public void run(){
		switch (getAlg()){
		case 1:
			Algorithm alg1 = new Algorithm1(getCirkels(), this);
			setAlgorithm(alg1);
			break;
		case 2:
			Algorithm alg2 = new Algorithm2(getCirkels(), this);
			setAlgorithm(alg2);
			break;
		case 3:
			Algorithm alg3 = new Algorithm3(getCirkels(), this);
			setAlgorithm(alg3);
			break;
		default:
			this.error = true;
			break;
		}
	}
	
	/**
	 * sets the algorithm which is used
	 * @param algorithm1
	 */
	public void setAlgorithm(Algorithm algorithm1){
		this.algo = algorithm1;
	}
	
	/**
	 * returns the used algorithm
	 * @return
	 */
	public Algorithm getAlgoritme(){
		return this.algo;
	}
	
	/**
	 * adds an intersectionpoint to the list of intersectionpoints
	 * @param snijpunten
	 */
	public void addSnijpunten(Punt snijpunten){	
		getSnijpunten().add(snijpunten);
	}
	
	/**
	 * returns the list of intersectionpoints
	 * @return
	 */
	public ArrayList<Punt> getSnijpunten(){
		return this.snijpunten;
	}
	
	/**
	 * reads Input.txt file and casts it to circles
	 */
	public ArrayList<Cirkel> readInput(File file){
		ArrayList<Cirkel> cirkels = new ArrayList<Cirkel>();
		
		Scanner scan;
		try {
			scan = new Scanner(file);
			setAlg(Integer.parseInt(scan.nextLine()));
			int amountOfCirkels = Integer.parseInt(scan.nextLine());
			for(int i = 0; i < amountOfCirkels; i++){
				Cirkel cirkel = castToCirkel(scan.nextLine());
				cirkels.add(cirkel);
			}return(cirkels);
		} catch (FileNotFoundException e) {
			System.out.println("File not found");
			e.printStackTrace();
		}return cirkels;
	}
	
	/**
	 * casts a string to a cirkel
	 * @param cirkel
	 * @return
	 */
	public Cirkel castToCirkel(String cirkel){
		String[] parts = cirkel.split(" ");
		Punt middelpunt = new Punt(Double.parseDouble(parts[0]), Double.parseDouble(parts[1]));
		Cirkel castedCirkel = new Cirkel(middelpunt, Double.parseDouble(parts[2]));
		return castedCirkel;
	}
	
	/**
	 * creates and writes an output file
	 * @throws IOException
	 */
	public void writeOutput() throws IOException{
		FileWriter out;
		try {
			out = new FileWriter(System.getProperty("user.dir") + File.separator + "output.txt");
			if (this.error) out.write("Dit algoritme is niet geimplementeerd");
			else{
				out.write(String.valueOf(getSnijpunten().size()));
				out.write(System.lineSeparator());
				for(int i = 0; i < getSnijpunten().size(); i++){
					out.write(String.valueOf(getSnijpunten().get(i).getX()) + " " + String.valueOf(getSnijpunten().get(i).getY()));
					out.write(System.lineSeparator());
				}
				out.write(System.lineSeparator());
				out.write(String.valueOf(getAlgoritme().getTime()));
			}
			out.flush();
			out.close();	
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
}
