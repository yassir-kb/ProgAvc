package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner(System.in);

        System.out.println("\ngraph-BFS-SP.txt");
        File graphFile = new File("src/com/company/graph-BFS-SP.txt");
        List<String> graphString = getStringData(graphFile);

        /*Graph G = new Graph(graphString);*/
        WDGraph G = new WDGraph(graphString);
        G.initialize();
        G.getGraph(graphString);
        G.getList();
        System.out.println("\nOrder is " + G.getOrder() + " and the size is " + G.getSize(G.getAdj()));


        System.out.println("\nDFS : ");
        System.out.print("Starting node is ");
        int startdfs = sc.nextInt();
        System.out.print("DFS : ");
        List<V> result = dfs(G, startdfs);
        for (V node : result) {
            System.out.print(node.getLabel() + " ");
        }
        System.out.println("\nGraph is filled with " + cc(result) + " components");
        if (isConnected(G, result) == true) System.out.println("The graph is connected.");
        else System.out.println("The graph is not connected.");

        for (V node : G.getList()) {
            node.setIsVisited(false);
        }

        System.out.println("\nBFS");
        System.out.print("Starting node is ");
        int startbfs = sc.nextInt();
        System.out.print("BFS : ");
        List<V> bfsResult = bfs(G, startbfs);
        for (V node : bfsResult) {
            System.out.print(node.getLabel() + " ");
        }
        System.out.println("\nGraph is filled with " + cc(bfsResult) + " components");
        if (isConnected(G, bfsResult) == true) System.out.println("The graph is connected.");
        else System.out.println("The graph is not connected.");


        System.out.println("\nBFS Shortest Paths");
        BFSShortestPaths BFSShortestPathsGraph = new BFSShortestPaths(graphString);
        BFSShortestPathsGraph.initialize();
        BFSShortestPathsGraph.getGraph(graphString);
        System.out.print("\nStarting node : ");
        int bfsShortestPathsStartingNode = sc.nextInt();
        BFSShortestPathsGraph.bfs(BFSShortestPathsGraph, bfsShortestPathsStartingNode);


        System.out.print("\nNode 1 :");
        System.out.print("\n" + 1 + " has path to root vertex : " + BFSShortestPathsGraph.hasPathTo(1));
        System.out.print("\nDistance from root vertex of node " + 1 + " : " + BFSShortestPathsGraph.distTo(1));
        BFSShortestPathsGraph.printSP(1);


        System.out.println("\n\ngraph-WDG.txt");
        File graphWDGFile = new File("src/com/company/graph-WDG.txt");
        List<String> graphWDGStringData = getStringData(graphWDGFile);
        WDGraph graphWDG = new WDGraph(graphWDGStringData);
        graphWDG.initialize();
        graphWDG.getGraph(graphWDGStringData);
        graphWDG.getList();
        System.out.println("\nOrder : " + graphWDG.getOrder());
        System.out.println("Size : " + graphWDG.getSize(graphWDG.getAdj()));
        graphWDG.setDirectedEdge();


        System.out.println("\nWeighted digraphs");
        graphWDG.printAdjacencyList();
    }

    public static List<V> dfs(Graph G, int first) {
        List<V> stack = new ArrayList<>();
        stack.add(G.getNode(first));
        List<V> vertices = new ArrayList<>();
        vertices.add(G.getNode(first));
        G.getNode(first).setIsVisited(true);
        while (stack.size() != 0) {
            V last = stack.get(stack.size() - 1);
            List<Integer> notVisited = new ArrayList<>();
            for (int neighbors : last.getNeighbors()) {
                for (V node : G.getList()) {
                    if (node.getLabel() == neighbors && node.getIsVisited() == false) notVisited.add(node.getLabel());
                }
            }
            if (notVisited.size() == 0) stack.remove(last);
            else {
                int tNode = Collections.min(notVisited);
                vertices.add(G.getNode(tNode));
                stack.add(G.getNode(tNode));
                G.getNode(tNode).setIsVisited(true);
            }
        }
        return vertices;
    }

    public static int cc(List<V> output) {
        return output.size();
    }

    public static List<V> bfs(Graph G, int first) {
        List<V> queue = new ArrayList<>();
        queue.add(G.getNode(first));
        List<V> vertices = new ArrayList<>();
        vertices.add(G.getNode(first));
        G.getNode(first).setIsVisited(true);
        while (queue.size() != 0) {
            V nodeFirst = queue.get(0);
            List<Integer> notVisited = new ArrayList<>();
            for (int neighbors : nodeFirst.getNeighbors()) {
                for (V node : G.getList()) {
                    if (node.getLabel() == neighbors && node.getIsVisited() == false) notVisited.add(node.getLabel());
                }
            }
            if (notVisited.size() == 0) queue.remove(nodeFirst);
            else {
                int tNode = 0;
                while (notVisited.size() != 0) {
                    tNode = Collections.min(notVisited);
                    vertices.add(G.getNode(tNode));
                    queue.add(G.getNode(tNode));
                    G.getNode(tNode).setIsVisited(true);
                    notVisited.remove(notVisited.indexOf(tNode));
                }
            }
        }
        return vertices;
    }

    public static List<String> getStringData(File file) throws FileNotFoundException {
        List<String> graphData = new ArrayList<>();
        Scanner myReader = new Scanner(file);
        while (myReader.hasNextLine()) {
            String data = myReader.nextLine();
            graphData.add(data);
        }
        return graphData;
    }

    public static boolean isConnected(Graph graph, List<V> output) {
        List<Integer> outputLabel = new ArrayList<>();
        for (V node : output) {
            outputLabel.add(node.getLabel());
        }
        for (V node : graph.getList()) {
            if (!outputLabel.contains(node.getLabel())) {
                return false;
            }
        }
        return true;
    }
}
