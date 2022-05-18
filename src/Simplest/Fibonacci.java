package Simplest;

public class Fibonacci {
	
	//Fibonacci sequence = element n is the sum o n - 1 and n - 2, except for the first two elements: 1 and 0
	//0, 1, 1, 2, 3, 5, 8, 12, 20, 32 ...

	//the call tree of this method grows exponentially: comouting fibonacciOneValue(20) results in around 20k calls (inefficient)
	private static int fibonacciRecursive(int n) { //intuitive recursive method, computes one value only
		if(n < 2) {
			return n;
		} else {
			return fibonacciRecursive(n-2) + fibonacciRecursive(n-1);
		}
	}
	
	//let's try using the memoization technique: the program remembers what has already computed and doesn't need to compute it
	//multiple times
	private int last = 0, next = 1; //first two elements of the fibonacci sequence aka base cases
	
	public static void main(String... strings) {
		System.out.println(fibonacciRecursive(5));
	}
	
}
