
public class Main {

	public static void main(String[] args) throws InvalidArgumentException {
		
		int[] triangle_sizes = {15}; 
		boolean debug = false;
		for (int i : triangle_sizes){
			for (int j = 1; j<=i;j++){
				Triangle tri = new Triangle(i,j);
				StackSolve solver = new StackSolve(tri,debug);
				double[] results = solver.stackSolve();
				System.out.println("Triangle of size: " + i+ ",	with initial empty position at: "+j+" solved in: "+results[1]+ "		seconds, with "+(int)results[0]+" total Solutions" );	
			}	
		}
		System.out.println();
	}

}
