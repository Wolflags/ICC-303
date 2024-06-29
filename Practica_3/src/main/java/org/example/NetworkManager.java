package org.example;

import org.example.Frameworks.HypercubeNetwork;
import org.example.Interfaces.NetworkTopology;

public class NetworkManager {
    private NetworkTopology network;

    public void configureNetwork(NetworkTopology network) {
        this.network = network;
        if (network instanceof HypercubeNetwork) {
            this.network.configureNetwork(8);
            return;
        }
        this.network.configureNetwork(5);

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