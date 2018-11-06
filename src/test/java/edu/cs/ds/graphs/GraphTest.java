package edu.cs.ds.graphs;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

import java.io.IOException;
import java.util.List;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class GraphTest {

    private final Graph graph = new Graph();

    @Before
    public void init() throws IOException {
        graph.addNode(0);
        graph.addNode(1);
        graph.addNode(2);
        graph.addNode(3);
        graph.addNode(4);
        graph.addNode(5);
        graph.addNode(6);

        graph.addAdjacency(0, 6, 10);
        graph.addAdjacency(0, 3, 20);
        graph.addAdjacency(0, 2, 10);
        graph.addAdjacency(2, 1, 0);
        graph.addAdjacency(4, 5, 1);
        graph.addAdjacency(4, 3, 2);
        graph.addAdjacency(3, 6, 5);
        graph.addAdjacency(5, 6, 30);
    }

    @Test
    public void testDfs() throws IOException {
        final List<Node> dfsNodes = graph.depthFirstSearch(0);
        assertEquals(dfsNodes.get(0).getData(), 0);
        assertEquals(dfsNodes.get(1).getData(), 2);
        assertEquals(dfsNodes.get(2).getData(), 1);
        assertEquals(dfsNodes.get(3).getData(), 3);
        assertEquals(dfsNodes.get(4).getData(), 6);
    }

    @Test
    public void testBfs() throws IOException {
        final List<Node> dfsNodes = graph.breadthFirstSearch(0);
        assertEquals(dfsNodes.get(0).getData(), 0);
        assertEquals(dfsNodes.get(1).getData(), 6);
        assertEquals(dfsNodes.get(2).getData(), 3);
        assertEquals(dfsNodes.get(3).getData(), 2);
        assertEquals(dfsNodes.get(4).getData(), 1);
    }

    @Test
    public void testPrint() throws IOException {
        System.out.println("---------------DFS---------------");
        graph.depthFirstSearch(0).stream().forEach(System.out::println);

        System.out.println("---------------BFS---------------");
        graph.breadthFirstSearch(0).stream().forEach(System.out::println);
    }

    @Test
    public void testIsCyclic() throws IOException {
        assertTrue(graph.isCyclic(0));
    }

}
