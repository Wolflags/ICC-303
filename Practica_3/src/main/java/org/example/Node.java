package org.example;

import java.util.ArrayList;
import java.util.List;

public class Node {
    private int id;
    private Node nextNode;
    private Node prevNode;
    private List<Node> children = new ArrayList<>();

    public Node(int id) {
        this.id = id;
    }

    public void setNextNode(Node nextNode) {
        this.nextNode = nextNode;
    }

    public Node getNextNode() {
        return nextNode;
    }

    public void setPrevNode(Node prevNode) {
        this.prevNode = prevNode;
    }

    public Node getPrevNode() {
        return prevNode;
    }

    public void addChild(Node child) {
        this.children.add(child);
    }

    public List<Node> getChildren() {
        return this.children;
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
            Thread.currentThread().interrupt();
        }
    }
}
