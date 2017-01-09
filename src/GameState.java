import java.util.ArrayList;

public class GameState implements Comparable{

	public ArrayList<GameState> children;
	public Triangle tri;
	
	public GameState(Triangle tri) {
		this.children = new ArrayList<GameState>();
		this.tri = tri;
	}
	
	public void addChild(GameState child){
		children.add(child);
	}
	
	public String toString(){
		System.out.println("GameState Object. This.tri: ");
		this.tri.printTriangle();
		return " ";
	}

	@Override
	public int compareTo(Object o) {
		if (o instanceof GameState ){
			GameState gs = (GameState) o;
			return gs.tri.compareTo(this.tri);
		}
		else{
			try {
				throw new InvalidArgumentException("Invalid compareTo() input");
			} catch (InvalidArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}   
		}
		System.out.println("Printing from compareTo in GameState class. Major bug is seeing this message");
		return -1;
	}

}
