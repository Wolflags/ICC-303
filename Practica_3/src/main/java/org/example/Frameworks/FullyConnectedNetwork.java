package org.example.Frameworks;

import org.example.Interfaces.NetworkTopology;
import org.example.Node;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FullyConnectedNetwork implements NetworkTopology {
    private List<Node> nodes = new ArrayList<>();
    private ExecutorService executor;

    @Override
    public void configureNetwork(int numberOfNodes) {
        nodes.clear();
        executor = Executors.newFixedThreadPool(numberOfNodes);


        for (int i = 0; i < numberOfNodes; i++) {
            nodes.add(new Node(i));
        }


        for (Node node : nodes) {
            for (Node other : nodes) {
                if (node != other) {
                    node.addChild(other);
                }
            }
        }
    }

    @Override
    public void sendMessage(int from, int to, String message) {
        if (from >= 0 && from < nodes.size() && to >= 0 && to < nodes.size()) {
            Node sender = nodes.get(from);
            Node receiver = nodes.get(to);
            executor.submit(() -> {
                receiver.receiveMessage(message);
            });
        }
    }

    @Override
    public void runNetwork() {
        for (Node node : nodes) {
            executor.submit(node::run);
        }
    }

    @Override
    public void shutdown() {
        if (executor != null) {
            executor.shutdown();
        }
    }
}
