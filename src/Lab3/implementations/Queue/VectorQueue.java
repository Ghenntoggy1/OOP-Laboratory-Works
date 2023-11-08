package Lab3.implementations.Queue;

import Lab3.interfaces.QueueInterface;

import java.util.Vector;

public class VectorQueue implements QueueInterface {
    private Vector<Object> vectorQueue;

    public VectorQueue(int capacity) {
        this.vectorQueue = new Vector<>(capacity);
    }

    @Override
    public void enqueue(Object newElement) {
        this.vectorQueue.add(newElement);
    }

    @Override
    public void deque() {
        if (isEmpty()) {
            System.out.println("NO ELEMENTS IN THE QUEUE!");
            return;
        }
        this.vectorQueue.remove(0);
    }

    @Override
    public Object peek() {
        if (isEmpty()) {
            System.out.println("NO ELEMENTS IN THE QUEUE!");
            return null;
        }
        return this.vectorQueue.get(this.vectorQueue.size() - 1);
    }

    @Override
    public void search(Object searchedElement) {
        boolean isFound = false;
        if (isEmpty()) {
            System.out.println("NO ELEMENTS IN THE STACK!");
            return;
        }
        else {
            for (int i = 0; i < this.vectorQueue.size(); i++) {
                if (this.vectorQueue.get(i).toString().equals(searchedElement.toString())) {
                    System.out.println("ELEMENT " + searchedElement + " FOUND AT INDEX " + i);
                    isFound = true;
                }
            }
        }
        if (!isFound) {
            System.out.println("ELEMENT " + searchedElement + " NOT FOUND!");
        }
    }

    @Override
    public boolean isEmpty() {
        return this.vectorQueue.isEmpty();
    }

    @Override
    public void empty() {
        if (isEmpty()) {
            System.out.println("QUEUE ALREADY EMPTY!");
            return;
        }
        this.vectorQueue.clear();
    }

    public Object getRearElement() {
        if (isEmpty()) {
            System.out.println("NO ELEMENTS IN THE QUEUE!");
            return null;
        }
        return this.vectorQueue.get(0);
    }

    @Override
    public String toString() {
        return this.vectorQueue.toString();

    }

    public void deleteQueue() {
        this.vectorQueue = null;
    }
}
