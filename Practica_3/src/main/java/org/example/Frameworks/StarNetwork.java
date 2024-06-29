package org.example.Frameworks;

import org.example.Interfaces.NetworkTopology;
import org.example.Node;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class StarNetwork implements NetworkTopology {
    private List<Node> nodes = new ArrayList<>();
    private ExecutorService executor;
    private Node centralNode;  // Nodo central de la red

    @Override
    public void configureNetwork(int numberOfNodes) {
        executor = Executors.newFixedThreadPool(numberOfNodes);
        // Crear el nodo central
        centralNode = new Node(0);
        nodes.add(centralNode);

        // Crear los nodos periféricos y conectarlos al nodo central
        for (int i = 1; i < numberOfNodes; i++) {
            Node node = new Node(i);
            nodes.add(node);
        }
    }

    @Override
    public void sendMessage(int from, int to, String message) {
        if (from == 0) {
            // El nodo central envía directamente al destino
            nodes.get(to).receiveMessage(message);
        } else {
            // Los mensajes de otros nodos pasan por el nodo central
            System.out.println("Node " + from + " sends message to Node 0");
            centralNode.receiveMessage("Routing message to Node " + to + ": " + message);
            nodes.get(to).receiveMessage(message);
        }
    }

    @Override
    public void runNetwork() {
        for (Node node : nodes) {
            executor.submit(() -> {
                node.run();
            });
        }
    }

    @Override
    public void shutdown() {
        if (executor != null) {
            executor.shutdown();
        }
    }
}
