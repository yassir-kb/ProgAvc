package com.company;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BFSShortestPaths extends Graph {
    private boolean[] marked;
    private int[] previous;
    private int[] distance;
    private List<List<V>> paths = new ArrayList<>();

    public void bfs(Graph G, int s) {
        int order = getOrder();
        marked = new boolean[order];
        previous = new int[order];
        distance = new int[order];
        V lastNode = new V();
        lastNode.setLabel(200);
        for (int k = 0; k < order; k++) {
            List<V> queue = new ArrayList<>();
            queue.add(G.getNode(s));
            List<V> vertices = new ArrayList<>();
            vertices.add(G.getNode(s));
            marked[G.getNode(s).getLabel()] = true;
            V pNode = G.getNode(s);
            previous[G.getNode(s).getLabel()] = 0;
            if (k != s) {
                while (queue.size() != 0 && lastNode.getLabel() != G.getNode(k).getLabel()) {
                    V nodeFirst = queue.get(0);
                    List<Integer> notVisited = new ArrayList<>();
                    for (int neighbors : nodeFirst.getNeighbors()) {
                        for (V node : G.getList()) {
                            if (node.getLabel() == neighbors && marked[node.getLabel()] != true)
                                notVisited.add(node.getLabel());
                        }
                    }
                    if (notVisited.size() == 0) queue.remove(nodeFirst);
                    else {
                        int tnode = 200;
                        while (notVisited.size() != 0 && tnode != k) {
                            tnode = Collections.min(notVisited);
                            vertices.add(G.getNode(tnode));
                            queue.add(G.getNode(tnode));
                            marked[G.getNode(tnode).getLabel()] = true;
                            previous[G.getNode(tnode).getLabel()] = pNode.getLabel();
                            notVisited.remove(notVisited.indexOf(tnode));
                            pNode = G.getNode(tnode);
                            lastNode = G.getNode(tnode);
                        }
                    }
                }
            }
            for (int i = 0; i < vertices.size(); i++) {
                paths.add(new ArrayList<>());
                paths.get(k).add(vertices.get(i));
            }
            distance[k] = vertices.size() - 1;

            for (int i = vertices.size(); i > 0; i--) {
                vertices.remove(i - 1);
            }
            for (int i = 0; i < order; i++) {
                marked[i] = false;
                previous[i] = 0;
            }
        }
    }

    public BFSShortestPaths(List<String> graphData) {
        super(graphData);
    }

    public boolean hasPathTo(int v) {
        if (paths.get(v).size() > 0) {
            return true;
        }
        return false;
    }

    public int distTo(int v) {
        return distance[v];
    }

    public void printSP(int v) {
        System.out.print("\nBFS for the node " + v + " : ");
        for (int i = 0; i < paths.get(v).size(); i++) {
            System.out.print(paths.get(v).get(i).getLabel() + " ");
        }
    }

}