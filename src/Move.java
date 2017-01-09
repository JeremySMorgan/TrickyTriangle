
public class Move {

	public Position from;
	public Position mid;
	public Position to;
	private Triangle tri;
	
	public Move(Triangle tri, Position from, Position mid, Position to) {
		this.from	= from;
		this.to 	= to;
		this.mid	= mid;
		this.tri	= tri;
	}
	

	public void makeMove(){
		this.from.isEmpty = true;
		this.mid.isEmpty = true;
		this.to.isEmpty = false;
	}
	
	
	public String toString(){
		return "MOVE object: from:"+from.n+" | mid: "+ mid.n+ " | to: "+to.n;
	}

}
