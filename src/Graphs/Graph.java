package Graphs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public abstract class Graph<V, E extends Edge> {

    private ArrayList<V> vertices = new ArrayList<>();
    protected ArrayList<ArrayList<E>> edges = new ArrayList<>();

    public Graph() {

    }

    public Graph(List<V> vertices) {
        this.vertices.addAll(vertices);
        for (V vertex : vertices) {
            edges.add(new ArrayList<>());
        }
    }

    public int getVertexCount() {
        return vertices.size();
    }

    public int getEdgeCount() {
        return edges.stream().mapToInt(ArrayList::size).sum();
    }

    public int addVertex(V vertex) {
        vertices.add(vertex);
        edges.add(new ArrayList<>());
        return getVertexCount() - 1;
    }

    public V vertexAt(int index) {
        return vertices.get(index);
    }

    public int indexOf(V vertex) {
        return vertices.indexOf(vertex);
    }

    public List<V> neighborsOf(int index) {
        return edges.get(index).stream()
                .map(edge -> vertexAt(edge.v))
                .collect(Collectors.toList());
    }

    public List<V> neighborsOf(V vertex) {
        return neighborsOf(indexOf(vertex));
    }

    public List<E> edgesOf(int index) {
        return edges.get(index);
    }

    public List<E> edgesOf(V vertex) {
        return edgesOf(indexOf(vertex));
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < getVertexCount(); i++) {
            sb.append(vertexAt(i));
            sb.append("->");
            sb.append(Arrays.toString(neighborsOf(i).toArray()));
            sb.append(System.lineSeparator());
        }
        return sb.toString();
    }

    public static void main(String[] args) {

        UnweightedGraph<String> cityGraph = new UnweightedGraph<>(
                List.of("Seattle", "San Francisco", "Los Angeles",
                        "Riverside", "Phoenix", "Chicago", "Boston", "New York", "Atlanta",
                        "Miami", "Dallas", "Houston", "Detroit", "Philadelphia", "Washington"));
        cityGraph.addEdge("Seattle", "Chicago");
        cityGraph.addEdge("Seattle", "San Francisco");
        cityGraph.addEdge("San Francisco", "Riverside");
        cityGraph.addEdge("San Francisco", "Los Angeles");
        cityGraph.addEdge("Los Angeles", "Riverside");
        cityGraph.addEdge("Los Angeles", "Phoenix");
        cityGraph.addEdge("Riverside", "Phoenix");
        cityGraph.addEdge("Riverside", "Chicago");
        cityGraph.addEdge("Phoenix", "Dallas");
        cityGraph.addEdge("Phoenix", "Houston");
        cityGraph.addEdge("Dallas", "Chicago");
        cityGraph.addEdge("Dallas", "Atlanta");
        cityGraph.addEdge("Dallas", "Houston");
        cityGraph.addEdge("Houston", "Atlanta");
        cityGraph.addEdge("Houston", "Miami");
        cityGraph.addEdge("Atlanta", "Chicago");
        cityGraph.addEdge("Atlanta", "Washington");
        cityGraph.addEdge("Atlanta", "Miami");
        cityGraph.addEdge("Miami", "Washington");
        cityGraph.addEdge("Chicago", "Detroit");
        cityGraph.addEdge("Detroit", "Boston");
        cityGraph.addEdge("Detroit", "Washington");
        cityGraph.addEdge("Detroit", "New York");
        cityGraph.addEdge("Boston", "New York");
        cityGraph.addEdge("New York", "Philadelphia");
        cityGraph.addEdge("Philadelphia", "Washington");
        System.out.println(cityGraph.toString());
    }

}
