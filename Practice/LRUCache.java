package Practice;

import java.util.HashMap;
import java.util.Map;

/**
 * LeetCode 146: LRU Cache
 * Difficulty: Medium
 * 
 * Design a data structure that follows the constraints of a 
 * Least Recently Used (LRU) cache.
 * 
 * Implement the LRUCache class:
 * - LRUCache(int capacity) Initialize the LRU cache with positive size capacity.
 * - int get(int key) Return the value of the key if the key exists, otherwise return -1.
 * - void put(int key, int value) Update the value of the key if the key exists. 
 *   Otherwise, add the key-value pair to the cache. If the number of keys exceeds the 
 *   capacity from this operation, evict the least recently used key.
 * 
 * The functions get and put must each run in O(1) average time complexity.
 * 
 * Approach:
 * - Doubly Linked List + Hash Map.
 * - HashMap provides O(1) key lookup to the Doubly Linked List Node.
 * - Doubly Linked List provides O(1) node insertion and deletion.
 * - Head dummy node is next to Most Recently Used (MRU) items.
 * - Tail dummy node is previous to Least Recently Used (LRU) items.
 * 
 * Complexity:
 * - Time: O(1) for both get() and put().
 * - Space: O(capacity) to store at most capacity nodes in HashMap and Doubly Linked List.
 */
public class LRUCache {

    private static class Node {
        int key;
        int value;
        Node prev;
        Node next;

        Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    private final int capacity;
    private final Map<Integer, Node> cache;
    private final Node head;
    private final Node tail;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.cache = new HashMap<>();

        // Initialize dummy head and tail nodes
        this.head = new Node(0, 0);
        this.tail = new Node(0, 0);
        this.head.next = this.tail;
        this.tail.prev = this.head;
    }

    public int get(int key) {
        Node node = cache.get(key);
        if (node == null) {
            return -1;
        }
        // Move the accessed node to the head (most recently used)
        moveToHead(node);
        return node.value;
    }

    public void put(int key, int value) {
        Node node = cache.get(key);

        if (node != null) {
            // Key already exists, update value and move to head
            node.value = value;
            moveToHead(node);
        } else {
            Node newNode = new Node(key, value);
            cache.put(key, newNode);
            addNode(newNode);

            // If capacity exceeded, evict the least recently used node
            if (cache.size() > capacity) {
                Node lruNode = popTail();
                cache.remove(lruNode.key);
            }
        }
    }

    /**
     * Inserts a new node right after the dummy head.
     */
    private void addNode(Node node) {
        node.prev = head;
        node.next = head.next;

        head.next.prev = node;
        head.next = node;
    }

    /**
     * Unlinks an existing node from the doubly linked list.
     */
    private void removeNode(Node node) {
        Node prevNode = node.prev;
        Node nextNode = node.next;

        prevNode.next = nextNode;
        nextNode.prev = prevNode;
    }

    /**
     * Moves a given node to the head (MRU position).
     */
    private void moveToHead(Node node) {
        removeNode(node);
        addNode(node);
    }

    /**
     * Removes and returns the LRU node right before the dummy tail.
     */
    private Node popTail() {
        Node lru = tail.prev;
        removeNode(lru);
        return lru;
    }

    public static void main(String[] args) {
        LRUCache lRUCache = new LRUCache(2);

        lRUCache.put(1, 1); // cache is {1=1}
        lRUCache.put(2, 2); // cache is {1=1, 2=2}
        System.out.println("get(1): " + lRUCache.get(1)); // return 1

        lRUCache.put(3, 3); // LRU key was 2, evicts key 2, cache is {1=1, 3=3}
        System.out.println("get(2): " + lRUCache.get(2)); // returns -1 (not found)

        lRUCache.put(4, 4); // LRU key was 1, evicts key 1, cache is {4=4, 3=3}
        System.out.println("get(1): " + lRUCache.get(1)); // return -1 (not found)
        System.out.println("get(3): " + lRUCache.get(3)); // return 3
        System.out.println("get(4): " + lRUCache.get(4)); // return 4
    }
}
