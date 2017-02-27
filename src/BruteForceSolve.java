import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class BruteForceSolve {

	private Triangle tri;
	private int numPieces;
	private int initialEmptySpot;
	private Random rm;
	
	public BruteForceSolve(int numPieces, int initialEmptySpot ) throws InvalidArgumentException{
		this.numPieces = numPieces;
		this.initialEmptySpot = initialEmptySpot;
		this.tri = new Triangle(numPieces,initialEmptySpot);
		this.rm = new Random();
	}
	
	public void testSolve() throws InvalidArgumentException{
		
		int[] moves = {0, 2, 2, 1, 5, 4, 2, 1, 1, 3, 1, 0, 0};
		Triangle temp = new Triangle(numPieces,initialEmptySpot );
		temp.printTriangle(0);
		for (int m : moves){
			temp.printMoves();
			temp.makeMove(m);
			temp.printTriangle(0);
		}

		temp.printMoves();
		
	}
	
	
	
	
	
	public void solve() throws InvalidArgumentException{
		
		ArrayList<Integer> moves = new ArrayList<Integer>();
		
		int numLeft = tri.numPieces-1;
		
		Triangle temp = new Triangle(numPieces,initialEmptySpot );
		temp.printTriangle(0);
		
		while (numLeft != 1){
			int possibleMoveCount = temp.possibleMoves.size();

			if (possibleMoveCount == 0 ){
				System.out.println("// ------------ //");
				System.out.println();
				System.out.println();
				System.out.println();
				System.out.println();
				System.out.println("numLeft: "+numLeft);
				System.out.println();
				System.out.println();
				System.out.println();
				System.out.println();
				System.out.println("// ------------ //");
				break;
				//temp.reset();
				//temp.printTriangle();
				//numLeft = temp.numPieces-1;
			}
			else{
				int randomMoveCount = rm.nextInt(possibleMoveCount);
				moves.add(new Integer(randomMoveCount));
				temp.printMoves();
				temp.makeMove(randomMoveCount);
				numLeft--;
				temp.printTriangle(0);
			}

		} 
		
		System.out.println("Problem solved");
		System.out.println(moves);
		
		
		
	}
	
	
}
