package Lab3.implementations;

import Lab3.interfaces.QueueInterface;

import java.util.ArrayList;
import java.util.Arrays;

public class ArrayQueue implements QueueInterface {
    private ArrayList<Object> arrayQueue;
    private int front;
    private int rear;
    private int size;
    private int defaultSize = 1;

    public ArrayQueue(int capacity) {
        this.arrayQueue = new ArrayList<>();
        this.front = 0;
        this.rear = capacity - 1;
//        this.size = this.arrayQueue.size();
    }

    public ArrayQueue() {
        this.arrayQueue = new ArrayList<>();
        this.front = 0;
        this.rear = this.defaultSize - 1;
//        this.size = this.arrayQueue.length;
    }

    @Override
    public void enqueue(Object newElement) {
        this.arrayQueue.add(this.front, newElement);
        this.size = this.arrayQueue.size();
        this.rear = this.arrayQueue.size() - 1;
    }

    @Override
    public void deque() {
        if (isEmpty()) {
            System.out.println("NO ELEMENTS IN THE QUEUE!");
            return;
        }
        this.arrayQueue.remove(this.rear);
        this.rear = this.arrayQueue.size() - 1;
    }

    @Override
    public Object peek() {
        if (isEmpty()) {
            System.out.println("NO ELEMENTS IN THE QUEUE!");
            return null;
        }
        return this.arrayQueue.get(this.front);
    }

    @Override
    public void search(Object searchedElement) {
        boolean isFound = false;
        if (isEmpty()) {
            System.out.println("NO ELEMENTS IN THE STACK!");
        }
        else {
            for (int i = 0; i < this.arrayQueue.size(); i++) {
                if (this.arrayQueue.get(i).toString().equals(searchedElement.toString())) {
                    System.out.println("ELEMENT " + searchedElement + " FOUND AT INDEX " + i);
                    isFound = true;
                }
            }
        }
        if (!isFound && !isEmpty()) {
            System.out.println("ELEMENT " + searchedElement + " NOT FOUND!");
        }
    }

    @Override
    public boolean isEmpty() {
        return this.arrayQueue.isEmpty();
    }

    @Override
    public void empty() {
        this.arrayQueue.clear();
    }

    @Override
    public String toString() {
        return this.arrayQueue.toString();
    }

    public Object getRearElement() {
        if (isEmpty()) {
            System.out.println("NO ELEMENTS IN THE QUEUE!");
            return null;
        }
        return this.arrayQueue.get(this.rear);
    }
}
