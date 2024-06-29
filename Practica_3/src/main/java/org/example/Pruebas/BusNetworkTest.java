package org.example.Pruebas;


import org.example.Frameworks.BusNetwork;
import org.example.NetworkManager;

public class BusNetworkTest {

    public static void main(String[] args) {
        //Crear una instancia de NetworkManager
        NetworkManager manager = new NetworkManager();

        //Configurar la red con la topologia de bus y otros parametros necesarios
        manager.configureNetwork(new BusNetwork());

        //Ejecutar la red
        manager.runNetwork();
        manager.sendMessage(0, 1, "Hola Mundo");
        manager.sendMessage(1, 2, "Hola Mundo");
        manager.sendMessage(2, 3, "Hola Mundo");
        manager.sendMessage(3, 4, "Hola Mundo");
        manager.sendMessage(4, 0, "Hola Mundo");

        //Detener la red
        manager.shutdown();
    }


}
