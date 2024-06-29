package org.example;

public class Node {
    private int id;
    private Node nextNode; // Referencia al siguiente nodo en el anillo

    public Node(int id) {
        this.id = id;
    }

    public void setNextNode(Node nextNode) {
        this.nextNode = nextNode;
    }

    public Node getNextNode() {
        return nextNode;
    }

    public int getId() {
        return id;
    }

    public void receiveMessage(String message) {
        System.out.println("Node " + id + " received message: " + message);
    }

    public void run() {
        // Simulate work activity
        try {
            Thread.sleep((long) (Math.random() * 1000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
