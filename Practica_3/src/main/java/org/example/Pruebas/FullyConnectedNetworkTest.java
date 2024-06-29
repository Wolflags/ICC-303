package org.example.Pruebas;


import org.example.Frameworks.FullyConnectedNetwork;
import org.example.NetworkManager;

public class FullyConnectedNetworkTest {
    public static void main(String[] args) {
        NetworkManager manager = new NetworkManager();
        manager.configureNetwork(new FullyConnectedNetwork());
        manager.runNetwork();
        manager.sendMessage(0, 4, "Hola Mundo");
        manager.sendMessage(1, 0, "Hola Mundo");
        manager.shutdown();
    }
}
