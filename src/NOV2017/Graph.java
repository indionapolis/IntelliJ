package NOV2017;

import IMPORTANT.LinkedQueue;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Project name: HomeWork
 * Created by pavel on 30.10.2017.
 * Pavel Nikulin BS1-8
 * Copyright ©
 * v2.0
 */
public class Graph<V> {
    public Hashtable<String, Vertex<V>> vertices;
    private List<Edge<V>> edges;
    private int sum = 0;
    private int flow = 0;
    private boolean isMaxFlow;

    public Graph() {
        this.vertices = new Hashtable<>();
        this.edges = new ArrayList<>();
    }






    public Vertex setVertex(String name){
        return new Vertex(name);
    }

    public void addVertex(Vertex vertex){
        vertices.put(vertex.getName(), vertex);
    }




    public void addDEdge(Vertex source, Vertex destination, int weight){
        Edge edge = new Edge(source, destination, weight);
        source.edges.put(destination.getName(), edge);
        destination.InEdges.put(source.getName(), edge);

        edges.add(edge);
        this.sum += weight;
    }

    public void addEdge(Vertex vertex1, Vertex vertex2, int weight){
        Edge edge1 = new Edge(vertex1, vertex2, weight);
        Edge edge2 = new Edge(vertex2, vertex1, weight);
        vertex1.edges.put(vertex2.getName(), edge1);
        vertex2.edges.put(vertex1.getName(), edge2);

        edges.add(edge1);
        this.sum += weight;
    }




    public void removeEdge(Edge edge){
        edge.source.edges.remove(edge.destination.getName());
        edge.destination.edges.remove(edge.source.getName());
        edges.remove(edge);
    }

    public void removeVertex(Vertex vertex){

    }

    public void traverse(){
        System.out.println("");
        edges.forEach(s -> System.out.println(s.source.getName() + " - " + s.destination.getName() + "  " + s.flow + "" + " / " + s.weight + ""));

        System.out.println("\nmax flow: " + this.flow);
        System.out.println("\nsum weight: " + this.sum);
    }






    /**
     * DFS
     * O(E + V)
     */
    public void DFT(String name){
        DFTVisit(vertices.get(name), "", "(0) ");
        vertices.values().forEach(s -> s.visit = false);
    }

    private void DFTVisit(Vertex vertex, String lvl, String dis){
        if (vertex.visit) return;
        System.out.println(lvl + dis + vertex.getName());
        vertex.visit = true;

        Object[] edger = vertex.edges.values().toArray();

        for (int i = 0; i < edger.length; i++) {
            DFTVisit(((Edge) edger[i]).destination, lvl + "    ", ("(" + ((Edge) edger[i]).weight + ") "));
        }
    }






    /**
     * DFS
     * O(E + V)
     */
    public String DFS(String start, String finish){
        String st = DFSVisit(vertices.get(start), vertices.get(finish), start + " -> ");
        vertices.values().forEach(s -> s.visit = false);
        return st;
    }

    private String DFSVisit(Vertex start, Vertex finish, String path){
        String result = "";
        if (start.equals(finish)) return path.substring(0, path.length()-4);

        if (start.visit) return "";

        start.visit = true;

        Object[] edger = start.edges.values().toArray();

        for (int i = 0; i < edger.length; i++) {
            path += ((Edge) edger[i]).destination.getName() + " -> ";
            result += DFSVisit(((Edge) edger[i]).destination, finish, path);
        }
        return result;
    }







    /**
     * BFS
     * O(E + V)
     */
    public void BFS(String name){
        LinkedQueue<Vertex<V>> queue = new LinkedQueue();
        BFSVisit(vertices.get(name), queue, "");
        vertices.values().forEach(s -> s.visit = false);
    }

    private void BFSVisit(Vertex vertex, LinkedQueue queue, String lvl){
        if (vertex.visit) return;
        vertex.visit = true;
        queue.add(vertex);

        while (!queue.isEmpty()){
            Vertex v1 = (Vertex) queue.remove();

            System.out.println(lvl + v1.getName());

            Object[] edger = v1.edges.values().toArray();

            for (int i = 0; i < edger.length; i++) {
                if (!((Edge) edger[i]).destination.visit) {
                    ((Edge) edger[i]).destination.visit = true;
                    queue.add(((Edge) edger[i]).destination);
                }
            }
        }
    }







