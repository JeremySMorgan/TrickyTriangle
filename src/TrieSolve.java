import java.util.ArrayList;
import java.util.Random;

public class TrieSolve {

	private Triangle tri;
	private int numPieces;
	private int initialEmptySpot;
	private Random rm;
	
	public TrieSolve(int numPieces, int initialEmptySpot ) throws InvalidArgumentException{
		this.numPieces = numPieces;
		this.initialEmptySpot = initialEmptySpot;
		this.tri = new Triangle(numPieces,initialEmptySpot);
		this.rm = new Random();
	}
	
	
	public void solve() throws InvalidArgumentException{
		
		ArrayList<Integer> moves = new ArrayList<Integer>();
		int numLeft = tri.numPieces-1;
		Triangle temp = new Triangle(numPieces,initialEmptySpot );
		temp.printTriangle();
	}
	
	
}
