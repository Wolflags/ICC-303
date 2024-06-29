package org.example.Frameworks;

import org.example.Node;
import org.example.Interfaces.NetworkTopology;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class RingNetwork implements NetworkTopology {
    private List<Node> nodes = new ArrayList<>();
    private ExecutorService executor;

    @Override
    public void configureNetwork(int numberOfNodes) {
        for (int i = 0; i < numberOfNodes; i++) {
            nodes.add(new Node(i));
        }

        for (int i = 0; i < numberOfNodes; i++) {
            // Configura el siguiente nodo en el anillo, conectando el último con el primero
            nodes.get(i).setNextNode(nodes.get((i + 1) % numberOfNodes));
        }
    }

    @Override
    public void sendMessage(int from, int to, String message) {
        Node startNode = nodes.get(from);
        Node currentNode = startNode;

        do {
            currentNode.receiveMessage(message);
            currentNode = currentNode.getNextNode();
        } while (currentNode != startNode && currentNode.getId() != to);

        if (currentNode.getId() == to) {
            currentNode.receiveMessage(message);
        }
    }

    @Override
    public void runNetwork() {
        executor = Executors.newFixedThreadPool(nodes.size());
        for (Node node : nodes) {
            executor.execute(node::run);
        }
    }

    @Override
    public void shutdown() {
        if (executor != null) {
            executor.shutdown();
        }
    }
}
