import java.util.ArrayList;
import java.util.Random;

public class TrieSolve {

	private Triangle tri;
	public long endstates = 0;
	public int winningEndStates = 0;
	public ArrayList<GameState> encounteredGameStates;

	public TrieSolve(Triangle tri ) throws InvalidArgumentException{
		this.tri = tri;
		this.encounteredGameStates = new ArrayList<GameState>();
	}
	
	
	public void solve() throws InvalidArgumentException{
		
		Triangle begginingState = tri;
		GameState root = new GameState(begginingState);
		
		long startTime = System.nanoTime();
		solverHelper(root,1);
		long endTime = System.nanoTime();
		
		long duration = (endTime - startTime)/1000000 ; //seconds
		
		System.out.println("Triangle of size: "+tri.numPieces+"	pieces. " +endstates + ",	"+winningEndStates+" possible, winning, endstates found. Program executes in: "+duration+"	milliseconds");
	}
	
	public boolean gamestateEncountered(GameState gs){
		for (GameState existingGS : encounteredGameStates){
			if (gs.compareTo(existingGS) == 0){
				return true;
			}
		}
		return false;
	}
	
	
	// iterate through possible moves. 
	// 1. Make new triangle,
	// 2. make move on triangle, 
	// 3. make new game state with new triangle
	// 4. append new game state to in gamestate children
	// 5. call solverHelper on child
	public void solverHelper(GameState in,int depth) throws InvalidArgumentException{
		
		if (in.tri.possibleMoves.size() ==0){

			endstates++;
			if (in.tri.nonNonEmptySpots() == 1){
				winningEndStates++;
			}
		}
		
		/*
		for (Move mv : in.tri.possibleMoves){
			in.tri.makeMove(mv);
			GameState child = new GameState(in.tri);
			in.children.add(child);
			System.out.println("Recursion depth: "+depth);
			solverHelper(child,depth+1);
			in.tri.undoMove(mv);
		}*/
		
		for (int i = 0; i < in.tri.possibleMoves.size(); i++){
			
			
			Triangle triCopy = in.tri.returnCopy();
			triCopy.makeMove(i);
			GameState child = new GameState( triCopy );
			in.children.add(child);
			
			//System.out.println("Recursion depth: "+depth);
			
			// look to see if solverHelper has been called on the gamestate before.
			// if it has, we  ignore the child because it has already been calculated.
			// if it is new, we add it to encountered gamestates and recurse into it
			if (!(gamestateEncountered(child))){
				encounteredGameStates.add(child);
				solverHelper(child,depth+1);	
			}
			
		}
		
	}
	
	
	
}







