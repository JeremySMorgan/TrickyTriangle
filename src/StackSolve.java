import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;

public class StackSolve {

	private Triangle tri;
	private boolean debug;
	
	public StackSolve(Triangle tri,boolean debug){
		this.tri = tri;
		this.debug = debug;
	}
	
	public double[] stackSolve() throws InvalidArgumentException {

		Stack<Move> winningMoves = new Stack<Move>();
		double startTime = System.nanoTime();
		int totalWinningMoves = stackSolve(tri,winningMoves,1,0);
		double endTime = System.nanoTime();
		double duration = (endTime - startTime)/1000000000 ; //seconds
		double[] returnVal = {(double)totalWinningMoves,duration};	
		return returnVal;
	}
	

	public int stackSolve(Triangle inputTriangle, Stack<Move> stackIn,int depth,int winningStates) throws InvalidArgumentException{
		
		//if (this.debug) inputTriangle.printTriangle(depth*5);
		if (this.debug) System.out.println(Arrays.toString(stackIn.toArray()));
		
		if (inputTriangle.possibleMoves.size() ==0){
			
			//if (this.debug) System.out.println("num possible moves: 0");
			
			if (inputTriangle.nonNonEmptySpots() == 1){
				if (this.debug) System.out.println("<> Winning solution found.");
				
				return 1;
			}
			else{
				//if (this.debug) System.out.println("non winning solution found. Going higher in recursion tree");
				return 0;
			}
			
		}
		else{
			ArrayList<Move> moveHolder = new ArrayList<Move>();
			moveHolder.addAll(inputTriangle.possibleMoves);
			
			int ws = 0;
			for (Move mv : moveHolder){
				inputTriangle.makeMove(mv);
				stackIn.push(mv);
				ws += stackSolve(inputTriangle,stackIn,depth+1,winningStates);	
				stackIn.pop();
				inputTriangle.undoMove(mv);
			}
			return ws;
		}
		

	}	

	
}
