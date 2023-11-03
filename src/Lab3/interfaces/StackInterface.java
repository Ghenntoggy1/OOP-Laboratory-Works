package Lab3.interfaces;

public interface StackInterface<T> {
    void push(T newElement);
    void pop();
    T peek();
    void search(T searchedElement);
    boolean isEmpty();
    String toString();
    void empty();
}
