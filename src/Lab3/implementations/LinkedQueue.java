package Lab3.implementations;

import Lab3.interfaces.QueueInterface;

import java.util.LinkedList;

public class LinkedQueue implements QueueInterface {
    private LinkedList<Object> linkedListQueue;
    private Node topNode;
    private int size;
    private int occupiedSpace;

    @Override
    public void enqueue(Object newElement) {

    }

    @Override
    public void deque() {

    }

    @Override
    public Object peek() {
        return null;
    }

    @Override
    public void search(Object searchedElement) {

    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public void empty() {

    }
}
