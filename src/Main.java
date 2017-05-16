import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.FileSystems;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
	
	private int algoritme;
	private Algorithm algo;
	private ArrayList<Cirkel> cirkels = new ArrayList<Cirkel>();
	private ArrayList<Punt> snijpunten = new ArrayList<Punt>();
	private boolean error = false;
	private long ExecutionTime;
	
	/**
	/*	Run
	 */
	public static void main(String[] args){
		new Main();
	}
	
	/**
	 * constructor
	 */
	public Main(){
		long starttime = System.currentTimeMillis();
		File file = new File("C:\\Users\\willem\\Documents\\Wens\\WenS");
		setCirkels(this.readInput(file));
		setAlg(1);
		run();
		System.out.println(getSnijpunten().size());
		for(int i = 0; i < getSnijpunten().size(); i++){
			System.out.println("snijpunt: " + i);
			System.out.println("puntX: " + getSnijpunten().get(i).getX());
			System.out.println("puntY: " + getSnijpunten().get(i).getY());
			System.out.println("------------------------");
		}
		ExecutionTime = System.currentTimeMillis() - starttime;
		System.out.println("time: " + ExecutionTime + "Ms");
		writeOutput();
	}
	
	public void setAlg(int algorithm1){
		this.algoritme = algorithm1;
	}
	
	public int getAlg(){
		return this.algoritme;
	}
	
	private void setCirkels(ArrayList<Cirkel> cirkels) {
		this.cirkels = cirkels;
	}

	public ArrayList<Cirkel> getCirkels() {
		return cirkels;
	}
	
	public void addCirkel(Cirkel cirkel){
		this.cirkels.add(cirkel);
	}

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
		}
	}
	
	public void setAlgorithm(Algorithm algorithm1){
		this.algo = algorithm1;
	}
	
	public Algorithm getAlgoritme(){
		return this.algo;
	}
	
	public void addSnijpunten(Punt snijpunten){	
		getSnijpunten().add(snijpunten);
	}
	
	public ArrayList<Punt> getSnijpunten(){
		return this.snijpunten;
	}
	
	/*
	 * reads Input.txt file and casts it to circles
	 */
	public ArrayList<Cirkel> readInput(File file){
		ArrayList<Cirkel> cirkels = new ArrayList<Cirkel>();
		
		Scanner scan;
		try {
			scan = new Scanner(file);
			setAlg(Integer.parseInt(scan.nextLine()));
			//TODO kan veel makelijkere
			int amountOfCirkels = Integer.parseInt(scan.nextLine());
			for(int i = 0; i < amountOfCirkels; i++){
				Cirkel cirkel = castToCirkel(scan.nextLine());
				cirkels.add(cirkel);
			}return(cirkels);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}return cirkels;
	}
	
	public Cirkel castToCirkel(String cirkel){
		String[] parts = cirkel.split(" ");
		Punt middelpunt = new Punt(Double.parseDouble(parts[0]), Double.parseDouble(parts[1]));
		Cirkel castedCirkel = new Cirkel(middelpunt, Double.parseDouble(parts[2]));
		return castedCirkel;
	}
	
	public void writeOutput(){
		//TODO functie schrijven die ouput in een text bestand zet
		try{
		    PrintWriter writer = new PrintWriter("the-file-name.txt", "UTF-8");
		    if(this.error == true)
		    	writer.println("Dit algoritme is niet geimplementeerd");
		    else{
		    writer.println(this.snijpunten.size());
		    for(int i = 0; i < this.snijpunten.size(); i=1){
		    	writer.println(this.snijpunten.get(i).getX() + "" + this.snijpunten.get(i).getY());
		    }
		    writer.println("");
		    writer.println(ExecutionTime);
		    writer.close();
		    }
		} catch (IOException e) {
		   //TODO exception als er iets fout gaat
		}
	}
	
}
