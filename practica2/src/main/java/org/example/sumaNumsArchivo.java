package org.example;

import java.io.*;
import java.util.Random;

public class sumaNumsArchivo {

    private static final String NOMBRE_ARCHIVO = "numeros";
    private static final int  NUM_REGISTROS = 1000000;

    public static void main(String[] args) throws IOException {
        // 1. Genera un archivo con 1,000,000 de registros comprendido entre 1 y 10,000,
        // el cual deberá usar como base para los demás cálculos.
        String nombreArchivo = "numeros";
        generarArchivo(nombreArchivo);


        // 2. Escribe un programa secuencial que sume los elementos de un arreglo de un
        // millón de enteros
        BufferedReader lector = new BufferedReader(new FileReader(nombreArchivo + ".txt"));
        String linea;
        long sumaSecuencial = 0;

        // Empieza el proceso de suma
        long inicioSecuencial = System.nanoTime();
        while ((linea = lector.readLine()) != null) {
            sumaSecuencial += Integer.parseInt(linea);
        }
        lector.close();
        long finSecuencial = System.nanoTime();
        // Tiempo transcurrido en segundos
        double tiempoTranscurrido = (finSecuencial - inicioSecuencial) / 1_000_000_000.0;


        System.out.println("SUMA SECUENCIAL");
        System.out.println("Suma secuencial total : " +  sumaSecuencial);
        System.out.println("Tiempo necesario      : " +  tiempoTranscurrido + " s");
        System.out.println("\n");





    }


    public static void generarArchivo(String nombreArchivo) throws IOException {
        File file = new File(nombreArchivo + ".txt");
        if (file.exists()) {
            //System.out.println("El archivo " + nombreArchivo + ".txt ya existe.");
            return;
        }

        Random rand = new Random();
        FileWriter writer = new FileWriter(file);

        for (int i = 0; i < NUM_REGISTROS; i++) {
            writer.write((rand.nextInt(10000) + 1) + "\n");
        }
        writer.close();
    }
}

