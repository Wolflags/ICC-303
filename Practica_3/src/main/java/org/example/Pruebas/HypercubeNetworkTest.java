package org.example.Pruebas;

import org.example.Frameworks.HypercubeNetwork;
import org.example.NetworkManager;

public class HypercubeNetworkTest {
    public static void main(String[] args) {
        NetworkManager manager = new NetworkManager();
        manager.configureNetwork(new HypercubeNetwork());
        manager.runNetwork();
        manager.sendMessage(0, 4, "Hola Mundo");
        manager.sendMessage(1, 0, "Hola Mundo");
        manager.shutdown();
    }
}
