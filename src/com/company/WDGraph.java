package com.company;

import java.util.ArrayList;
import java.util.List;

public class WDGraph extends Graph {
    private static double[] weight;
    private DirectedEdge[] directedEdge;

    public WDGraph(List<String> G) {
        super(G);
    }

    public double[] getWeight() {
        return weight;
    }

    public DirectedEdge[] getDirectedEdge() {
        return directedEdge;
    }

    public void initialize() {
        setAdj(getGraph(getGraphData()));
        setList(getNodeList(getAdj()));
        getEdge(getGraphData());
    }

    /**
     * We use abstract class to optimize the implementation of our code in the project
     **/
    /*public static List<List<V>> getGraph(List<String> G) {
        weight = new double[G.size()];
        List<List<V>> graph = new ArrayList<>();
        String[] Gstringrow;
        List<Integer> Gintrow = new ArrayList<>();
        for (int i = 0; i < G.size(); i++) {
            Gstringrow = G.get(i).split(" ");
            for (int j = 0; j < 2; j++) {
                Integer node = Integer.parseInt(Gstringrow[j]);
                Gintrow.add(node);
            }
            graph.add(lineValues(Gintrow.get(0), Gintrow.get(1)));
            weight[i] = Double.parseDouble(Gstringrow[2]);

            Gintrow.remove(1);
            Gintrow.remove(0);
        }
        return graph;
    }

    public void getEdge(List<String> graphData) {
        String[] graphStringRow;
        List<Integer> graphIntRow = new ArrayList<>();

        for (int i = 0; i < graphData.size(); i++) {
            graphStringRow = graphData.get(i).split(" ");
            for (int index = 0; index < 2; index++) {
                Integer node = Integer.parseInt(graphStringRow[index]);
                graphIntRow.add(node);
            }
            addEdge(graphIntRow.get(0), graphIntRow.get(1));

            graphIntRow.remove(1);
            graphIntRow.remove(0);
        }
    }
*/
    public void setDirectedEdge() {
        directedEdge = new DirectedEdge[weight.length];
        for (int i = 0; i < weight.length; i++) {
            directedEdge[i] = new DirectedEdge(getAdj().get(i).get(0).getLabel(), getAdj().get(i).get(1).getLabel(), weight[i]);
        }
    }

    public void printAdjacencyList() {
        for (V node : this.getList()) {
            System.out.print(node.getLabel());
            for (DirectedEdge edge : directedEdge) {
                if (edge.from() == node.getLabel()) {
                    System.out.print(" -> " + node.getLabel() + " | " + edge.to() + " | " + edge.weight());
                }
            }
            System.out.println("");
        }
    }
}
