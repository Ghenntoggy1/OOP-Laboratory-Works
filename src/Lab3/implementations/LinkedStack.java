package Lab3.implementations;

import Lab3.interfaces.StackInterface;

public class LinkedStack<T> implements StackInterface<T> {
    private Node<T> topNode;
    private int size;

    public LinkedStack() {
        this.topNode = null;
        this.size = 0;
    }

    @Override
    public void push(T newElement) {
        Node<T> newNode = new Node<>(newElement);
        newNode.setPointer(this.topNode);
        this.topNode = newNode;
        this.size++;
        System.out.println("Size: ");
    }

    @Override
    public void pop() {
        if (isEmpty()) {
            System.out.println("NO ELEMENTS IN THE STACK!");
        } else {
            this.topNode = this.topNode.getPointer();
            this.size--;
        }
        System.out.println("Size: ");
    }

    @Override
    public T peek() {
        if (isEmpty()) {
            System.out.println("NO ELEMENTS IN THE STACK!");
        }

        return this.topNode.getStoredObject();
    }

    @Override
    public void search(T searchedElement) {

    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void empty() {  // elements that were reachable from my top node become targets for garbage collector of Java
        this.topNode = null;
        this.size = 0;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("[");
        Node<T> currentNode = this.topNode;
        while (currentNode != null) {
            result.append(currentNode.getStoredObject());
            currentNode = currentNode.getPointer();
            if (currentNode != null) {
                result.append(", ");
            }
        }
        result.append("]");
        return result.toString();
    }
}
