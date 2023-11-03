package Lab3.implementations;

import Lab3.interfaces.StackInterface;

public class ArrayDownStack<T> implements StackInterface {
    private int bottomIndex;
    private T[] stackArray;
    private int defaultSize = 5;

    @Override
    public void push(Object newElement) {

    }

    @Override
    public Object pop() {
        return null;
    }

    @Override
    public Object peek() {
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
