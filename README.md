# ICC-303
# Programacion Paralela y Concurrente ICC-303


# Análisis del rendimiento en función del número de procesos utilizados
Observando los tiempos secuenciales vs los paralelos, podemos apreciar que que los tiempos de ejecución paralelos son menores que los secuenciales para 1, 2 y 4 procesos sin embargo, cuando se utilizan más de 8 hilos o procesos, el tiempo de ejecución en paralelo se incrementa de manera considerable, llegando a ser mayor que el tiempo de ejecución en secuencia.

El speedup es mayor que 1 para 1, 2 y 4 hilos/procesos, lo que indica una mejora en el rendimiento. Sin embargo, para 8, 16 y 32 hilos/procesos, el speedup es menor que 1, indicando una pérdida de rendimiento. A medida que el número de hilos o procesos crece, la eficiencia se reduce. Esto sugiere que añadir más hilos o procesos no mejora el rendimiento de manera proporcional. De hecho, la eficiencia se reduce de forma notable, en particular con 8, 16 y 32. El programa es escalable hasta un máximo de 2 procesos.


# Ejemplos de entrada y salida
El programa leerá un archivo con números aleatorios dentro de cierto rango, esto es lo que toma como entrada, y luego, sumará dichos números de las dos formas distintas solicitadas en este caso y se imprimirá el resultado y tiempo. 

1 proceso
![image](https://github.com/Wolflags/ICC-303/assets/113074158/1e55ef9c-a5c4-4a8f-8bfd-7b998be9fca8)


2 procesos
![image](https://github.com/Wolflags/ICC-303/assets/113074158/1c1e9f89-a48d-4285-bbc6-2189c7d89ace)


4 procesos
![image](https://github.com/Wolflags/ICC-303/assets/113074158/ec17ea68-b174-4e18-9bbd-bac0eb8fe32b)


8 procesos
![image](https://github.com/Wolflags/ICC-303/assets/113074158/b6520cbe-f1c5-4624-80b5-f01de86fdf6d)


16 procesos
![image](https://github.com/Wolflags/ICC-303/assets/113074158/7dcdad75-1a33-4507-a085-910c98ea8cd6)


32 procesos
![image](https://github.com/Wolflags/ICC-303/assets/113074158/5e8d003f-5c6e-4d96-8ba9-bc2f2c323715)


