package org.example;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class sumaNumsArchivo {

    private static final String NOMBRE_ARCHIVO = "numeros";

    public static void main(String[] args) throws IOException {
        // 1. Genera un archivo con 1,000,000 de registros comprendido entre 1 y 10,000,
        // el cual deberá usar como base para los demás cálculos.
        String nombreArchivo = "numeros";
        generarArchivo(nombreArchivo);






        
    }


    public static void generarArchivo(String nombreArchivo) throws IOException {
        File file = new File(nombreArchivo + ".txt");
        if (file.exists()) {
            //System.out.println("El archivo " + nombreArchivo + ".txt ya existe.");
            return;
        }

        Random rand = new Random();
        FileWriter writer = new FileWriter(file);

        for (int i = 0; i < 1000000; i++) {
            writer.write((rand.nextInt(10000) + 1) + "\n");
        }
        writer.close();
    }
}

