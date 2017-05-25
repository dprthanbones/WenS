import java.awt.Color;
import java.awt.Graphics;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JComponent;
import javax.swing.JFrame;

class Draw extends JFrame {
 
	public static void main(String args[])
	  {
	    new Draw();
	  }
	
  public Draw(){
	  	setTitle("circle Drawing");
	    setSize(4500, 2000);
	    setVisible(true);
	    makeCircle(getGraphics());
  }
  
  public void makeCircle(Graphics g){
	  int scaleFactor = 300;
	  int xOffset = 1;
	  int yOffset = 1;
	  int radius = 3;
	  File file = new File(System.getProperty("user.dir") + File.separator + "input.txt");
	  ArrayList<Cirkel> cirkels = readInput(file);
	  File file2 = new File(System.getProperty("user.dir") + File.separator + "output.txt");
	  ArrayList<Punt> Punten = readInputPoints(file2);
	  
      for (Cirkel circle: cirkels) {
          if (circle != null)
              g.drawOval((int)((circle.getMiddelpunt().getX()+ xOffset - circle.getStraal())*scaleFactor), (int)((circle.getMiddelpunt().getY()+ yOffset - circle.getStraal())*scaleFactor),
                      (int)(circle.getStraal()*2.0*scaleFactor), (int)(circle.getStraal()*2.0*scaleFactor));
      }
      for (Punt snijpunt: Punten) {
          if (snijpunt != null) {
              g.setColor(Color.RED);
              g.fillOval(
                      (int)(((snijpunt.getX()+ xOffset)*scaleFactor) - radius), (int)(((snijpunt.getY()+ yOffset)*scaleFactor) - radius), (int)(radius*2.0), (int)(radius*2.0));
          }
      }
  }
  
  public ArrayList<Cirkel> readInput(File file){
		ArrayList<Cirkel> cirkels = new ArrayList<Cirkel>();
		
		Scanner scan;
		try {
			scan = new Scanner(file);
			Integer.parseInt(scan.nextLine());
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
  
  public Cirkel castToCirkel(String cirkel){
		String[] parts = cirkel.split(" ");
		Punt middelpunt = new Punt(Double.parseDouble(parts[0]), Double.parseDouble(parts[1]));
		Cirkel castedCirkel = new Cirkel(middelpunt, Double.parseDouble(parts[2]));
		return castedCirkel;
	}
  
  public ArrayList<Punt> readInputPoints(File file){
		ArrayList<Punt> punten = new ArrayList<Punt>();
		
		Scanner scan;
		try {
			scan = new Scanner(file);
			int n = Integer.parseInt(scan.nextLine());
			for(int i = 0; i < n; i++){
				Punt p = castToPunt(scan.nextLine());
				punten.add(p);
			}return(punten);
		} catch (FileNotFoundException e) {
			System.out.println("File not found");
			e.printStackTrace();
		}return punten;
	}
  
  public Punt castToPunt(String punt){
		String[] parts = punt.split(" ");
		Punt p = new Punt(Double.parseDouble(parts[0]), Double.parseDouble(parts[1]));		
		return p;
	}
  
}


  