    /**
     * Floyd-Warshall algorithm
     * O(V^3)
     */
    public void minPathAllToAll(){
        DecimalFormat df = new DecimalFormat("#");
        df.setMinimumIntegerDigits(3);
        Object[] v = vertices.values().toArray();
        int[][] matrix = new int[v.length][v.length];

        //init
        for (int i = 0; i < v.length; i++) {
            for (int j = 0; j < v.length; j++) {
                if (((Vertex)v[i]).edges.containsKey(((Vertex)v[j]).getName())){
                    matrix[i][j] = ((Edge)((Vertex)v[i]).edges.get(((Vertex)v[j]).getName())).weight;
                }
                else matrix[i][j] = 999;
                if (i == j) matrix[i][j] = 0;
            }
        }


        printMatrix(matrix);

        // All-To-All Floyd-Warshall algorithm
        for (int k = 0; k < v.length; k++) {
            for (int j = 0; j < v.length; j++) {
                for (int i = 0; i < v.length; i++) {
                    matrix[i][j] = Math.min(matrix[i][j], matrix[i][k] + matrix[k][j]);
                }
            }
        }

        printMatrix(matrix);
    }

    private void printMatrix(int[][] matrix){
        DecimalFormat df = new DecimalFormat("#");
        df.setMinimumIntegerDigits(3);
        Object[] v = vertices.values().toArray();


        System.out.println("");
        System.out.print("     ");
        for (int i = 0; i < v.length; i++) {
            System.out.print(((Vertex)v[i]).name + "   ");
        }
        System.out.println(" ");

        for (int i = 0; i < v.length; i++) {
            System.out.print(((Vertex)v[i]).getName() + "   ");
            for (int j = 0; j < v.length; j++) {
                System.out.print(df.format(matrix[i][j]) + " ");
            }
            System.out.println(" ");
        }
    }







    //TODO subgraph relations
    /**
     * Prim’s algorithm
     * O(E log V)
     */
    public Graph MST(String name){
        Graph subgraph = new Graph();
        vertices.values().forEach(s -> subgraph.addVertex(subgraph.setVertex(s.getName())));
        Boolean first = true;
        Vertex vertex = vertices.get(name);
        vertices.values().forEach(s -> {s.key = 9999999 ; s.shortest = null;});

        vertex.key = 0;

        NOV2017.BinaryHeap queue = new NOV2017.BinaryHeap();
        vertices.values().forEach(s -> queue.insert(s.key, s));


        while (!queue.isEmpty()){
            Vertex ve = (Vertex) queue.removeMin();


            if (!first) {
                subgraph.edges.add(ve.shortest);
                subgraph.sum += ve.shortest.weight;
                //TODO subgraph relations
            }

            Object[] edges1 = ve.edges.values().toArray();

            for (int i = 0; i < edges1.length; i++) {
                if (((Edge)edges1[i]).weight < ((Edge)edges1[i]).destination.key){
                    ((Edge)edges1[i]).destination.key = ((Edge)edges1[i]).weight;
                    ((Edge)edges1[i]).destination.shortest = ((Edge)edges1[i]);
                    queue.upData();
                }
            }
            first = false;
        }
        return subgraph;
    }

    /**
     * Dijkstra
     * O(E log V)
     */
    public void Dijkstra(String s){
        Vertex start = vertices.get(s);
        System.out.println("");
        vertices.values().forEach(l -> {l.visit = false; l.key = 999999;} );
        start.key = 0;

        PriorityQueue<Vertex> queue = new PriorityQueue();
        queue.add(start);

        while (!queue.isEmpty()){
            Vertex vertex = queue.remove();
            vertex.visit = true;
            vertex.edges.values().forEach(l -> {

                if (!((Edge)l).destination.visit){

                    ((Edge)l).destination.key = Math.min(((Edge)l).weight + vertex.key, ((Edge)l).destination.key);
                    queue.add(((Edge)l).destination);
                }

            });


        }

        DecimalFormat df = new DecimalFormat("#");
        df.setMinimumIntegerDigits(3);

        System.out.println("     " + s);
        vertices.values().forEach(z -> System.out.println(z.name + "   " + df.format(z.key)));
    }







