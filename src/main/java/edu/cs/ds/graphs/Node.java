package edu.cs.ds.graphs;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

/**
 * Class represents a node in the graph A {@link Node} can have refrences of the adjacent {@link Node} called {@link Vertex}
 */
public class Node {

    private final int data;
    private final List<Vertex> adjacencies;

    public Node(final int data) {
        this.data = data;
        adjacencies = new LinkedList<>();
    }

    /**
     * Add a new adjacency to the current node
     * 
     * @param targetNode
     *            a {@link Node} which is adjacent to the current node
     * @param weight
     *            a int representing the weight of the path from the current node to targetNode
     * @throws IOException
     *             thrown when the targetNode is null
     */
    public void addAdjacency(final Node targetNode, final int weight) throws IOException {
        if (targetNode == null) {
            throw new IOException("Target node cannot be null");
        }
        adjacencies.add(new Vertex(weight, targetNode));
    }

    public int getData() {
        return data;
    }

    public List<Vertex> getAdjacencies() {
        return adjacencies;
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Node)) {
            return false;
        }
        if (data == ((Node) obj).data) {
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        final StringBuilder strBuilder = new StringBuilder();
        adjacencies.stream().forEach(i -> strBuilder.append(i.getTargetNode().data).append(':').append(i.getWeight()).append(','));
        return data + ":{" + strBuilder.toString() + "}";
    }

    @Override
    public int hashCode() {
        return Objects.hash(data);
    }
}
