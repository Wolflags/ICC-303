# Practica 3: Implementación de un Framework para Topologías de Redes para Procesamiento Paralelo

## JOSE RAFAEL JAQUEZ TORRES #10146000


### Instrucciones para ejecutar el programa
Se ejecuta el archivo main y automáticamente se mostrarán las pruebas de cada topología de red.

## Funcionamiento de las topologías de red

### Interfaz NetworkTopology
Esta clase es una interfaz que contiene los métodos que deben ser implementados por las topologías de red, los cuales son:

ConfigureNetwork: Este método se encarga de configurar la red, es decir, de la creación de los nodos y las conexiones entre ellos.
SendMessages: Este método se encarga de enviar los mensajes entre los nodos de la red.
RunNetwork: Este método se encarga de ejecutar la red.
ShutdownNetwork: Este método se encarga de apagar la red.

### Clase NetworkManager
Esta clase es la encargada de manejar las topologías de red, es decir, de crear las instancias de las topologías de red y de ejecutarlas. Esta ordena cosas como la cantidad de nodos y los mensajes a enviar independientemente de la topología de red.

### Clases de pruebas
En esta carpeta se encuentran las clases de pruebas de cada topología de red, las cuales implementan NetworkManager para poder ejecutar las pruebas.

### Clase Main
Esta clase es la encargada de ejecutar las pruebas de cada topología de red.

## Topologías de red implementadas

### Bus Network
La topología de red Bus Network es una topología de red en la que todos los nodos están conectados a un solo canal de comunicación. En esta topología, los nodos están conectados en serie, es decir, el primer nodo está conectado al segundo, el segundo al tercero y así sucesivamente. Cuando un nodo envía un mensaje, este mensaje se propaga a través de la red hasta llegar al nodo destino.


### Ring Network
En esta topología de red, los nodos están conectados en forma de anillo, utilizamos sendMessages para enviar mensajes entre nodos, mientras el nodo actual sea diferente al nodo destino, el mensaje se envía al siguiente nodo en el anillo. Si el nodo actual es igual al nodo destino, el mensaje se envía al nodo destino.


### Mesh Network
La topología de red Mesh Network es una topología de red en la que todos los nodos están conectados a todos los demás nodos. En esta topología, los nodos están conectados en una malla, es decir, cada nodo está conectado a todos los demás nodos. Cuando un nodo envía un mensaje, este mensaje se propaga a través de la red hasta llegar al nodo destino.
Se utilizó una lista de nodos para almacenar los nodos de la red, en la clase Node se implementó un método para agregar hijos a un nodo, de esta forma se pueden conectar los nodos entre sí. En el método configureNetwork se crean los nodos y se conectan entre sí, en este caso, cada nodo se conecta con todos los demás nodos. En el método sendMessage se envía el mensaje a través de la red hasta llegar al nodo destino. En el método runNetwork se ejecutan los nodos de la red y en el método shutdown se apagan los nodos de la red.


### Star Network
La topología de red Star Network es una topología de red en la que todos los nodos están conectados a un nodo central. En esta topología, los nodos periféricos están conectados al nodo central. Cuando un nodo envía un mensaje, este mensaje se propaga a través del nodo central hasta llegar al nodo destino. En la clase Node se implementó un método para agregar hijos a un nodo, de esta forma se pueden conectar los nodos entre sí. En el método configureNetwork se crean los nodos y se conectan al nodo central, en el método sendMessage se envía el mensaje a través del nodo central hasta llegar al nodo destino. En el método runNetwork se ejecutan los nodos de la red y en el método shutdown se apagan los nodos de la red.


### Hypercube Network
La topología de red Hypercube Network es una topología de red en la que todos los nodos están conectados a todos los demás nodos a través de una red de hipercubo que consiste en una forma de dos cuadrados. En sendMessage se busca el bit más bajo donde difieren los IDs de los nodos actual y destino, se invierte ese bit en el ID del nodo actual y se envía el mensaje al nodo con el ID resultante.  


### Tree Network
En esta topología de red, los nodos están conectados en forma de árbol, es decir, cada nodo tiene un nodo padre y uno o más nodos hijos. Modificamos la clase Node para que tenga un nodo padre y una lista de nodos hijos. Para el sendMessage se utiliza findNode la cual busca el nodo con el ID correspondiente iterando sobre los nodos del árbol. En el método runNetwork se ejecutan los nodos del árbol y en el método shutdown se apagan los nodos del árbol.


### FullyConnected Network
Esta red completamente conectada es una topología de red en la que todos los nodos están conectados a todos los demás nodos. En el sendMessage primero se verifica que los nodos de origen y destino estén en el rango de nodos de la red, luego se obtienen los nodos de origen y destino y se envía el mensaje a todos los nodos para ser procesado desde el nodo de destino. En el método runNetwork se ejecutan los nodos de la red y en el método shutdown se apagan los nodos de la red.


### Switched Network
La topología de red Switched Network es una topología de red en la que todos los nodos están conectados a un switch. En el sendMessage se verifica que los nodos de origen y destino estén en el rango de nodos de la red, luego se obtienen los nodos de origen y destino y se envía el mensaje al nodo destino. En el método runNetwork se ejecutan los nodos de la red y en el método shutdown se apagan los nodos de la red.