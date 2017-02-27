
public class Main {

	public static void main(String[] args) throws InvalidArgumentException {
		
		int[] triangle_sizes = {15}; 
		boolean debug = false;
		for (int i : triangle_sizes){

			System.out.println("	Triangle Size	|	Initial Empty Position	|	Computation Time (s)	|	Total Solutions");
			System.out.println("	-------------		----------------------		--------------------		---------------");
			for (int j = 1; j<=i;j++){
				Triangle tri = new Triangle(i,j);
				StackSolve solver = new StackSolve(tri,debug);
				double[] results = solver.stackSolve();
				System.out.println("	"+i+ "		|	 "+j+"			|	"+results[1]+ "		|	"+(int)results[0] );	
			}	
		}
		System.out.println();
	}

}
