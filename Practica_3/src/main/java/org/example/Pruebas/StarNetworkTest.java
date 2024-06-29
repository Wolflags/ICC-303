package org.example.Pruebas;

import org.example.Frameworks.StarNetwork;
import org.example.NetworkManager;

public class StarNetworkTest {
    public static void main(String[] args) {
        NetworkManager manager = new NetworkManager();
        manager.configureNetwork(new StarNetwork());
        manager.runNetwork();
        manager.sendMessage(1, 3, "Hola Mundo");
        manager.shutdown();


    }
}
