package Lab3.interfaces;

public interface StackInterface {
    void push(Object newElement);
    void pop();
    Object peek();
    void search(Object searchedElement);
    boolean isEmpty();
    void empty();
}
