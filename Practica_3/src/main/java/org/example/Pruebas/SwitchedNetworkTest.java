package org.example.Pruebas;

import org.example.Frameworks.SwitchedNetwork;
import org.example.NetworkManager;

public class SwitchedNetworkTest {
    public static void main(String[] args) {
        NetworkManager manager = new NetworkManager();
        manager.configureNetwork(new SwitchedNetwork());
        manager.runNetwork();
        manager.sendMessage(1, 3, "Hola Mundo");
        manager.shutdown();
    }
}
