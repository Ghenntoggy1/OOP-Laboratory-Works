package Lab3.implementations;

import Lab3.interfaces.StackInterface;

import java.util.Arrays;

public class ArrayDownStack implements StackInterface {
    private int topIndex;
    private Object[] stackArray;
    private int defaultSize = 5;
    private int occupiedSpace;

    public ArrayDownStack(int specificSize) {
        this.stackArray = new Object[specificSize];
        this.topIndex = specificSize - 1;
    }

    public ArrayDownStack() {
        this.stackArray = new Object[this.defaultSize];
        this.topIndex = this.defaultSize - 1;
    }

    @Override
    public void push(Object newElement) {
        if (this.topIndex < 0) {
            expandStackArray();
        }
        this.stackArray[this.topIndex] = newElement;
        this.topIndex--;
        setOccupiedSpace();
    }

    private void expandStackArray() {
        int newCapacity = this.stackArray.length + 1;
        Object[] newStackArray = new Object[newCapacity];
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
    public Object peek() {
        if (isEmpty()) {
            System.out.println("NO ELEMENTS IN THE STACK!");
            return null;
        }
        setOccupiedSpace();
        return this.stackArray[this.stackArray.length - this.occupiedSpace];
    }

    @Override
    public void search(Object searchedElement) {
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
        this.stackArray = new Object[this.stackArray.length];
        this.topIndex = this.stackArray.length - 1;
    }

    @Override
    public String toString() {
        for (int i = this.stackArray.length - 1; i >= 0; i--) {
            System.out.println(this.stackArray[i]);
        }
        return Arrays.toString(stackArray);
    }

    public Object[] getStackArray() {
        return this.stackArray;
    }

    public int getOccupiedSpace() {
        return this.occupiedSpace;
    }
}
