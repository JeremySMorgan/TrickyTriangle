
public class Main {

	public static void main(String[] args) throws InvalidArgumentException {
		
		int numPieces = 21;
		int initEmptySpot = 1;
		
		Triangle tri = new Triangle(numPieces, initEmptySpot);
		
		TrieSolve ts;
		try {
			ts = new TrieSolve(numPieces, initEmptySpot);
			ts.solve();
		} catch (InvalidArgumentException e) {
			e.printStackTrace();
		}
		
	}

}
