package org.example;

import org.example.Frameworks.BusNetwork;
import org.example.Pruebas.*;

import static java.lang.Thread.sleep;

public class Main {
    public static void main(String[] args) throws InterruptedException {

        //Prueba BusNetwork
        System.out.println("Prueba BusNetwork:");
        BusNetworkTest.main(args);

        sleep(1000);

        //Prueba RingNetwork
        System.out.println("Prueba RingNetwork:");
        RingNetworkTest.main(args);

        sleep(1000);

        //Prueba MeshNetwork
        System.out.println("Prueba MeshNetwork:");
        MeshNetworkTest.main(args);

        sleep(1000);

        //Prueba StarNetwork
        System.out.println("Prueba StarNetwork:");
        StarNetworkTest.main(args);

        sleep(1000);

        //Prueba HypercubeNetwork
        System.out.println("Prueba HypercubeNetwork:");
        HypercubeNetworkTest.main(args);

        sleep(1000);

        //Prueba TreeNetwork
        System.out.println("Prueba TreeNetwork:");
        TreeNetworkTest.main(args);

        sleep(1000);

        //Prueba FullyConnectedNetwork
        System.out.println("Prueba FullyConnectedNetwork:");
        FullyConnectedNetworkTest.main(args);

        sleep(1000);

        //Prueba SwitchedNetwork
        System.out.println("Prueba SwitchedNetwork:");
        SwitchedNetworkTest.main(args);

    }
}