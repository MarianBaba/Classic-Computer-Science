package Simplest;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

public class Fibonacci {

	// Fibonacci sequence = element n is the sum o n - 1 and n - 2, except for the
	// first two elements: 1 and 0
	// 0, 1, 1, 2, 3, 5, 8, 12, 20, 32 ...

	// the call tree of this method grows exponentially: comouting
	// fibonacciOneValue(20) results in around 20k calls (inefficient)
	private static int fibonacciRecursive(int n) { // intuitive recursive method, computes one value only
		if (n < 2) {
			return n;
		} else {
			return fibonacciRecursive(n - 2) + fibonacciRecursive(n - 1);
		}
	}

	// let's try using the memoization technique: the program remembers what has
	// already computed and doesn't need to compute it
	// multiple times

	// we create a map for storing all the values already computed: the first two
	// are given
	// now fibonacci(20) only need around 40 calls
	static Map<Integer, Integer> memo = new HashMap<>(Map.of(0, 0, 1, 1));

	private static int fibonacciMemoization(int n) {
		if (!memo.containsKey(n)) { // the algorithm computes the nth fibonacci value only if it needs to
			memo.put(n, fibonacciMemoization(n - 2) + fibonacciMemoization(n - 1));
		}
		return memo.get(n);
	}

	// counterintuitively, the most efficient way of computed a value of the
	// fibonacci sequence is iteratively and not recursively
	// the for loop executes n-1 times, much better than the previous algorithms
	private static int fibonacciIterative(int n) {
		int last = 0, next = 1;
		for (int i = 0; i < n; i++) {
			int lastOld = last;
			last = next;
			next = lastOld + last;
		}
		return last;
	}

	// previously, we only generated single values, let's generate a stream of
	// values up to the nth fibonacci element
	private int last = 0, next = 1; // first two elements of the fibonacci sequence aka base cases

	public IntStream fibonacciStream() {
		return IntStream.generate(() -> { // we use a lambda function to generate each value
			int lastOld = last;
			last = next;
			next = lastOld + last;
			return lastOld;
		});
	}

	public static void main(String... strings) {
		System.out.println(fibonacciRecursive(5));
		System.out.println(fibonacciMemoization(5));
		System.out.println(fibonacciIterative(5));
		Fibonacci f = new Fibonacci();
		f.fibonacciStream().limit(20).forEachOrdered(System.out::println);
	}

}
