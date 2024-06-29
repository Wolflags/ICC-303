package org.example.Frameworks;

import org.example.Interfaces.NetworkTopology;
import org.example.Node;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class HypercubeNetwork implements NetworkTopology {
    private List<Node> nodes = new ArrayList<>();
    private ExecutorService executor;

    @Override
    public void configureNetwork(int numberOfNodes) {
        // Asegurar que el número de nodos es una potencia de 2
        if (Integer.bitCount(numberOfNodes) != 1) {
            throw new IllegalArgumentException("Number of nodes must be a power of 2 for Hypercube topology.");
        }

        // Inicializar los nodos
        for (int i = 0; i < numberOfNodes; i++) {
            nodes.add(new Node(i));
        }

        // Configurar las conexiones entre nodos
        int dimensions = Integer.numberOfTrailingZeros(numberOfNodes);
        for (int i = 0; i < numberOfNodes; i++) {
            for (int d = 0; d < dimensions; d++) {
                int neighborIndex = i ^ (1 << d);
                nodes.get(i).setNextNode(nodes.get(neighborIndex)); // Ejemplo simple de conexión bidireccional
                nodes.get(neighborIndex).setPrevNode(nodes.get(i));
            }
        }

        // Inicializar el ExecutorService
        executor = Executors.newFixedThreadPool(numberOfNodes);
    }

    @Override
    public void sendMessage(int from, int to, String message) {
        if (from < 0 || from >= nodes.size() || to < 0 || to >= nodes.size()) {
            throw new IllegalArgumentException("Invalid node index");
        }
        Node currentNode = nodes.get(from);
        while (currentNode.getId() != to) {
            int currentId = currentNode.getId();
            // Encontrar el bit más bajo donde difieren los IDs
            int bitToFlip = Integer.lowestOneBit(currentId ^ to);
            currentId = currentId ^ bitToFlip;
            currentNode = nodes.get(currentId);
        }
        currentNode.receiveMessage(message);
    }

    @Override
    public void runNetwork() {
        nodes.forEach(node -> executor.submit(node::run));
    }

    @Override
    public void shutdown() {
        if (executor != null && !executor.isShutdown()) {
            executor.shutdownNow();
        }
    }
}
