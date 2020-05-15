import java.util.*;

/*
 * Combination implemented by recursive algorithm
 */

class Combination {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		while(true) {
			System.err.println("Enter n m : ");
			int n = in.nextInt();
			int m = in.nextInt();
			if(n == 0) 
				break;

			System.out.println("Combination(" +  
				n + ", " + m + ") : " + 
				Combi(n, m));
		}
	}
	
	static int Combi(int n, int m) {
		
		if( n == m || m == 0 ){
			return 1;
		}
		else if( n -1 == m ) {
			return n;
		}
		else{
		
		return Combi( n-1 , m ) + Combi( n-1 , m -1);
		}
	}
}

