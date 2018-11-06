package edu.cs.ds.graphs;

/**
 * Represents a Vertex in a {@link Graph}
 */
public class Vertex {

    private final int weight;
    private final Node targetNode;

    public Vertex(final int weight, final Node targetNode) {
        this.weight = weight;
        this.targetNode = targetNode;
    }

    public int getWeight() {
        return weight;
    }

    public Node getTargetNode() {
        return targetNode;
    }
}
