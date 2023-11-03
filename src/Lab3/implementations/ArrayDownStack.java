package Lab3.implementations;

import Lab3.interfaces.StackInterface;

import java.util.Arrays;

public class ArrayDownStack<T> implements StackInterface<T> {
    private int topIndex;
    private T[] stackArray;
    private int defaultSize = 5;

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
        if (this.topIndex == 0) {
            expandStackArray();
        }
        this.stackArray[this.topIndex] = newElement;
        this.topIndex--;
    }

    private void expandStackArray() {
        int newCapacity = this.stackArray.length + 1;
        T[] newStackArray = (T[])(new Object[newCapacity]);
        newStackArray[0] = null;
        System.arraycopy(this.stackArray, 0, newStackArray, 1, this.stackArray.length);
        this.stackArray = newStackArray;
        System.out.println(Arrays.toString(newStackArray));
    }

    @Override
    public void pop() {

    }

    @Override
    public T peek() {
        return null;
    }

    @Override
    public void search(Object searchedElement) {

    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public void empty() {

    }
}
