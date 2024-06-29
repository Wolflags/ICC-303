package org.example.Pruebas;

import org.example.Frameworks.MeshNetwork;
import org.example.NetworkManager;

public class MeshNetworkTest {
    public static void main(String[] args) {
        NetworkManager manager = new NetworkManager();
        manager.configureNetwork(new MeshNetwork());
        manager.runNetwork();
        manager.sendMessage(0, 1, "Hola Mundo");
        manager.sendMessage(1, 4, "Hola Mundo");
        manager.shutdown();
    }
}
