package Lab3.implementations;

import Lab3.interfaces.StackInterface;

import java.util.Arrays;
import java.util.Scanner;

public class ArrayStack<T> implements StackInterface<T> {
    private Scanner scanner;
    private int topIndex;
    private T[] stackArray;
    private final int defaultSize = 5;

    public ArrayStack (int specificSize, Scanner scanner) {
        this.stackArray = (T[])(new Object[specificSize]);
        this.topIndex = 0;
        this.scanner = scanner;
    }

    public ArrayStack(Scanner scanner) {
        this.stackArray = (T[])(new Object[this.defaultSize]);
        this.topIndex = 0;
        this.scanner = scanner;
    }

    @Override
    public void push(T newElement) {
        if (topIndex == stackArray.length) {
            System.out.println("STACK FULL! CHOOSE HOW MANY SPACES TO ADD:");

            System.out.println("STACK EXPANDED!");
        }
        stackArray[topIndex] = newElement;
        topIndex++;
    }

    private void expandStackArray(int amountIndexes) {
        int newCapacity = this.topIndex + amountIndexes;
        this.stackArray = Arrays.copyOf(this.stackArray, newCapacity);
    }

    @Override
    public T pop() {
        return null;
    }

    @Override
    public T peek() {
        return null;
    }

    @Override
    public T search() {
        return null;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }
}
