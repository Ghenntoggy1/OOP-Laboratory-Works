package Lab3.implementations.Queue;

import Lab3.interfaces.QueueInterface;

import java.util.LinkedList;

public class LinkedQueue implements QueueInterface {
    private LinkedList<Object> linkedListQueue;

    public LinkedQueue() {
        this.linkedListQueue = new LinkedList<>();
    }

    @Override
    public void enqueue(Object newElement) {
        this.linkedListQueue.add(newElement);
    }

    @Override
    public void deque() {
        if (isEmpty()) {
            System.out.println("NO ELEMENTS IN THE QUEUE!");
            return;
        }
        this.linkedListQueue.removeFirst();
    }

    @Override
    public Object peek() {
        if (isEmpty()) {
            System.out.println("NO ELEMENTS IN THE QUEUE!");
            return null;
        }
        return this.linkedListQueue.getLast();
    }

    @Override
    public void search(Object searchedElement) {
        boolean isFound = false;
        if (isEmpty()) {
            System.out.println("NO ELEMENTS IN THE STACK!");
            return;
        }
        else {
            for (int i = 0; i < this.linkedListQueue.size(); i++) {
                if (this.linkedListQueue.get(i).toString().equals(searchedElement.toString())) {
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
        return this.linkedListQueue.isEmpty();
    }

    @Override
    public void empty() {
        this.linkedListQueue.clear();
    }

    public Object getRearElement() {
        if (isEmpty()) {
            System.out.println("NO ELEMENTS IN THE QUEUE!");
            return null;
        }
        return this.linkedListQueue.getFirst();
    }

    @Override
    public String toString() {
        return this.linkedListQueue.toString();

    }

    public void deleteQueue() {
        this.linkedListQueue = null;
    }
}
