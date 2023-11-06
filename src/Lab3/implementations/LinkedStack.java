package Lab3.implementations;

import Lab3.interfaces.StackInterface;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class LinkedStack implements StackInterface {
    private Object[] linkedListStack;
    private Node topNode;
    private int size;
    private int occupiedSpace;

    public LinkedStack(int specificSize) {
        this.linkedListStack = new Object[specificSize];
        this.topNode = null;
        this.size = 0;
        this.occupiedSpace = 0;
    }

    @Override
    public void push(Object newElement) {
        if (this.size == this.linkedListStack.length) {
            expandLinkedListStack();
        }
        Node newNode = new Node(newElement);
        newNode.setPointer(this.topNode);
        this.linkedListStack[this.size] = Arrays.asList(newNode.getStoredObject(), newNode.getPointer());
        this.topNode = newNode;
        this.size++;
    }

    private void expandLinkedListStack() {
        int newCapacity = this.size + 1;
        this.linkedListStack = Arrays.copyOf(this.linkedListStack, newCapacity);
    }

    @Override
    public void pop() {
        if (isEmpty()) {
            System.out.println("NO ELEMENTS IN THE STACK!");
            return;
        }
        this.topNode = this.topNode.getPointer();
        this.linkedListStack[this.size - 1] = null;
        this.size--;
    }

    @Override
    public Object peek() {
        if (isEmpty()) {
            System.out.println("NO ELEMENTS IN THE STACK!");
            return null;
        }
        return this.topNode.getStoredObject();
    }

    @Override
    public void search(Object searchedElement) {
        if (isEmpty()) {
            System.out.println("NO ELEMENTS IN THE STACK!");
            return;
        }
        int index = this.size - 1;
        boolean isFound = false;
        Node currentNode = this.topNode;
        while (currentNode != null) {
            if (currentNode.getStoredObject().equals(searchedElement)) {
                System.out.println("ELEMENT " + searchedElement + " FOUND AT INDEX " + index);
                isFound = true;
            }
            currentNode = currentNode.getPointer();
            index--;
        }
        if (!isFound && !isEmpty()) {
            System.out.println("ELEMENT " + searchedElement + " NOT FOUND!");
        }
    }

    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    @Override
    public void empty() {
        this.linkedListStack = new Object[this.size];
        this.topNode = null;
        this.size = 0;
    }

    public Object[] getLinkedListStack() {
        return this.linkedListStack;
    }

    public void setOccupiedSpace() {
        this.occupiedSpace = 0;
        Node currentNode = this.topNode;
        while (currentNode != null) {
            currentNode = currentNode.getPointer();
            this.occupiedSpace++;
        }
    }

    public int getOccupiedSpace() {
        return this.occupiedSpace;
    }

    @Override
    public String toString() {
        List<Object> reversedList = new ArrayList<>(Arrays.asList(this.linkedListStack));
        Collections.reverse(reversedList);
        StringBuilder stringBuilder = new StringBuilder();
        for (Object element:
             reversedList) {
            stringBuilder.append(element).append("\n");
        }
        return stringBuilder.toString();
    }
}
