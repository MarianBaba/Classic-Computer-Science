package Search;

import java.util.List;

public class GenericSearch {

    // generalized linear search
    public static <T extends Comparable<T>> boolean linearContains(List<T> list, T key) {
        for (T item : list) {
            if (item.compareTo(key) == 0) {
                return true;
            }
        }
        return false;
    }

    // generalized binary search, assumes list is already sorted
    public static <T extends Comparable<T>> boolean binaryContains(List<T> list, T key) {
        int low = 0;
        int high = list.size() - 1;
        while (low <= high) {
            int middle = (low + high) / 2;
            int comparison = list.get(middle).compareTo(key);
            if (comparison < 0) {
                low = middle + 1;
            } else if (comparison > 0) {
                high = middle - 1;
            } else {
                return true;
            }
        }
        return false;
    }

    //node class
    public static class Node<T> implements Comparable<Node<T>> {
		
		final T state;
		Node<T> parent;
		double cost;
		double heuristic;
		
		Node(T state, Node<T> parent) {
			this.state = state;
			this.parent = parent;
		}
		
		Node(T state, Node<T> parent, double cost, double heuristic) {
			this.state = state;
			this.parent = parent;
			this.cost = cost;
			this.heuristic = heuristic;
		}
		
		@Override
		public int compareTo(Node<T> other) {
			Double mine = cost + heuristic;
			Double theirs = other.cost + other.heuristic;
			return mine.compareTo(theirs);
		}
	}

    public static void main(String... strings) {
        System.out.println(linearContains(List.of(1, 5, 15, 15, 15, 15, 20), 5));
        System.out.println(binaryContains(List.of("a", "d", "e", "f", "z"), "f"));
        System.out.println(binaryContains(List.of("john", "mark", "ronald", "sarah"), "sheila"));
    }

}
