package org.example.Pruebas;

import org.example.Frameworks.RingNetwork;
import org.example.NetworkManager;

public class RingNetworkTest {
    public static void main(String[] args) {

        NetworkManager manager = new NetworkManager();
        manager.configureNetwork(new RingNetwork());
        manager.runNetwork();
        manager.sendMessage(0, 4, "Hola Mundo");
        manager.shutdown();
    }
}
