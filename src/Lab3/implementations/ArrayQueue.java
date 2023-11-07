package Lab3.implementations;

import Lab3.interfaces.QueueInterface;

import java.util.Arrays;

public class ArrayQueue implements QueueInterface {
    private Object[] arrayQueue;
    private int front;
    private int rear;
    private int size;
    private int defaultSize = 1;

    public ArrayQueue(int capacity) {
        this.arrayQueue = new Object[capacity];
        this.front = 0;
        this.rear = capacity - 1;
        this.size = this.arrayQueue.length;
    }

    public ArrayQueue() {
        this.arrayQueue = new Object[this.defaultSize];
        this.front = 0;
        this.rear = this.defaultSize - 1;
        this.size = this.arrayQueue.length;
    }

    @Override
    public void enqueue(Object newElement) {
        if (isEmpty()) {
            expandQueueArray();
        }
        this.arrayQueue = insertX(newElement, this.front);  // TODO repair expand adds too many nulls
        this.size++;
        this.rear++;
    }

    public Object[] insertX(Object insertedElement, int pos)
    {
        Object[] newArrayQueue = new Object[this.size + 1];
        for (int i = 0; i < this.size + 1; i++) {
            if (i < pos) {
                newArrayQueue[i] = this.arrayQueue[i];
            }
            else if (i == pos) {
                newArrayQueue[i] = insertedElement;
            }
            else {
                newArrayQueue[i] = this.arrayQueue[i - 1];
            }
        }
        return newArrayQueue;
    }

    private void expandQueueArray() {
        int newCapacity = this.size + 1;
        this.arrayQueue = Arrays.copyOf(this.arrayQueue, newCapacity);
        this.size = newCapacity;
    }

    @Override
    public void deque() {
        if (isEmpty()) {
            System.out.println("NO ELEMENTS IN THE QUEUE!");
            return;
        }
        this.arrayQueue[this.rear] = null;
        this.rear--;
    }

    @Override
    public Object peek() {
        if (isEmpty()) {
            System.out.println("NO ELEMENTS IN THE QUEUE!");
            return null;
        }
        return this.arrayQueue[0];
    }

    @Override
    public void search(Object searchedElement) {

    }

    @Override
    public boolean isEmpty() {
        for (int i = 0; i < this.arrayQueue.length ; i++) {
            if (this.arrayQueue[i] != null) {
                return false;
            }
        }
        return true;
    }

    @Override
    public void empty() {
        this.arrayQueue = new Object[this.size];
        this.front = 0;
        this.rear = this.size - 1;
    }

    @Override
    public String toString() {
        return Arrays.toString(this.arrayQueue);
    }
}
