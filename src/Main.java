
public class Main {

	public static void main(String[] args) throws InvalidArgumentException {
		
		int[] np = {6,10,15,21};
		int ies = 1;
		
		for (int numPiecesInTri : np){
			Triangle tri = new Triangle(numPiecesInTri, ies);
			TrieSolve ts;
			ts = new TrieSolve(tri);
			ts.solve();			
		}

	}

}
