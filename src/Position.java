import java.util.ArrayList;

public class Position {

	public Triangle triangle;
	public ArrayList<Move> possibleMoveSpots;
	public int col;
	public int row;
	public boolean isEmpty;
	public int n = -1;

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
		int[][] possibleSpotOffsets = {{2,2}, {2,-2}, {-2,2}, {-2,-2},{-4,0}, {4,0}};
		if (!this.isEmpty){
			for (int i = 0; i < possibleSpotOffsets.length; i ++){
				int moveToCol = this.col + possibleSpotOffsets[i][0];
				int moveToRow = this.row + possibleSpotOffsets[i][1];
				
				if (this.triangle.isValidSpot(moveToCol,moveToRow )){
					Position moveTo = triangle.getPositionAt(moveToCol, moveToRow);
					int midSpotCol = Triangle.getMidValue( this.col, moveTo.col);
		            int midSpotRow = Triangle.getMidValue( this.row, moveTo.row);
		            Position midPos = triangle.getPositionAt(midSpotCol, midSpotRow);
		            if (!midPos.isEmpty && moveTo.isEmpty){
		            	System.out.println("this.n: "+this.n);
		            	System.out.println("in setPossibleMoveSpots() Making new position from "+this.n+" to "+moveTo.n);
						possibleMoveSpots.add( new Move(this.triangle, this, midPos, moveTo) );
		            }
				}
			}
		}
	}
	
	public ArrayList<Move> getMoves(){
		return this.possibleMoveSpots;
	}
	
}