    //TODO isMaxFlow

    /**
     * Ford & Fulkerson Algorithm
     * O(E * f)
     * @param Source
     * @param Sink
     */
    public void maxFlow(Vertex Source, Vertex Sink){
        edges.forEach(l -> l.flow = 0);

        isMaxFlow = false;

        while (!isMaxFlow){
            vertices.values().forEach(d -> d.visit = false);
            if (visit(Source, Sink)) {
                System.out.println("");
            }else isMaxFlow = true;
        }


        Sink.InEdges.values().forEach(l -> flow += ((Edge)l).flow);

        traverse();
    }

    private boolean visit(Vertex vertex, Vertex Sink){
        if (vertex.equals(Sink)) return true;
        vertex.visit = true;

        Object[] z = vertex.edges.values().toArray();
        for (int i = 0; i < z.length; i++) {

            if (!((Edge)z[i]).destination.visit){

                if (((Edge)z[i]).flow < ((Edge)z[i]).weight){

                    if (visit(((Edge)z[i]).destination, Sink)){

                        System.out.print(((Edge)z[i]).destination.name + " <- " + ((Edge)z[i]).source.name + " ");
                        ((Edge)z[i]).flow++;
                        return true;
                    }
                }
            }
        }


        Object[] g = vertex.InEdges.values().toArray();
        for (int j = 0; j < g.length; j++) {

            if (!((Edge)g[j]).source.visit){

                if (((Edge)g[j]).flow > 0){

                    if (visit(((Edge)g[j]).source, Sink)){

                        System.out.print(((Edge)g[j]).source.name + " <- " + ((Edge)g[j]).destination.name + " ");
                        ((Edge)g[j]).flow--;
                        return true;
                    }
                }
            }
        }
        return false;
    }









    /**
     * Vertex
     * @param <V>
     */
    public static class Vertex<V> implements Comparable{
        private String name;
        private boolean visit;
        public int key;
        private Edge shortest;

        public Hashtable<String,Edge<V>> edges;
        public Hashtable<String,Edge<V>> InEdges;

        public Vertex(String name) {
            this.name = name;
            this.visit = false;
            this.edges = new Hashtable<>();
            this.InEdges = new Hashtable<>();
        }

        public int degree(){
            return edges.size();
        }

        public String getName() {
            return name;
        }

        public void traverse(){
            edges.values().forEach(s -> System.out.println(s.source.getName() + " -> " + s.destination.getName()));
        }

        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + ((name == null) ? 0 :
                    name.hashCode());
            return result;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj)
                return true;
            if (obj == null)
                return false;
            if (getClass() != obj.getClass())
                return false;
            Vertex other = (Vertex) obj;
            if (name == null) {
                if (other.name != null)
                    return false;
            } else if (!name.equals(other.name))
                return false;
            return true;
        }

        @Override
        public int compareTo(Object o) {
            return key - ((Vertex)o).key;
        }
    }







    /**
     * Edge
     * @param <V>
     */
    public static class Edge<V> {
        private Vertex<V> source;
        private Vertex<V> destination;
        int weight = 0;
        int flow = 0;

        public Edge(Vertex<V> source, Vertex<V> destination, int weight) {
            super();
            this.source = source;
            this.destination = destination;
            this.weight = weight;
        }

        public Vertex<V> getSource() {
            return source;
        }

        public Vertex<V> getDestination() {
            return destination;
        }

        public int getWeight() {
            return weight;
        }

        @Override
        public String toString() {
            return "Edge [source=" + source.getName() + ", destination=" + destination.getName()
                    + ", weight=" + weight + "]";
        }


        public boolean equals(Edge edge){
            return destination.equals(edge.source) && source.equals(edge.destination);
        }

    }

}
