package Lab3.implementations;

import Lab3.interfaces.StackInterface;

import java.util.Arrays;

public class ArrayStackUp implements StackInterface {
    private int topIndex;
    private Object[] stackArray;
    private int defaultSize = 5;

    public ArrayStackUp(int specificSize) {
        this.stackArray = new Object[specificSize];
        this.topIndex = 0;
    }

    public ArrayStackUp() {
        this.stackArray = new Object[this.defaultSize];
        this.topIndex = 0;
    }

    @Override
    public void push(Object newElement) {
        if (this.topIndex == this.stackArray.length) {
            expandStackArray();
        }
        this.stackArray[this.topIndex] = newElement;
        this.topIndex++;
    }

    private void expandStackArray() {
        int newCapacity = this.topIndex + 1;
        this.stackArray = Arrays.copyOf(this.stackArray, newCapacity);
    }

    @Override
    public void pop() {
        if (isEmpty()) {
            System.out.println("NO ELEMENTS IN THE STACK!");
            return;
        }
        this.topIndex--;
        this.stackArray[this.topIndex] = null;
    }

    @Override
    public Object peek() {
        if (isEmpty()) {
            System.out.println("NO ELEMENTS IN THE STACK!");
            return null;
        }
        return this.stackArray[topIndex - 1];
    }

    @Override
    public void search(Object searchedElement) {
        boolean isFound = false;
        if (isEmpty()) {
            System.out.println("NO ELEMENTS IN THE STACK!");
        }
        else {
            for (int i = 0; i < this.topIndex; i++) {
                if (this.stackArray[i].toString().equals(searchedElement.toString())) {
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
        for (int i = this.topIndex - 1; i >= 0 ; i--) {
            if (this.stackArray[i] != null) {
                return false;
            }
        }
        return true;
    }

    @Override
    public String toString() {
        return Arrays.toString(stackArray);
    }

    @Override
    public void empty() {
        this.stackArray = new Object[this.stackArray.length];
        this.topIndex = 0;
    }

    public int getTopIndex() {
        return this.topIndex;
    }

    public Object[] getStackArray() {
        return this.stackArray;
    }
}