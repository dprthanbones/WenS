public class Cirkel {

		private Punt middelpunt;
		private double straal;

		public Cirkel(Punt middelpunt, double straal){
			this.middelpunt = middelpunt;
			this.straal = straal;
		}
		
		public Punt getMiddelpunt(){
			return this.middelpunt;
		}
		
		public double getStraal(){
			return this.straal;
		}
		
		public double distancepoints(Punt punt1, Punt punt2){
			return Math.sqrt(Math.pow(punt2.getX() - punt1.getX(),2) + Math.pow(punt2.getY() - punt1.getY(),2));
		}
		
		//snijpunten berekenen
		public Punt[] snijpuntAndereCirkel(Cirkel cirkel){
			//afstand middelpunten
			double d = distancepoints(getMiddelpunt(), cirkel.getMiddelpunt());
			if(d > getStraal() + cirkel.getStraal()){
				return new Punt[0];
			}
	
			double x_1 = getMiddelpunt().getX();
			double y_1 = getMiddelpunt().getY();
			double x_2 = cirkel.getMiddelpunt().getX();
			double y_2 = cirkel.getMiddelpunt().getY();
			
			double d_1 = (Math.pow(d, 2) + Math.pow(getStraal(), 2) - Math.pow(cirkel.getStraal(), 2))/(2*d);
			double h = Math.sqrt(Math.abs(Math.pow(cirkel.getStraal(), 2) - Math.pow(d_1, 2)));
			double X_3 = x_1 + (d_1*(x_2 - x_1))/d;
			double Y_3 = y_1 + (d_1*(y_2 - y_1))/d;
			if(d != getStraal() + cirkel.getStraal()){
				//1ste snijpunten
				double X_5 = X_3 + (h*(y_2 - y_1))/d;
				double Y_5 = Y_3 - (h*(x_2 - x_1))/d;
				//2de snijpunten
				double X_6 = X_3 - (h*(y_2 - y_1))/d;
				double Y_6 = Y_3 + (h*(x_2 - x_1))/d;
				return new Punt[]{new Punt(X_5,Y_5), new Punt(X_6,Y_6)}; 
			}return new Punt[]{new Punt(X_3,Y_3)};
		}
}
