package org.example;

import org.example.Interfaces.NetworkTopology;

public class NetworkManager {
    private NetworkTopology network;

    public void configureNetwork(NetworkTopology network) {
        this.network = network;
        this.network.configureNetwork(5); // Configure network with 5 nodes default
    }

    public void runNetwork() {
        if (network != null) {
            network.runNetwork();
        }
    }

    public void sendMessage(int from, int to, String message) {
        if (network != null) {
            network.sendMessage(from, to, message);
        }
    }

    public void shutdown() {
        if (network != null) {
            network.shutdown();
        }
    }
}