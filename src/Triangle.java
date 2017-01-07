import java.util.ArrayList;

public class Triangle {

    private double aColCoef = 1.0/8.0;
    private double bColCoef = 1.0/2.0;
    private double cColVal = 3.0/8.0;
    
    private double aRowCoef = 1.0/2;
    private double bRowCoef = 1.0/2;
    private double cRowVal = 0.0;

    private String emptySpotChar = "-";
	private int numSpacesInPrint = 2;
	
	public int numPieces;
	private int initialEmptySpot;
	private int numCols;
	private int maxRows;
	public ArrayList<Integer> emptySpots;
	public ArrayList<Position> positions;
	public ArrayList<Move> possibleMoves;
	
	public Triangle(int numPieces, int initialEmptySpot) throws InvalidArgumentException {
        if (this.isValidNumPieces(numPieces)){
            this.numPieces = numPieces;
            this.initialEmptySpot = initialEmptySpot;

            this.numCols = solveQuadratic(this.aColCoef,this.bColCoef, (this.cColVal - numPieces));
            this.maxRows = solveQuadratic(this.aRowCoef,this.bRowCoef, this.cRowVal- numPieces);

            this.emptySpots = new ArrayList<Integer>();
            this.emptySpots.add(initialEmptySpot);

            this.positions = getPositions(this.numPieces,initialEmptySpot);
            this.possibleMoves = new ArrayList<Move>();
            setPossibleMoves();
        }

        else{
        	throw new InvalidArgumentException("Number of Pieces Invalid");       	
        }
            
	}
	
	public void makeMove(int mv){
		possibleMoves.get(mv).makeMove();
		setPossibleMoves();
	}

	private void setPossibleMoves() {
		possibleMoves.clear();
        for (int i = 0; i < this.positions.size(); i ++){
        	positions.get(i).setPossibleMoveSpots();
        }
        for (Position pos : positions){
        	this.possibleMoves.addAll(pos.getMoves());
        }
	}

	static int getMidValue(int x1, int x2) {
		return (int) (x1 + ((x2-x1)/2));
	}

	public Position getPositionObjectByN(int n) {
		return this.positions.get(n);
	}

	// returns position object given row and column value of the target position
	public Position getPositionAt(int col, int row) {
		for (Position pos : this.positions){
			if (pos.row == row && pos.col == col){
				return pos;
			}
		}
		System.out.println("Position at: col:"+col+", row: "+row+" Does not exist.");
		return null;
	}

	private ArrayList<Position> getPositions(int numPieces,int initEmptySpot) throws InvalidArgumentException {
		ArrayList<Position> returnPositions = new ArrayList<Position>();
		for (int i = 1; i <= numPieces; i ++){
			if (i == initEmptySpot){
				returnPositions.add(new Position(this,this.getCol(i), this.getRow(i),true,i));
			}
			else {
				returnPositions.add(new Position(this,this.getCol(i), this.getRow(i),false,i));
			}
		}
		return returnPositions;
	}

	private int getRow(int pieceNumber) throws InvalidArgumentException {
		if (!this.isValidNumPieces(pieceNumber)){
			while (!this.isValidNumPieces(pieceNumber)){
				pieceNumber--;
			}
			return ( this.solveQuadratic(this.aRowCoef, this.aRowCoef, this.cRowVal - pieceNumber ) + 1 );
		} 
		return this.solveQuadratic(this.aRowCoef, this.aRowCoef, this.cRowVal - pieceNumber);
	}
	
	private int getCol(int pieceNumber) throws InvalidArgumentException {
		if (pieceNumber == 1){
			return 0;
		}
		else if (this.isValidNumPieces(pieceNumber)){
			return this.getRow(pieceNumber) -1;
		}
		else{
			int upperBound = pieceNumber;
			while (!this.isValidNumPieces(upperBound)){
				upperBound++;
			}
			int upperBoundRow = this.getRow(upperBound);
			int numNumbersBack = upperBound - pieceNumber;
			return upperBoundRow - 2*numNumbersBack -1;
		}
	}

	private int solveQuadratic(double a, double b, double c) throws InvalidArgumentException {
		double d = Math.pow(b, 2) - 4 *a *c;
		if (d < 0){
			throw new InvalidArgumentException("Invalid Quadratic Arguments");
		}
		else if (d == 0){
			return (int) ((-b + Math.sqrt( Math.pow(b, 2) - 4 * a * c  ))/ (2*a));
		}
		else {
			int x1 = (int) ((-b + Math.sqrt( Math.pow(b, 2) - 4 * a * c  ))/ (2*a));
			int x2 = (int) ((-b - Math.sqrt( Math.pow(b, 2) - 4 * a * c  ))/ (2*a));
			if (x1> 0){
				return x1;
			}
			return x2;
		}
	}

	private boolean isValidNumPieces(int numPieces) {
		long calc_num = 8*numPieces+1;
	     long t = (long) Math.sqrt(calc_num);
	     if (t*t==calc_num) {
	        return true;
	     }
	     return false;
	}
	
	public void printTriangle(){
		System.out.println();
        int rowHeight = this.maxRows;
        int indent = (int) (rowHeight * this.numSpacesInPrint);
        String spacer = stringRepeater(" ",this.numSpacesInPrint);
        int i = 1;
        
        System.out.print( stringRepeater(" ",indent) + spacer);
        
        while (i < (this.numPieces+1)){
            if (this.isValidNumPieces(i)){
            	
                Position curPosition = getPositionObjectByN(i-1);

                if (!curPosition.isEmpty){
                    if (i < 10){
                    	System.out.print( spacer + i + " " );
                    }
                        
                    else {
                    	System.out.print( spacer + i);
                    }
                        
                }
                else{
                	System.out.print( spacer + this.emptySpotChar);
                }
                    
                i ++;
                System.out.print( " \n"+ stringRepeater(" ",indent) );
                indent -= this.numSpacesInPrint;
            }
            else{
                Position curPosition = this.getPositionObjectByN(i-1);
                if (!curPosition.isEmpty){
                    if (i < 10){
                    	System.out.print( spacer + i + " " );
                    }
                        
                    else{
                    	System.out.print( spacer + i);
                    }
                        
                }
                else{
                	System.out.print( spacer + this.emptySpotChar);
                }
                i += 1;
            }
        }
     }
	
	public void printMoves() {
		for (Move mv : possibleMoves){
			System.out.println(mv);
		}
	}
	
	public void printPositions() {
		for (Position pos : this.positions){
			System.out.println(pos);
		}
	}
	
	public String stringRepeater(String input, int count){
		String returnStr = "";
		for (int i = 0 ; i < count; i++){
			returnStr = returnStr + input;
		}
		return returnStr;
	}
	
	public boolean isValidSpot(int col, int row) {
		if ( row >= (Math.abs(col)+1) ){
			if ( (isOdd(col) && !isOdd(row) ) || (!isOdd(col) && isOdd(row) )  ){
				if (row < maxRows){
					return true;
				}
			}
		}
		return false;
	}

	private boolean isOdd(int i) {
		if (i % 2 == 0){
			return false;
		}
		return true;
	}

	public void reset() throws InvalidArgumentException{
		this.emptySpots.clear();
		this.emptySpots.add(new Integer(initialEmptySpot));
		
		this.positions.clear();
		this.positions.addAll(this.getPositions(this.numPieces, this.initialEmptySpot));
		
		this.possibleMoves.clear();
		setPossibleMoves();
	}

	
}
