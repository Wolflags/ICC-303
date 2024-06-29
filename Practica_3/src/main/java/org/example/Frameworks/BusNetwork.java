package org.example.Frameworks;

import org.example.Interfaces.NetworkTopology;
import org.example.Node;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class BusNetwork implements NetworkTopology {
    private List<Node> nodes;
    private ExecutorService executor;


    @Override
    public void configureNetwork(int numberOfNodes) {
        nodes = new ArrayList<>();
        for (int i = 0; i < numberOfNodes; i++) {
            nodes.add(new Node(i));
        }

        executor = Executors.newFixedThreadPool(numberOfNodes);
    }

    @Override
    public void sendMessage(int from, int to, String message) {
        executor.submit(() -> nodes.get(to).receiveMessage(message));
    }

    @Override
    public void runNetwork() {
        //...
        for(Node node : nodes) {
            executor.submit(node::run);
        }
    }

    @Override
    public void shutdown() {
        executor.shutdown();
    }
}
