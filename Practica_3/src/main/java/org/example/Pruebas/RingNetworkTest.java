package org.example.Pruebas;

import org.example.Frameworks.RingNetwork;

public class RingNetworkTest {
    public static void main(String[] args) {

        RingNetwork ringNetwork = new RingNetwork();
        ringNetwork.configureNetwork(5);
        ringNetwork.runNetwork();
        ringNetwork.sendMessage(0, 1, "Hola desde 0 a 1");
        ringNetwork.sendMessage(1, 3, "Hola desde 1 a 4");
        ringNetwork.shutdown();
    }
}
