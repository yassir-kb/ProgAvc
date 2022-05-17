package com.company;

import java.util.ArrayList;
import java.util.List;


public abstract class Graph {

    private static List<String> GData = new ArrayList<>();
    private static List<List<V>> adjacent = new ArrayList<>();
    private List<V> List = new ArrayList<>();

    public static List<List<V>> getGraph(List<String> graphData) {
        List<List<V>> graph = new ArrayList<>();
        String[] graphStringRow;
        List<Integer> graphIntRow = new ArrayList<>();
        for (int i = 0; i < graphData.size(); i++) {
            graphStringRow = graphData.get(i).split(" ");
            for (int index = 0; index < graphStringRow.length; index++) {
                Integer node = Integer.parseInt(graphStringRow[index]);
                graphIntRow.add(node);
            }
            graph.add(lineValues(graphIntRow.get(0), graphIntRow.get(1)));
            graphIntRow.remove(1);
            graphIntRow.remove(0);
        }
        return graph;
    }

    public void getEdge(List<String> graphData) {
        String[] graphStringRow;
        List<Integer> graphIntRow = new ArrayList<>();

        for (int i = 0; i < graphData.size(); i++) {
            graphStringRow = graphData.get(i).split(" ");
            for (int index = 0; index < graphStringRow.length; index++) {
                Integer node = Integer.parseInt(graphStringRow[index]);
                graphIntRow.add(node);
            }
            addEdge(graphIntRow.get(0), graphIntRow.get(1));

            graphIntRow.remove(1);
            graphIntRow.remove(0);
        }
    }

    public void addEdge(int u, int v) {
        for (V node : this.List) {
            if (u == v && node.getLabel() == u) {
                node.getNeighbors().add(u);
            } else {
                if (node.getLabel() == u && !node.getNeighbors().contains(v)) {
                    node.getNeighbors().add(v);
                }
                if (node.getLabel() == v && !node.getNeighbors().contains(u)) {
                    node.getNeighbors().add(u);
                }
            }
        }
    }

    public V getNode(int nodeLabel) {
        V searchedNode = new V();
        for (V node : this.List) {
            if (node.getLabel() == nodeLabel) {
                searchedNode = node;
            }
        }
        return searchedNode;
    }

    public List<V> getNodeList(List<List<V>> G) {
        List<V> nodeList = new ArrayList<>();
        List<Integer> nodeListInt = new ArrayList<>();
        for (List<V> line : G) {
            for (int j = 0; j < 2; j++) {
                if (nodeListInt.size() == 0) {
                    nodeListInt.add(line.get(j).getLabel());
                } else {
                    if (!nodeListInt.contains(line.get(j).getLabel())) {
                        nodeListInt.add(line.get(j).getLabel());
                    }
                }
            }
        }
        for (Integer node : nodeListInt) {
            V newNode = new V();
            newNode.setLabel(node);
            nodeList.add(newNode);
        }
        return nodeList;
    }

    public int getOrder() {
        return this.List.size();
    }

    public int getSize(List<List<V>> graph) {
        return graph.size();
    }

    public static List<V> lineValues(int i, int j) {
        List<V> temp = new ArrayList<>();
        V iNode = new V();
        iNode.setLabel(i);
        V jNode = new V();
        jNode.setLabel(j);
        temp.add(iNode);
        temp.add(jNode);
        return temp;
    }

    public Graph(List<String> graphData) {
        this.GData = graphData;
    }

    public List<List<V>> getAdj() {
        return adjacent;
    }

    public void setAdj(List<List<V>> newAdj) {
        this.adjacent = newAdj;
    }

    public List<String> getGraphData() {
        return GData;
    }

    public List<V> getList() {
        return List;
    }

    public void setList(List<V> newNodeList) {
        this.List = newNodeList;
    }

    public void initialize() {
        this.adjacent = getGraph(GData);
        this.List = getNodeList(adjacent);
        getEdge(GData);
    }
}
