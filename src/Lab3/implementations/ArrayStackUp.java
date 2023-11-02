package Lab3.implementations;

import Lab3.interfaces.StackInterface;

import java.util.Arrays;
import java.util.Scanner;

public class ArrayStackUp<T> implements StackInterface<T> {
    private Scanner scanner;
    private int topIndex;
    private T[] stackArray;
    private int defaultSize = 5;

    public ArrayStackUp(int specificSize, Scanner scanner) {
        this.stackArray = (T[])(new Object[specificSize]);
        this.topIndex = 0;
        this.scanner = scanner;
    }

    public ArrayStackUp(Scanner scanner) {
        this.stackArray = (T[])(new Object[this.defaultSize]);
        this.topIndex = 0;
        this.scanner = scanner;
    }

    @Override
    public void push(T newElement) {
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
    public T pop() {
        if (isEmpty()) {
            return null;
        }
        this.topIndex--;
        T poppedElement = this.stackArray[this.topIndex];
        this.stackArray[this.topIndex] = null;
//        this.stackArray = Arrays.copyOf(this.stackArray, this.stackArray.length - 1);
        return poppedElement;
    }

    @Override
    public T peek() {
        if (isEmpty()) {
            System.out.println("NO ELEMENTS IN THE STACK!");
            return null;
        }
        return this.stackArray[topIndex - 1];
    }

    @Override
    public void search(T searchedElement) {
        if (isEmpty()) {
            System.out.println("NO ELEMENTS IN THE STACK!");
        }
        for (int i = 0; i < this.stackArray.length; i++) {
            if (this.stackArray[i].toString().equals(searchedElement.toString())) {
                System.out.println("ELEMENT " + searchedElement + " FOUND AT INDEX " + i);
            }
            else if (this.stackArray.length - 1 == i && !this.stackArray[i].toString().equals(searchedElement.toString())) {
                System.out.println("NO SUCH ELEMENT HAS BEEN FOUND!");
            }
        }
    }

    @Override
    public boolean isEmpty() {
        for (int i = 0; i < this.topIndex; i++) {
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

    public int getTopIndex() {
        return this.topIndex;
    }

    public T[] getStackArray() {
        return this.stackArray;
    }
}