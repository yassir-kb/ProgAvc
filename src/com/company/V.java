package com.company;

import java.util.ArrayList;
import java.util.List;

public class V {

    private int label;
    private List<Integer> neighbors = new ArrayList<>();
    private boolean isVisited = false;


    public List<Integer> getNeighbors() {
        return neighbors;
    }

    public boolean getIsVisited() {
        return isVisited;
    }

    public void setIsVisited(boolean newIsVisited) {
        this.isVisited = newIsVisited;
    }

    public int getLabel() {
        return label;
    }

    public void setLabel(int newLabel) {
        this.label = newLabel;
    }

}