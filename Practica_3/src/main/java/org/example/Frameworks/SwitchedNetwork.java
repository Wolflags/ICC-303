package org.example.Frameworks;

import org.example.Interfaces.NetworkTopology;
import org.example.Node;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class SwitchedNetwork implements NetworkTopology {
    private List<Node> nodes = new ArrayList<>();
    private ExecutorService executor;

    @Override
    public void configureNetwork(int numberOfNodes) {
        nodes.clear();
        for (int i = 0; i < numberOfNodes; i++) {
            nodes.add(new Node(i));
        }
        executor = Executors.newFixedThreadPool(numberOfNodes);
    }

    @Override
    public void sendMessage(int from, int to, String message) {
        if (from < 0 || from >= nodes.size() || to < 0 || to >= nodes.size()) {
            System.out.println("Invalid node index");
            return;
        }
        Node fromNode = nodes.get(from);
        Node toNode = nodes.get(to);

        executor.execute(() -> {
            fromNode.receiveMessage("Sending message to " + to);
            toNode.receiveMessage(message);
        });
    }

    @Override
    public void runNetwork() {
        for (Node node : nodes) {
            executor.execute(node::run);
        }
    }

    @Override
    public void shutdown() {
        executor.shutdown();
        try {
            if (!executor.awaitTermination(60, TimeUnit.SECONDS)) {
                executor.shutdownNow();
            }
        } catch (InterruptedException e) {
            executor.shutdownNow();
            Thread.currentThread().interrupt();
        }
    }
}
