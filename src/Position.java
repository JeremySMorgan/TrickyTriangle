import java.util.ArrayList;

public class Position {

	public Triangle triangle;
	public ArrayList<Move> possibleMoveSpots;
	public int col;
	public int row;
	public boolean isEmpty;
	public int n = -1;
	public static boolean debug = false;

	// Primary Constructor
	public Position(Triangle triangle, int col, int row, boolean isEmpty, int n) {
		this.triangle = triangle;
		this.col = col;
		this.row = row;
		this.isEmpty = isEmpty;
		this.n = n;
		possibleMoveSpots = new ArrayList<Move>();
	}
	
	// Secondary Constructor, used to hold possible move spots
	public Position(Triangle triangle, int col, int row, boolean isEmpty) {
		this.triangle = triangle;
		this.col = col;
		this.row = row;
		this.isEmpty = isEmpty;
	}

	public String toString() {
		return "POSITION OBJECT: col:" + this.col + " | row: " + this.row + " | n: " + this.n;
	}

	public void setPossibleMoveSpots(){
		this.possibleMoveSpots.clear();
		int[][] possibleSpotOffsets = {{2,2}, {2,-2}, {-2,2}, {-2,-2},{-4,0}, {4,0},{0,-4}, {0,4}};
		if (!this.isEmpty){
			
			if (debug){
				System.out.println(" // ------------------------------------------------- //");
				System.out.println("finding spots! this.col: "+this.col+" this.row: "+this.row);
				this.triangle.printTriangle();
			}

			
			for (int i = 0; i < possibleSpotOffsets.length; i ++){
			
				int moveToCol = this.col + possibleSpotOffsets[i][0];
				int moveToRow = this.row + possibleSpotOffsets[i][1];
				
				if (debug) System.out.println("testing if (col: "+moveToCol+" | row: "+moveToRow+") is valid spot");
				
				if (this.triangle.isValidSpot(moveToCol,moveToRow )){
					if (debug) System.out.println(" VALIDSPOT. testing if end spot is empty, mid spot is occupied");
					
					Position moveTo = triangle.getPositionAt(moveToCol, moveToRow);
					
					int midSpotCol = Triangle.getMidValue( this.col, moveTo.col);
		            int midSpotRow = Triangle.getMidValue( this.row, moveTo.row);
		            Position midPos = triangle.getPositionAt(midSpotCol, midSpotRow);
		            
		            if (!midPos.isEmpty && moveTo.isEmpty){
		            	if (debug){
		            		System.out.println("JUMP SPOT FOUND!	New move from: "+this.n+"  	to: "+moveTo.n);
		            		System.out.println();
		            	}

						possibleMoveSpots.add( new Move(this.triangle, this, midPos, moveTo) );
		            }
		            else{
		            	if (debug){
			            	System.out.println("MOVE NOT VALID BECAUSE OF EMPTY STATUS OF POSITIONS");
			            	System.out.println();
		            	}

		            }
		            
				}
				else{
					if (debug){
						System.out.println("IS NOT VALID SPOT");
						System.out.println();
					}

				}
				
				
			}
		}
	}
	
	public ArrayList<Move> getMoves(){
		return this.possibleMoveSpots;
	}
	
}
