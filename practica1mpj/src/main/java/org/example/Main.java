package org.example;

import mpi.*;

public class Main {
    public static void main(String[] args) {
        MPI.Init(args);

        int rank = MPI.COMM_WORLD.Rank();
        int size = MPI.COMM_WORLD.Size();

        // Definimos el tamaño de las matrices (por simplicidad, matrices cuadradas)
        int N = 2000;
        double[] A = new double[N * N];
        double[] B = new double[N * N];
        double[] C = new double[N * N];
        double[] C_part = new double[(N * N) / size];

        // Inicialización de matrices y medición del tiempo secuencial
        if (rank == 0) {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    A[i * N + j] = Math.random();
                    B[i * N + j] = Math.random();
                }
            }

            long startSeq = System.nanoTime();
            multiplicarMatricesSecuencial(A, B, C, N);
            long endSeq = System.nanoTime();
            System.out.println("Tiempo secuencial: " + (endSeq - startSeq) / 1_000_000.0 + " ms");
        }

        // 2. Arquitectura de Memoria Distribuida:
        // a. Distribuya las filas de las matrices A y B a todos los procesos utilizando la función Bcast() de MPI.
        MPI.COMM_WORLD.Bcast(A, 0, N * N, MPI.DOUBLE, 0);
        MPI.COMM_WORLD.Bcast(B, 0, N * N, MPI.DOUBLE, 0);

        // Calcular el número de filas que cada proceso debe manejar
        int filasPorProceso = (N + size - 1) / size;
        int inicio = rank * filasPorProceso;
        int fin = Math.min(inicio + filasPorProceso, N);

        // Crear una matriz parcial para almacenar los resultados locales de cada proceso
        C_part = new double[(fin - inicio) * N];

        // Medición del tiempo paralelo
        MPI.COMM_WORLD.Barrier(); // Asegurarse de que todos los procesos comiencen al mismo tiempo
        double startPar = MPI.Wtime();

        // 3. Paso de Mensajes:
        // a. Cada proceso calculará una porción de la matriz resultante C utilizando el algoritmo de multiplicación de matrices.
        multiplicarMatrices(A, B, C_part, inicio, fin, N);

        // 4. Recopilación de Resultados:
        // a. Reúna los resultados parciales de cada proceso en el proceso raíz utilizando la función Gather() de MPI.
        int[] sendCounts = new int[size];
        int[] displs = new int[size];

        for (int i = 0; i < size; i++) {
            int start = i * filasPorProceso;
            int end = Math.min(start + filasPorProceso, N);
            sendCounts[i] = (end - start) * N;
            displs[i] = start * N;
        }

        MPI.COMM_WORLD.Gatherv(C_part, 0, (fin - inicio) * N, MPI.DOUBLE, C, 0, sendCounts, displs, MPI.DOUBLE, 0);

        MPI.COMM_WORLD.Barrier(); // Asegurarse de que todos los procesos terminen antes de medir el tiempo
        double endPar = MPI.Wtime();

        if (rank == 0) {
            System.out.println("Tiempo paralelo: " + (endPar - startPar) * 1_000.0 + " ms");
//            System.out.println("Matriz A:");
//            imprimirMatriz(A, N);
//            System.out.println("Matriz B:");
//            imprimirMatriz(B, N);
//            System.out.println("Matriz C:");
//            imprimirMatriz(C, N);
        }

        MPI.Finalize();
    }

    private static void imprimirMatriz(double[] matriz, int N) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print(matriz[i * N + j] + " ");
            }
            System.out.println();
        }
    }

    private static void multiplicarMatrices(double[] A, double[] B, double[] C, int inicio, int fin, int N) {
        for (int i = inicio; i < fin; i++) {
            for (int j = 0; j < N; j++) {
                C[(i - inicio) * N + j] = 0;
                for (int k = 0; k < N; k++) {
                    C[(i - inicio) * N + j] += A[i * N + k] * B[k * N + j];
                }
            }
        }
    }

    private static void multiplicarMatricesSecuencial(double[] A, double[] B, double[] C, int N) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                C[i * N + j] = 0;
                for (int k = 0; k < N; k++) {
                    C[i * N + j] += A[i * N + k] * B[k * N + j];
                }
            }
        }
    }
}
