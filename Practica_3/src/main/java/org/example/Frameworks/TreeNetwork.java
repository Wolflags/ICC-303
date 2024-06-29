package org.example.Frameworks;

import org.example.Interfaces.NetworkTopology;
import org.example.Node;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TreeNetwork implements NetworkTopology {
    private Node root; // Nodo raíz del árbol
    private ExecutorService executor = Executors.newCachedThreadPool();

    @Override
    public void configureNetwork(int numberOfNodes) {
        if (numberOfNodes < 1) return;
        root = new Node(0); // Nodo raíz
        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);
        int currentId = 1;

        while (currentId < numberOfNodes && !queue.isEmpty()) {
            Node current = queue.poll();
            if (currentId < numberOfNodes) {
                Node leftChild = new Node(currentId++);
                current.addChild(leftChild);
                queue.offer(leftChild);
            }
            if (currentId < numberOfNodes) {
                Node rightChild = new Node(currentId++);
                current.addChild(rightChild);
                queue.offer(rightChild);
            }
        }
    }

    @Override
    public void sendMessage(int from, int to, String message) {
        Node sender = findNode(root, from);
        Node receiver = findNode(root, to);
        if (sender != null && receiver != null) {
            executor.submit(() -> {
                receiver.receiveMessage(message);
            });
        } else {
            System.out.println("Sender or receiver not found");
        }
    }

    @Override
    public void runNetwork() {
        if (root != null) {
            runNode(root);
        }
    }

    private void runNode(Node node) {
        executor.submit(node::run);
        for (Node child : node.getChildren()) {
            runNode(child);
        }
    }

    @Override
    public void shutdown() {
        if (executor != null) {
            executor.shutdownNow();
        }
    }

    private Node findNode(Node node, int id) {
        if (node.getId() == id) {
            return node;
        }
        for (Node child : node.getChildren()) {
            Node found = findNode(child, id);
            if (found != null) {
                return found;
            }
        }
        return null;
    }
}
