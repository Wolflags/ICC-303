package org.example.Frameworks;

import org.example.Interfaces.NetworkTopology;
import org.example.Node;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MeshNetwork implements NetworkTopology {
    private List<Node> nodes = new ArrayList<>();
    private ExecutorService executor;

    @Override
    public void configureNetwork(int numberOfNodes) {
        nodes = new ArrayList<>(numberOfNodes);
        for (int i = 0; i < numberOfNodes; i++) {
            nodes.add(new Node(i));
        }

        for (int i = 0; i < numberOfNodes; i++) {
            Node currentNode = nodes.get(i);
            Node nextNode = nodes.get((i + 1) % numberOfNodes);  // Conexión circular
            Node prevNode = nodes.get((i - 1 + numberOfNodes) % numberOfNodes); // Conexión circular
            currentNode.setNextNode(nextNode);
            currentNode.setPrevNode(prevNode);
        }
    }

    @Override
    public void sendMessage(int from, int to, String message) {
        if (from < 0 || from >= nodes.size() || to < 0 || to >= nodes.size()) {
            System.out.println("Invalid node index");
            return;
        }

        Node startNode = nodes.get(from);
        Node currentNode = startNode;

        // Envia el mensaje a través de la red hasta alcanzar el destino
        while (currentNode != null && currentNode.getId() != to) {
            currentNode = currentNode.getNextNode();
            if (currentNode == startNode) break;  // Evita los ciclos infinitos
        }

        if (currentNode != null) {
            currentNode.receiveMessage(message);
        } else {
            System.out.println("Node " + to + " not found.");
        }
    }

    @Override
    public void runNetwork() {
        executor = Executors.newFixedThreadPool(nodes.size());
        for (Node node : nodes) {
            executor.submit(() -> {
                while (!Thread.currentThread().isInterrupted()) {
                    node.run();
                }
            });
        }
    }

    @Override
    public void shutdown() {
        //Espera a que todos los nodos terminen su trabajo
        executor.shutdownNow();
    }
}
