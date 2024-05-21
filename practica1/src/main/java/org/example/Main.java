package org.example;

import java.util.Random;
import java.util.concurrent.ExecutionException;
public class Main {
    public static void main(String[] args){
        int N = 1000;
        int[][] A = new int[N][N];
        int[][] B = new int[N][N];
        int[][] C = new int[N][N];

        initializarMatriz(A);
        initializarMatriz(B);

        int numProcesadores = A.length;
        multiplicarParalelo(A, B, C, numProcesadores);

        int[][] CSequential = sequentialMatrixMultiplication(A, B);
        boolean correcto = verifyResult(C, CSequential);
        if(correcto) {
            System.out.println("Correcto");
        }else{
            System.out.println("Incorrecto");
        }
    }

    private static void initializarMatriz(int[][] matriz) {
        int N = matriz.length;
        Random rand = new Random();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                int num = rand.nextInt(100);
                matriz[i][j] = num;
            }
        }
    }

    private static void multiplicarParalelo(int[][] A, int[][] B, int[][] C, int numProcesadores){

        Thread[] threads = new Thread[A.length];

        for (int i = 0; i < numProcesadores; i++) {
            int row = i;
            threads[i] = new Thread(() -> multiplyRowByColumn(A,B,C,row));
            threads[i].start();
        }

        // Esperar a que todos los hilos terminen
        for (int i = 0; i < numProcesadores; i++) {
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }



        // Imprimir la matriz resultante C
        for (int i = 0; i < C.length; i++) {
            for (int j = 0; j < C[i].length; j++) {
                System.out.print(C[i][j] + " ");
            }
            System.out.println();
        }
    }

    private static int[][] sequentialMatrixMultiplication(int[][] A, int[][] B) {
        int N = A.length;
        int[][] C = new int[N][N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                C[i][j] = 0;
                for (int k = 0; k < N; k++) {
                    C[i][j] += A[i][k] * B[k][j];
                }
            }
        }

        return C;
    }

    private static boolean verifyResult(int[][] C, int[][] CSequential) {
        int N = C.length;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (Math.abs(C[i][j] - CSequential[i][j]) > 1e-9) {
                    return false;
                }
            }
        }
        return true;
    }

    static void multiplyRowByColumn(int[][] A, int[][] B, int [][] C, int row) {
        for (int col = 0; col < B[0].length; col++) {
            C[row][col] = 0;
            for (int k = 0; k < B.length; k++) {
                C[row][col] += A[row][k] * B[k][col];
            }
        }
    }
}