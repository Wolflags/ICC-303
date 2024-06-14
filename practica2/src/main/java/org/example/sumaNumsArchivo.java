package org.example;

import java.io.*;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicLong;

public class sumaNumsArchivo {

    private static final String NOMBRE_ARCHIVO = "numeros2";
    private static final int  NUM_REGISTROS = 1000000;
    private static final int NUM_HILOS = 8;

    public static void main(String[] args) throws IOException, InterruptedException {
        // 1. Genera un archivo con 1,000,000 de registros comprendido entre 1 y 10,000,
        // el cual deberá usar como base para los demás cálculos.
        String nombreArchivo = NOMBRE_ARCHIVO;
        generarArchivo(nombreArchivo);


        // 2. Escribe un programa secuencial que sume los elementos de un arreglo de un
        // millón de enteros
        // Empieza el proceso de suma
        long inicioSecuencial = System.nanoTime();

        BufferedReader lector = new BufferedReader(new FileReader(nombreArchivo + ".txt"));
        String linea;
        long sumaSecuencial = 0;
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

        // 3. Modifica tu programa para que use múltiples hilos o procesos para realizar la
        // suma en paralelo. Divide el arreglo en partes iguales para cada hilo/proceso.

        // Crear servicio de ejecución
        ExecutorService executor = Executors.newFixedThreadPool(NUM_HILOS);

        AtomicLong sumaParalela = new AtomicLong(0);

        // Empieza el proceso de suma
        long inicioParalelo = System.nanoTime();

        for (int i = 0; i < NUM_HILOS; i++) {
            int inicio = i * NUM_REGISTROS / NUM_HILOS;
            int fin = (i + 1) * NUM_REGISTROS / NUM_HILOS;
executor.submit(() -> {
                long sumaLocal = 0;
                try {
                    BufferedReader lectorParalelo = new BufferedReader(new FileReader(nombreArchivo + ".txt"));
                    for (int j = 0; j < inicio; j++) {
                        lectorParalelo.readLine();
                    }
                    for (int j = inicio; j < fin; j++) {
                        sumaLocal += Integer.parseInt(lectorParalelo.readLine());
                    }
                    lectorParalelo.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                sumaParalela.addAndGet(sumaLocal);
            });

        }
        executor.shutdown();
        executor.awaitTermination(1, java.util.concurrent.TimeUnit.HOURS);
        long finParalelo = System.nanoTime();

        // Tiempo transcurrido en segundos
        double tiempoTranscurridoParalelo = (finParalelo - inicioParalelo) / 1_000_000_000.0;

        System.out.println("SUMA PARALELA");
        System.out.println("Suma paralela total : " + sumaParalela);
        System.out.println("Tiempo necesario     : " + tiempoTranscurridoParalelo + " s");
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

