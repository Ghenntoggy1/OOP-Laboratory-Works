package Lab3.implementations;

import Lab3.interfaces.StackInterface;

import java.util.Arrays;

public class ArrayDownStack<T> implements StackInterface<T> {
    private int topIndex;
    private T[] stackArray;
    private int defaultSize = 5;
    private int occupiedSpace;

    public ArrayDownStack(int specificSize) {
        this.stackArray = (T[])(new Object[specificSize]);
        this.topIndex = specificSize - 1;
    }

    public ArrayDownStack() {
        this.stackArray = (T[])(new Object[this.defaultSize]);
        this.topIndex = this.defaultSize - 1;
    }

    @Override
    public void push(T newElement) {
        if (this.topIndex < 0) {
            expandStackArray();
        }
        this.stackArray[this.topIndex] = newElement;
        this.topIndex--;
        setOccupiedSpace();
        System.out.println("TOP INDEX " + topIndex);
        System.out.println("Current Index " + (this.stackArray.length - this.occupiedSpace));
    }

    private void expandStackArray() {
        int newCapacity = this.stackArray.length + 1;
        T[] newStackArray = (T[])(new Object[newCapacity]);
        newStackArray[0] = null;
        System.arraycopy(this.stackArray, 0, newStackArray, 1, this.stackArray.length);
        this.stackArray = newStackArray;
        this.topIndex = 0;
        setOccupiedSpace();
    }

    @Override
    public void pop() {
        if (isEmpty()) {
            return;
        }
        this.topIndex++;
        this.stackArray[this.stackArray.length - this.occupiedSpace] = null;
    }

    @Override
    public T peek() {
        if (isEmpty()) {
            System.out.println("NO ELEMENTS IN THE STACK!");
            return null;
        }
        setOccupiedSpace();
        return this.stackArray[this.stackArray.length - this.occupiedSpace];
    }

    @Override
    public void search(T searchedElement) {
        boolean isFound = false;
        if (isEmpty()) {
            System.out.println("NO ELEMENTS IN THE STACK!");
        }
        else {
            for (int i = this.stackArray.length - 1; i >= 0; i--) {
                if (this.stackArray[i] != null) {
                    if (this.stackArray[i].toString().equals(searchedElement.toString())) {
                        System.out.println("ELEMENT " + searchedElement + " FOUND AT INDEX " + (this.stackArray.length - i - 1));
                        isFound = true;
                    }
                }
            }
        }
        if (!isFound && !isEmpty()) {
            System.out.println("ELEMENT " + searchedElement + " NOT FOUND!");
        }
    }

    @Override
    public boolean isEmpty() {
        for (int i = this.stackArray.length - 1; i >= 0; i--) {
            if (this.stackArray[i] != null) {
                return false;
            }
        }
        return true;
    }

    public void setOccupiedSpace() {
        this.occupiedSpace = 0;
        for (int i = this.stackArray.length - 1; i >= 0; i--) {
            if (this.stackArray[i] != null) {
                this.occupiedSpace++;
            }
        }
    }

    @Override
    public void empty() {
        this.stackArray = (T[])(new Object[this.stackArray.length]);
        this.topIndex = this.stackArray.length - 1;
    }

    @Override
    public String toString() {
        return Arrays.toString(stackArray);
    }

    public int getTopIndex() {
        return this.topIndex;
    }

    public T[] getStackArray() {
        return this.stackArray;
    }

    public int getOccupiedSpace() {
        return this.occupiedSpace;
    }
}