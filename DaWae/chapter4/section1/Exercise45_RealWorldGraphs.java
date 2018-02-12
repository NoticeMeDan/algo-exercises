package chapter4.section1;

import chapter1.section3.Bag;
import chapter3.section4.SeparateChainingHashTable;
import chapter3.section5.HashSet;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import util.Constants;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rene Argento on 09/10/17.
 */
// The data source is a file containing information about United States flight distances
    // It can be downloaded here: http://networkrepository.com/inf-USAir97.php
@SuppressWarnings("unchecked")
public class Exercise45_RealWorldGraphs {

    private class Graph {

        private int vertices;
        private int edges;
        private SeparateChainingHashTable<Integer, Bag<Integer>> adjacent;

        public Graph() {
            vertices = 0;
            edges = 0;
            adjacent = new SeparateChainingHashTable<>();
        }

        public int vertices() {
            return vertices;
        }

        public int edges() {
            return edges;
        }

        public void addVertex(int vertex) {
            if(!adjacent.contains(vertex)) {
                adjacent.put(vertex, new Bag<>());
                vertices++;
            }
        }

        public void addEdge(int vertex1, int vertex2) {
            if(!adjacent.contains(vertex1)) {
                adjacent.put(vertex1, new Bag<>());
                vertices++;
            }
            if(!adjacent.contains(vertex2)) {
                adjacent.put(vertex2, new Bag<>());
                vertices++;
            }

            adjacent.get(vertex1).add(vertex2);
            adjacent.get(vertex2).add(vertex1);
            edges++;
        }

        public Iterable<Integer> adjacent(int vertex) {
            return adjacent.get(vertex);
        }

        @Override
        public String toString() {
            StringBuilder stringBuilder = new StringBuilder();

            for(int vertex = 0; vertex < vertices(); vertex++) {
                stringBuilder.append(vertex).append(": ");

                if(adjacent(vertex) != null) {
                    for(Integer neighbor : adjacent(vertex)) {
                        stringBuilder.append(neighbor).append(" ");
                    }
                }

                stringBuilder.append("\n");
            }

            return stringBuilder.toString();
        }
    }

    private class Edge {
        int vertex1;
        int vertex2;

        Edge(int vertex1, int vertex2) {
            this.vertex1 = vertex1;
            this.vertex2 = vertex2;
        }
    }

    private Graph randomRealGraph(int randomVerticesToChoose, int randomEdgesToChoose) {
        String filePath = Constants.FILES_PATH + Constants.US_AIR_FILE;
        String separator = " ";

        In in = new In(filePath);
        String[] firstLine = in.readLine().split(separator);
        int vertices = Integer.parseInt(firstLine[0]);
        int edges = Integer.parseInt(firstLine[2]);

        Graph fullGraph = new Graph();

        for(int edge = 0; edge < edges; edge++) {
            String[] connection = in.readLine().split(separator);

            int city1 = Integer.parseInt(connection[0]);
            int city2 = Integer.parseInt(connection[1]);
            double distance = Double.parseDouble(connection[2]); // Not used in this exercise

            fullGraph.addEdge(city1, city2);
        }

        Graph randomSubGraph = new Graph();
        SeparateChainingHashTable<Integer, Integer> graphToSubGraphMap = new SeparateChainingHashTable<>();

        List<Edge> allSubGraphEdges = new ArrayList<>();
        HashSet<Integer> chosenVertices = new HashSet<>();

        for(int vertex = 0; vertex < randomVerticesToChoose; vertex++) {
            // Randomly choose a vertex between 1 and vertices
            int randomVertexId = StdRandom.uniform(vertices) + 1;

            if(chosenVertices.contains(randomVertexId)) {
                continue;
            }
            chosenVertices.add(randomVertexId);

            int subGraphVertexId1 = graphToSubGraphMap.size();
            graphToSubGraphMap.put(randomVertexId, subGraphVertexId1);

            randomSubGraph.addVertex(subGraphVertexId1);

            for(Integer neighbor : fullGraph.adjacent(randomVertexId)) {
                int subGraphVertexId2;

                if(!graphToSubGraphMap.contains(neighbor)) {
                    subGraphVertexId2 = graphToSubGraphMap.size();
                    graphToSubGraphMap.put(neighbor, subGraphVertexId2);
                    randomSubGraph.addVertex(subGraphVertexId2);
                } else {
                    subGraphVertexId2 = graphToSubGraphMap.get(neighbor);
                }

                allSubGraphEdges.add(new Edge(subGraphVertexId1, subGraphVertexId2));
            }
        }

        // Randomly choose E edges from the subgraph induced by the random vertices
        if(randomEdgesToChoose > allSubGraphEdges.size()) {
            throw new IllegalArgumentException("Not enough edges to choose from the induced subgraph");
        }

        Edge[] allSubGraphEdgesArray = new Edge[allSubGraphEdges.size()];
        int allSubGraphEdgesArrayIndex = 0;
        HashSet<Integer> edgesChosen = new HashSet<>();

        for(Edge edge : allSubGraphEdges) {
            allSubGraphEdgesArray[allSubGraphEdgesArrayIndex++] = edge;
        }

        for(int edge = 0; edge < randomEdgesToChoose; edge++) {
            // Randomly choose an edge
            int randomEdgeId = StdRandom.uniform(allSubGraphEdgesArray.length);

            if(edgesChosen.contains(randomEdgeId)) {
                continue;
            }

            edgesChosen.add(randomEdgeId);

            Edge randomEdge = allSubGraphEdgesArray[randomEdgeId];
            randomSubGraph.addEdge(randomEdge.vertex1, randomEdge.vertex2);
        }

        return randomSubGraph;
    }

    // Example parameters:
    // 20 20
    public static void main(String[] args) {
        int randomVerticesToChoose = Integer.parseInt(args[0]);
        int randomEdgesToChoose = Integer.parseInt(args[1]);

        Graph randomRealGraph = new Exercise45_RealWorldGraphs().
                randomRealGraph(randomVerticesToChoose, randomEdgesToChoose);

        StdOut.println(randomRealGraph);
    }

}
