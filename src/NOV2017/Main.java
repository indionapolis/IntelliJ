package NOV2017;

/**
 * Project name: HomeWork
 * Created by pavel on 13.11.2017.
 * Pavel Nikulin BS1-8
 * Copyright ©
 * v1.0
 */
public class Main {
    public static void main(String[] args) {
        //NOV2017/graph_maxFlow.png
        Graph digraph = new Graph();

        Graph.Vertex s = digraph.setVertex("S");
        Graph.Vertex t = digraph.setVertex("T");
        Graph.Vertex a = digraph.setVertex("A");
        Graph.Vertex b = digraph.setVertex("B");
        Graph.Vertex c = digraph.setVertex("C");
        Graph.Vertex d = digraph.setVertex("D");
        Graph.Vertex f = digraph.setVertex("F");

        digraph.addVertex(s);
        digraph.addVertex(t);
        digraph.addVertex(a);
        digraph.addVertex(b);
        digraph.addVertex(c);
        digraph.addVertex(d);
        digraph.addVertex(f);

        digraph.addDEdge(s, a, 2);
        digraph.addDEdge(s, b, 3);
        digraph.addDEdge(s, c, 1);

        digraph.addDEdge(a, c, 2);
        digraph.addDEdge(a, d, 1);

        digraph.addDEdge(b, c, 2);
        digraph.addDEdge(b, f, 2);

        digraph.addDEdge(c, f, 4);
        digraph.addDEdge(c, d, 2);
        digraph.addDEdge(c, t, 2);

        digraph.addDEdge(d, t, 2);
        digraph.addDEdge(f, t, 1);

        digraph.maxFlow(s, t);



        //NOV2017/graph_MST.jpg
        Graph<Graph.Vertex> graph = new Graph();

        Graph.Vertex vA = graph.setVertex("A");
        graph.addVertex(vA);
        Graph.Vertex vB = graph.setVertex("B");
        graph.addVertex(vB);
        Graph.Vertex vC = graph.setVertex("C");
        graph.addVertex(vC);
        Graph.Vertex vD = graph.setVertex("D");
        graph.addVertex(vD);
        Graph.Vertex vE = graph.setVertex("E");
        graph.addVertex(vE);
        Graph.Vertex vF = graph.setVertex("F");
        graph.addVertex(vF);
        Graph.Vertex vG = graph.setVertex("G");
        graph.addVertex(vG);
        Graph.Vertex vH = graph.setVertex("H");
        graph.addVertex(vH);
        Graph.Vertex vI = graph.setVertex("I");
        graph.addVertex(vI);



        graph.addEdge(vA, vB, 4);
        graph.addEdge(vA, vH, 9);
        graph.addEdge(vB, vH, 11);

        graph.addEdge(vB, vC, 8);
        graph.addEdge(vI, vC, 2);
        graph.addEdge(vI, vH, 7);

        graph.addEdge(vI, vG, 6);
        graph.addEdge(vH, vG, 1);

        graph.addEdge(vD, vC, 7);
        graph.addEdge(vF, vC, 4);
        graph.addEdge(vF, vG, 2);
        graph.addEdge(vD, vF, 14);

        graph.addEdge(vD, vE, 9);
        graph.addEdge(vF, vE, 10);


        graph.MST("A").traverse();
        graph.minPathAllToAll();
        graph.Dijkstra("B");
    }
}
