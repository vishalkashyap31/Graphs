package edu.cs.ds.graphs;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

/**
 * Implementation of a directed Graph data structure
 */
public class Graph {

    Set<Node> graph = new HashSet<>();

    /**
     * Add a new node into the {@link Graph}
     * 
     * @param data
     *            a int representing the data in the node
     */
    public void addNode(final int data) {
        graph.add(new Node(data));
    }

    /**
     * Add a new adjacency into the {@link Graph}
     * 
     * @param source
     *            a int representing the source node data
     * @param target
     *            a int representing the target node data
     * @param weight
     *            a int representing the weight of the adjacency
     * @throws IOException
     *             thrown when either the source or target node is not found in the {@link Graph}
     */
    public void addAdjacency(final int source, final int target, final int weight) throws IOException {

        final Optional<Node> optionalSrcNode = graph.parallelStream().filter(currentNode -> currentNode.getData() == source).findFirst();
        final Optional<Node> optionalTargetNode = graph.parallelStream().filter(currentNode -> currentNode.getData() == target).findFirst();

        if (optionalSrcNode.isPresent() && optionalTargetNode.isPresent()) {
            optionalSrcNode.get().addAdjacency(optionalTargetNode.get(), weight);
        }
    }

    /**
     * Do a depth first search using a {@link Stack} form the provided source node
     * 
     * @param srcNode
     *            a int representing the source node data
     * @return a {@link List} of {@link Node} in the depth first manner
     * @throws IOException
     *             thrown when either the source is not found in the {@link Graph}
     */
    public List<Node> depthFirstSearch(final int srcNode) throws IOException {

        final Optional<Node> optionalNode = graph.parallelStream().filter(currentNode -> currentNode.getData() == srcNode).findFirst();
        if (!optionalNode.isPresent()) {
            throw new IOException("Could not find the node in the graph");
        }

        final Node root = optionalNode.get();
        final List<Node> orderedVisitedNodeList = new ArrayList<>();
        final Stack<Node> nodeStack = new Stack<>();
        nodeStack.push(root);

        while (!nodeStack.empty()) {
            final Node currentNode = nodeStack.pop();
            if (orderedVisitedNodeList.contains(currentNode)) {
                continue;
            }
            orderedVisitedNodeList.add(currentNode);
            currentNode.getAdjacencies().stream()
                    .map(vertex -> vertex.getTargetNode())
                    .forEach(nodeStack::push);
        }
        return orderedVisitedNodeList;
    }

    /**
     * Do a breadth first search using a {@link Queue} form the provided source node
     *
     * @param srcNode
     *            a int representing the source node data
     * @return a {@link List} of {@link Node} in the breath first manner
     * @throws IOException
     *             thrown when either the source is not found in the {@link Graph}
     */
    public List<Node> breadthFirstSearch(final int srcNode) throws IOException {
        final Optional<Node> optionalNode = graph.parallelStream().filter(currentNode -> currentNode.getData() == srcNode).findFirst();
        if (!optionalNode.isPresent()) {
            throw new IOException("Could not find the node in the graph");
        }

        final Node root = optionalNode.get();
        final List<Node> orderedVisitedNodeList = new ArrayList<>();
        final Queue<Node> nodeQueue = new LinkedList<>();
        nodeQueue.add(root);

        while (!nodeQueue.isEmpty()) {
            final Node currentNode = nodeQueue.poll();
            if (orderedVisitedNodeList.contains(currentNode)) {
                continue;
            }
            orderedVisitedNodeList.add(currentNode);
            currentNode.getAdjacencies().stream()
                    .map(vertex -> vertex.getTargetNode())
                    .forEach(nodeQueue::add);

        }
        return orderedVisitedNodeList;
    }

    /**
     * Check if a cycle exists from the provided node in teh {@link Graph}
     * 
     * @param srcNode
     *            a int representing the source node data
     * @return true if cycle exists
     * @throws IOException
     *             thrown when either the source is not found in the {@link Graph}
     */
    public boolean isCyclic(final int srcNode) throws IOException {
        final Optional<Node> optionalNode = graph.parallelStream().filter(currentNode -> currentNode.getData() == srcNode).findFirst();
        if (!optionalNode.isPresent()) {
            throw new IOException("Could not find the node in the graph");
        }

        final Node root = optionalNode.get();
        final List<Node> orderedVisitedNodeList = new ArrayList<>();
        final Queue<Node> nodeQueue = new LinkedList<>();
        nodeQueue.add(root);

        while (!nodeQueue.isEmpty()) {
            final Node currentNode = nodeQueue.poll();
            if (orderedVisitedNodeList.contains(currentNode)) {
                return true;
            }
            orderedVisitedNodeList.add(currentNode);
            currentNode.getAdjacencies().stream()
                    .map(vertex -> vertex.getTargetNode())
                    .forEach(nodeQueue::add);

        }
        return false;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        graph.forEach(node -> sb.append(node.toString()));
        return sb.toString();
    }
}
