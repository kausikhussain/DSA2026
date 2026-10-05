package Practice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CloneGraph {

    // Definition for a Node.
    public static class Node {
        public int val;
        public List<Node> neighbors;

        public Node() {
            val = 0;
            neighbors = new ArrayList<>();
        }

        public Node(int _val) {
            val = _val;
            neighbors = new ArrayList<>();
        }

        public Node(int _val, ArrayList<Node> _neighbors) {
            val = _val;
            neighbors = _neighbors;
        }
    }

    /**
     * LeetCode 133: Clone Graph
     * 
     * Given a reference of a node in a connected undirected graph, 
     * return a deep copy (clone) of the graph.
     */
    public Node cloneGraph(Node node) {
        if (node == null) {
            return null;
        }

        // Map to store mapping between original nodes and their cloned counterparts
        Map<Node, Node> visited = new HashMap<>();

        return dfs(node, visited);
    }

    private Node dfs(Node node, Map<Node, Node> visited) {
        // If node has already been cloned, return the cloned instance
        if (visited.containsKey(node)) {
            return visited.get(node);
        }

        // Create the clone node
        Node clone = new Node(node.val);
        visited.put(node, clone);

        // Recursively clone all neighbors
        for (Node neighbor : node.neighbors) {
            clone.neighbors.add(dfs(neighbor, visited));
        }

        return clone;
    }

    public static void main(String[] args) {
        CloneGraph solution = new CloneGraph();

        // Construct 4-node graph:
        // 1 -- 2
        // |    |
        // 4 -- 3
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);

        node1.neighbors.add(node2);
        node1.neighbors.add(node4);

        node2.neighbors.add(node1);
        node2.neighbors.add(node3);

        node3.neighbors.add(node2);
        node3.neighbors.add(node4);

        node4.neighbors.add(node1);
        node4.neighbors.add(node3);

        Node cloned = solution.cloneGraph(node1);
        System.out.println("Cloned root val: " + cloned.val); 
        // Expected: 1
        System.out.println("Cloned is different reference: " + (cloned != node1)); 
        // Expected: true
        System.out.println("Cloned neighbors count: " + cloned.neighbors.size()); 
        // Expected: 2
    }
}
